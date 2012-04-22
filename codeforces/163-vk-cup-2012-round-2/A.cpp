// VK Cup 2012 Round 2
// Problem A -- Substring and Subsequence
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 5555;
const int MOD = 1000000007;

char a[N], b[N];
int dp[N][N];

int main() {
    scanf("%s%s", a, b);
    int n = strlen(a);
    int m = strlen(b);
    memset(dp, 0, sizeof(dp));
    int result = 0;
    for (int i = n - 1; i >= 0; -- i) {
        int tmp = 0;
        for (int j = m - 1; j >= 0; -- j) {
            if (a[i] == b[j]) {
                dp[i][j] = (tmp + 1) % MOD;
            }
            result += dp[i][j];
            result %= MOD;
            if (i + 1 < n) {
                tmp += dp[i + 1][j];
                tmp %= MOD;
            }
        }
    }
    printf("%d\n", result);
    return 0;
}
