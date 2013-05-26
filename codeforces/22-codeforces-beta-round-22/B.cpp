// Codeforces Beta Round #22
// Problem B -- Bargaining Table
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 26;

int n, m, sum[N][N];
char map[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    memset(sum, 0, sizeof(sum));
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = m - 1; j >= 0; -- j) {
            sum[i][j] = sum[i + 1][j] + sum[i][j + 1] - sum[i + 1][j + 1] + (map[i][j] == '1');
        }
    }
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            for (int x = i + 1; x <= n; ++ x) {
                for (int y = j + 1; y <= m; ++ y) {
                    if (sum[i][j] - sum[x][j] - sum[i][y] + sum[x][y] == 0) {
                        answer = std::max(answer, 2 * (x - i + y - j));
                    }
                }
            }
            
        }
    }
    printf("%d\n", answer);
    return 0;
}
