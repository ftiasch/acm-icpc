// Codeforces Round #142
// Problem D -- Towers
#include <cstdio>
#include <cstring>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 5000 + 1;

int n, a[N], sum[N];
pair <int, int> dp[N];

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    sum[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        sum[i] = sum[i - 1] + a[i];
    }
    dp[0] = make_pair(0, 0);
    for (int i = 1; i <= n; ++ i) {
        for (int j = i - 1; j >= 0; -- j) {
            if (sum[i] - sum[j] >= dp[j].first) {
                dp[i] = make_pair(sum[i] - sum[j], dp[j].second + 1);
                break;
            }
        }
    }
    printf("%d\n", n - dp[n].second);
    return 0;
}
