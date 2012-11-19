// Codeforces Round #150
// Problem A -- The Brand New Function
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int D = 20;
const int N = 100000;

int n, a[N], sum[D][N + 1], next[D];

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    memset(sum, 0, sizeof(sum));
    for (int k = 0; k < D; ++ k) {
        for (int i = n - 1; i >= 0; -- i) {
            sum[k][i] = sum[k][i + 1] + (a[i] >> k & 1);
        }
    }
    for (int k = 0; k < D; ++ k) {
        next[k] = n;
    }
    vector <int> values;
    for (int i = n - 1; i >= 0; -- i) {
        vector <int> events(1, i);
        for (int k = 0; k < D; ++ k) {
            if (a[i] >> k & 1) {
                next[k] = i;
            }
            if (next[k] < n) {
                events.push_back(next[k]);
            }
        }
        foreach (iter, events) {
            int j = *iter + 1;
            int value = 0;
            for (int k = 0; k < 20; ++ k) {
                if (sum[k][i] > sum[k][j]) {
                    value |= 1 << k;
                }
            }
            values.push_back(value);
        }
    }
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());
    printf("%d\n", (int)values.size());
    return 0;
}
