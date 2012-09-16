// Codeforces Round #136
// Problem B -- Little Elephant and Array
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;
const int M = 500;

int n, m, a[N], c[N + 1], sum[M][N + 1];

#define foreach(i, v) for (typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

int main() {
    scanf("%d%d", &n, &m);
    memset(c, 0, sizeof(c));
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        if (a[i] <= n) {
            c[a[i]] ++;
        }
    }
    vector <int> choices;
    for (int i = 1; i <= n; ++ i) {
        if (c[i] >= i) {
            choices.push_back(i);
        }
    }
    memset(sum, 0, sizeof(sum));
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = 0; j < (int)choices.size(); ++ j) {
            sum[j][i] = sum[j][i + 1];
        }
        int k = lower_bound(choices.begin(), choices.end(), a[i]) - choices.begin();
        if (k < (int)choices.size() && choices[k] == a[i]) {
            sum[k][i] ++;
        }
    }
    while (m > 0) {
        m --;
        int l, r;
        scanf("%d%d", &l, &r);
        l --;
        int result = 0;
        for (int i = 0; i < (int)choices.size(); ++ i) {
            if (sum[i][l] - sum[i][r] == choices[i]) {
                result ++;
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
