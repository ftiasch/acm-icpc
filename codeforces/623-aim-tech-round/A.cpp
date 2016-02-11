#include <algorithm>
#include <cstdlib>
#include <cstdio>
#include <vector>

std::vector<int> solve(int n, const std::vector<std::vector<bool>>& graph)
{
    std::vector<int> result(n, -1);
    for (int i = 0; i < n; ++ i) {
        bool isolated = true;
        for (int j = 0; j < n; ++ j) {
            isolated &= !graph[i][j];
        }
        if (isolated) {
            result[i] = 1;
        }
    }
    int s = std::find(result.begin(), result.end(), -1) - result.begin();
    if (s < n) {
        result[s] = 0;
        bool changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < n; ++ i) {
                if (~result[i]) {
                    for (int j = 0; j < n; ++ j) {
                        if (graph[i][j]) {
                            if (result[i] == result[j]) {
                                return {};
                            }
                            changed |= !~result[j];
                            result[j] = result[i] ^ 2;
                        }
                    }
                }
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (std::abs(result[i] - result[j]) == 2 ^ graph[i][j]) {
                return {};
            }
        }
    }
    return result;
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    std::vector<std::vector<bool>> graph(n, std::vector<bool>(n, true));
    for (int i = 0; i < n; ++ i) {
        graph[i][i] = false;
    }
    for (int _ = 0; _ < m; ++ _) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph[a][b] = graph[b][a] = false;
    }
    std::vector<int> result = solve(n, graph);
    if (result.empty()) {
        puts("No");
    } else {
        puts("Yes");
        for (int i = 0; i < n; ++ i) {
            putchar('a' + result[i]);
        }
        puts("");
    }
    return 0;
}
