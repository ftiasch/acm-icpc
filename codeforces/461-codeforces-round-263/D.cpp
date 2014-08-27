#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <queue>
#include <utility>
#include <vector>

const int N = 100000;

int color[N + 2];
std::vector <std::pair <int, int>> graph[N + 2];

void add_edge(int a, int b, int c)
{
    graph[a].push_back({b, c});
    graph[b].push_back({a, c});
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    while (m --) {
        int a, b;
        char buffer[2];
        scanf("%d%d%s", &a, &b, buffer);
        int u = n - 1 - std::abs(a - b);
        int v = std::abs(a + b - n - 1);
        if (u > v) {
            std::swap(u, v);
        }
        add_edge(u, v + 2, *buffer == 'o');
    }
    int component = -2;
    memset(color, -1, sizeof(color));
    for (int source = 0; source < n + 2; ++ source) {
        if (color[source] == -1) {
            color[source] = 0;
            component ++;
            std::queue <int> queue;
            queue.push(source);
            while (!queue.empty()) {
                int u = queue.front();
                queue.pop();
                for (auto edge : graph[u]) {
                    int v = edge.first;
                    int w = edge.second;
                    if (color[v] == -1) {
                        color[v] = color[u] ^ w;
                        queue.push(v);
                    } else if ((color[u] ^ color[v]) != w) {
                        puts("0");
                        return 0;
                    }
                }
            }
        }
    }
    int result = 1;
    while (component --) {
        result = result * 2 % ((int)1e9 + 7);
    }
    printf("%d\n", result);
    return 0;
}
