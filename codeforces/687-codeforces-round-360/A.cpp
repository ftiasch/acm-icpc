#include <cstdio>
#include <vector>

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    std::vector<std::vector<int>> graph(n);
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph.at(a).push_back(b);
        graph.at(b).push_back(a);
    }
    std::vector<int> color(n, -1);
    bool ok = true;
    for (int s = 0; s < n && ok; ++ s) {
        if (!~color[s]) {
            std::vector<int> queue;
            queue.push_back(s);
            color[s] = 0;
            for (int h = 0; h < static_cast<int>(queue.size()); ++ h) {
                auto u = queue[h];
                for (int v : graph.at(u)) {
                    if (!~color[v]) {
                        color[v] = color[u] ^ 1;
                        queue.push_back(v);
                    }
                    ok &= color[u] != color[v];
                }
            }
        }
    }
    if (ok) {
        std::vector<std::vector<int>> result(2);
        for (int i = 0; i < n; ++ i) {
            result[color[i]].push_back(i);
        }
        for (int i = 0; i < 2; ++ i) {
            printf("%d\n", static_cast<int>(result[i].size()));
            for (auto&& v : result[i]) {
                printf("%d ", v + 1);
            }
            puts("");
        }
    } else {
        puts("-1");
    }
}
