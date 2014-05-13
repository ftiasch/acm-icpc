#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 1002;

int n, m, a[N][N], dr[N][N], dl[N][N], ur[N][N], ul[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= m; ++ j) {
            scanf("%d", a[i] + j);
        }
    }
    memset(dr, 0, sizeof(dr));
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= m; ++ j) {
            dr[i][j] = std::max(dr[i - 1][j], dr[i][j - 1]) + a[i][j];
        }
    }
    memset(dl, 0, sizeof(dl));
    for (int i = 1; i <= n; ++ i) {
        for (int j = m; j >= 1; -- j) {
            dl[i][j] = std::max(dl[i - 1][j], dl[i][j + 1]) + a[i][j];
        }
    }
    memset(ur, 0, sizeof(ur));
    for (int i = n; i >= 1; -- i) {
        for (int j = 1; j <= m; ++ j) {
            ur[i][j] = std::max(ur[i][j - 1], ur[i + 1][j]) + a[i][j];
        }
    }
    memset(ul, 0, sizeof(ul));
    for (int i = n; i >= 1; -- i) {
        for (int j = m; j >= 1; -- j) {
            ul[i][j] = std::max(ul[i][j + 1], ul[i + 1][j]) + a[i][j];
        }
    }
    int result = 0;
    for (int i = 2; i <= n - 1; ++ i) {
        for (int j = 2; j <= m - 1; ++ j) {
            result = std::max(result, dr[i - 1][j] + ur[i][j - 1] + ul[i + 1][j] + dl[i][j + 1]);
            result = std::max(result, dr[i][j - 1] + ur[i + 1][j] + ul[i][j + 1] + dl[i - 1][j]);
        }
    }
    printf("%d\n", result);
    return 0;
}
