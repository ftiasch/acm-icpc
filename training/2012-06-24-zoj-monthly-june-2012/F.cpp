// Problem F -- Choir III
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 111;
const int M = 2222;

int n, m, need_boy, need_girl, weight[N][M], sex[N][M], sum[N][M], bad[N][M], boy[N][M];

int main() {
    while (scanf("%d%d%d%d", &n, &m, &need_boy, &need_girl) == 4) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                scanf("%d%d", &weight[i][j], &sex[i][j]);
            }
        }
        memset(sum, 0, sizeof(sum));
        memset(bad, 0, sizeof(bad));
        memset(boy, 0, sizeof(boy));
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                boy[i][j] = boy[i + 1][j] + boy[i][j + 1] - boy[i + 1][j + 1] + (sex[i][j] == 1);
                bad[i][j] = bad[i + 1][j] + bad[i][j + 1] - bad[i + 1][j + 1] + (weight[i][j] < 0);
                sum[i][j] = sum[i + 1][j] + sum[i][j + 1] - sum[i + 1][j + 1] + weight[i][j];
            }
        }
        int result = -1;
        for (int lower = 0; lower < n; ++ lower) {
            for (int upper = lower + 1; upper <= n; ++ upper) {
                int j = m;
                for (int i = m - 1; i >= 0; -- i) {
                    while (i < j && bad[lower][i] - bad[upper][i] - bad[lower][j] + bad[upper][j] > 0) {
                        j --;
                    }
                    if (i < j) {
                        int boy_count = boy[lower][i] - boy[upper][i] - boy[lower][j] + boy[upper][j];
                        int girl_count = (upper - lower) * (j - i) - boy_count;
                        if (boy_count >= need_boy && girl_count >= need_girl) {
                            result = max(result, sum[lower][i] - sum[upper][i] - sum[lower][j] + sum[upper][j]);
                        }
                    }
                }
            }
        }
        if (result == -1) {
            puts("No solution!");
        } else {
            printf("%d\n", result);
        }
    }
    return 0;
}
