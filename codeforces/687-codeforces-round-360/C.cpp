#include <cstdio>
#include <cstring>
#include <vector>

const int N = 500;

bool dp[N + 1][N + 1];

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    memset(dp, 0, sizeof(dp));
    dp[0][0] = true;
    for (int _ = 0; _ < n; ++ _) {
        int c;
        scanf("%d", &c);
        for (int i = m; i >= c; -- i) {
            for (int j = i; j >= 0; -- j) {
                dp[i][j] |= dp[i - c][j];
                if (j >= c) {
                    dp[i][j] |= dp[i - c][j - c];
                }
            }
        }
    }
    std::vector<int> result;
    for (int i = 0; i <= m; ++ i) {
        if (dp[m][i]) {
            result.push_back(i);
        }
    }
    printf("%d\n", static_cast<int>(result.size()));
    for (auto&& x : result) {
        printf("%d ", x);
    }
    puts("");
}
