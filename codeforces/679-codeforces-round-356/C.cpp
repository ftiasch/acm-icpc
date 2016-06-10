#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 500;

int n, component[N][N], sum[N + 1][N + 1], size[N * N], visited[N * N];
char map[N][N + 1];

void dfs(int& x_min, int& x_max, int& y_min, int& y_max, int& size, int x, int y, int c)
{
    if (0 <= x && x < n && 0 <= y && y < n && map[x][y] == '.' && !~component[x][y]) {
        size ++;
        component[x][y] = c;
        x_min = std::min(x_min, x);
        x_max = std::max(x_max, x);
        y_min = std::min(y_min, y);
        y_max = std::max(y_max, y);
        dfs(x_min, x_max, y_min, y_max, size, x - 1, y, c);
        dfs(x_min, x_max, y_min, y_max, size, x, y - 1, c);
        dfs(x_min, x_max, y_min, y_max, size, x, y + 1, c);
        dfs(x_min, x_max, y_min, y_max, size, x + 1, y, c);
    }
}

int main()
{
    int k;
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    memset(component, -1, sizeof(component));
    int result = 0;
    int count = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (!~component[i][j]) {
                int x_min = i;
                int x_max = i;
                int y_min = j;
                int y_max = j;
                auto& s = size[count];
                s = 1;
                if (map[i][j] == '.') {
                    dfs(x_min, x_max, y_min, y_max, --s, i, j, count);
                }
                component[i][j] = count;
                result = std::max(result, s);
                x_min ++;
                y_min ++;
                x_max = std::max(x_max + 1 - k, 0);
                y_max = std::max(y_max + 1 - k, 0);
                if (x_max < x_min && y_max < y_min) {
                    sum[x_max][y_max] += s;
                    sum[x_max][y_min] -= s;
                    sum[x_min][y_max] -= s;
                    sum[x_min][y_min] += s;
                }
                count ++;
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (i) {
                sum[i][j] += sum[i - 1][j];
            }
            if (j) {
                sum[i][j] += sum[i][j - 1];
            }
            if (i && j) {
                sum[i][j] -= sum[i - 1][j - 1];
            }
        }
    }
    memset(visited, -1, sizeof(visited));
    for (int i = 0; i + k <= n; ++ i) {
        for (int j = 0; j + k <= n; ++ j) {
            int id = i * n + j;
            int tmp = sum[i][j];
            auto&& gao = [&](int x, int y) {
                auto&& c = component[x][y];
                auto& ref = visited[c];
                if (0 <= x && x < n && 0 <= y && y < n && map[x][y] == '.' && ref != id) {
                    ref = id;
                    tmp += size[c];
                }
            };
            for (int p = 0; p < k; ++ p) {
                gao(i - 1, j + p);
                gao(i + k, j + p);
                gao(i + p, j - 1);
                gao(i + p, j + k);
            }
            result = std::max(result, tmp);
        }
    }
    printf("%d\n", result);
}
