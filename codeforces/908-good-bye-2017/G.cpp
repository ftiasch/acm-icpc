#include <cstdio>
#include <cstring>

const int N = 705;
const int MOD = (int)1e9 + 7;

struct Sum
{
    int cnt, one;
};

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int n;
char s[N];
Sum dp[N][2];

int main()
{
    while (scanf("%s", s) == 1) {
        n = strlen(s);
        int result = 0;
        for (int d = 1; d < 10; ++ d) {
            memset(dp, 0, sizeof(dp));
            dp[0][0].one = 0;
            dp[0][0].cnt = 1;
            for (int i = 0; i < n; ++ i) {
                int di = s[i] - '0';
                for (int less = 0; less < 2; ++ less) {
                    int nl = less;
                    for (int dd = less ? 9 : di; dd >= 0; -- dd) {
                        update(dp[i + 1][nl].cnt, dp[i][less].cnt);
                        if (dd < d) {
                            update(dp[i + 1][nl].one, dp[i][less].one);
                        } else {
                            update(dp[i + 1][nl].one, (10LL * dp[i][less].one + dp[i][less].cnt) % MOD);
                        }
                        nl = true;
                    }
                }
            }
            update(result, dp[n][0].one);
            update(result, dp[n][1].one);
        }
        printf("%d\n", result);
    }
}
