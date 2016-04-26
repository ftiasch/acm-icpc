#include <cstdio>
#include <vector>

int main()
{
    int n, m, q;
    scanf("%d%d%d", &n, &m, &q);
    std::vector<std::vector<int>> result(n, std::vector<int>(m));
    std::vector<std::vector<int*>> state(n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            state[i].push_back(&result[i][j]);
        }
    }
    while (q --) {
        int t;
        scanf("%d", &t);
        if (t == 1) {
            int x;
            scanf("%d", &x);
            auto tmp = state[-- x][0];
            for (int i = 1; i < m; ++ i) {
                state[x][i - 1] = state[x][i];
            }
            state[x][m - 1] = tmp;
        } else if (t == 2) {
            int y;
            scanf("%d", &y);
            auto tmp = state[0][-- y];
            for (int i = 1; i < n; ++ i) {
                state[i - 1][y] = state[i][y];
            }
            state[n - 1][y] = tmp;
        } else if (t == 3) {
            int x, y, z;
            scanf("%d%d%d", &x, &y, &z);
            *state[x - 1][y - 1] = z;
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            printf("%d%c", result[i][j], " \n"[j == m - 1]);
        }
    }
}
