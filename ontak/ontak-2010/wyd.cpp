// ONTAK 2010 Day 1 -- Creative Accounting(wyd)
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 2000000;

typedef long long LL;

int n;
LL m;
int minimum[N + 1];
LL sum[N + 1];

int main() {
    ios::sync_with_stdio(false);
    cin >> n >> m;
    sum[0] = 0;
    LL result = 0;
    LL minSum = 0;
    vector <LL> values(1, 0);
    for (int i = 1; i <= n; ++ i) {
        LL a;
        cin >> a;
        sum[i] = (sum[i - 1] + a) % m;
        if (sum[i] < 0) {
            sum[i] += m;
        }
        minSum = min(minSum, sum[i]);
        result = max(result, sum[i] - minSum);
        values.push_back(sum[i]);
    }
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());
    int l = values.size();
    fill(minimum, minimum + l, l);
    for (int i = 0; i <= n; ++ i) {
        int a = lower_bound(values.begin(), values.end(), sum[i]) - values.begin();
        for (int k = a; k >= 0; k -= ~k & k + 1) {
            minimum[k] = min(minimum[k], a);
        }
        int ret = l;
        for (int k = a + 1; k < l; k += ~k & k + 1) {
            ret = min(ret, minimum[k]);
        }
        if (ret != l) {
            result = max(result, sum[i] - values[ret] + m);
        }
    }
    cout << result << endl;
    return 0;
}
