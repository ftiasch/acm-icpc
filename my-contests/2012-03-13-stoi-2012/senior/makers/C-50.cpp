#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 1111;

int dp[N];
char str[N];
bool can[N][N][3];

void update(int &x, int a) {
    x = min(x, a);
}

int main() {
    scanf("%s", str);
    int n = strlen(str);
    memset(can, 0, sizeof(can));
    for (int i = n - 1; i >= 0; -- i) {
        can[i][i][str[i] - 'a'] = true;
        for (int j = i + 1; j < n; ++ j) {
            for (int k = i; k < j; ++ k) {
                for (int a = 0; a < 3; ++ a) {
                    if (can[i][k][a]) {
                        for (int b = 0; b < 3; ++ b) {
                            if (can[k + 1][j][b] && a != b) {
                                can[i][j][3 - a - b] = true;
                            }
                        }
                    }
                }
            }
        }
    }
    dp[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        dp[i] = INT_MAX;
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = i; j < n; ++ j) {
            if (can[i][j][0] || can[i][j][1] || can[i][j][2]) {
                update(dp[j + 1], dp[i] + 1);
            }
        }
    }
    printf("%d\n", dp[n]);
    return 0;
}
