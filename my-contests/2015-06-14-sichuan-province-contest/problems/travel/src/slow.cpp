#include <cstdio>
#include <cstring>
#include <vector>

int main()
{
    int n, m, a, b;
    while (scanf("%d%d%d%d", &n, &m, &a, &b) == 4) {
        std::vector <std::vector <int>> floyd(n, std::vector <int>(n, b));
        for (int i = 0; i < m; ++ i) {
            int x, y;
            scanf("%d%d", &x, &y);
            x --;
            y --;
            floyd[x][y] = floyd[y][x] = a;
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    floyd[i][j] = std::min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }
        printf("%d\n", floyd[0][n - 1]);
    }
    return 0;
}
