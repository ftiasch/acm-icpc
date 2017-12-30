#include <algorithm>
#include <cstdio>

const int N = 1001;
const int MOD = (int)1e9 + 7;

int n, pa, pb, dp[N][N];

int inv(int a)
{
    return a == 1 ? a : 1LL * (MOD - MOD / a) * inv(MOD % a) % MOD;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("D.in", "r", stdin);
#endif
    while (scanf("%d%d%d", &n, &pa, &pb) == 3) {
        int s = inv(pa + pb);
        pa = 1LL * pa * s % MOD;
        pb = 1LL * pb * s % MOD;
        int ipb = inv(pb);
        for (int a = n; a >= 0; -- a) {
            for (int ab = n; ab >= 0; -- ab) {
                if (ab == n) {
                    dp[a][ab] = 0;
                } else if (a == n) {
                    dp[a][ab] = (1LL * pa * ipb + (dp[a][n] + n)) % MOD;
                } else if (a == 0) {
                    dp[a][ab] = dp[a + 1][ab];
                } else {
                    dp[a][ab] = (1LL * pa * dp[a + 1][ab] + 1LL * pb * (dp[a][std::min(ab + a, n)] + a)) % MOD;
                }
            }
        }
        printf("%d\n", dp[0][0]);
    }
}
