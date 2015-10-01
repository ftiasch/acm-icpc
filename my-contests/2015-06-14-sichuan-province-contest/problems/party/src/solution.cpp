#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>
#include <utility>
#include <algorithm>

const int N = 1000;
const int M = 10000;
const int INF = 1000000000;
const int V = N + 1 << 1;
const int E = N + M << 2;

int w[N], p[N], a[M], b[M], color[N], head[V], edge_count, to[E], capacity[E], next[E], level[V];

void add_edge_(int u, int v, int w)
{
    to[edge_count] = v;
    capacity[edge_count] = w;
    next[edge_count] = head[u];
    head[u] = edge_count ++;
}

void add_edge(int a, int b, int c)
{
    add_edge_(a, b, c);
    add_edge_(b, a, 0);
}

bool bfs(int v, int s, int t)
{
    memset(level, -1, sizeof(*level) * v);
    level[t] = 0;
    std::queue <int> queue;
    queue.push(t);
    while (!queue.empty() && level[s] == -1) {
        int v = queue.front();
        queue.pop();
        for (int it = head[v]; ~it; it = next[it]) {
            int u = to[it];
            if (capacity[it ^ 1] > 0 && level[u] == -1) {
                level[u] = level[v] + 1;
                queue.push(u);
            }
        }
    }
    return level[s] != -1;
}

int dfs(int u, int t, int d)
{
    if (u == t) {
        return d;
    }
    int result = 0;
    for (int it = head[u]; ~it; it = next[it]) {
        int v = to[it];
        if (capacity[it] > 0 && level[u] == level[v] + 1) {
            int r = dfs(v, t, std::min(d - result, capacity[it]));
            capacity[it] -= r;
            capacity[it ^ 1] += r;
            result += r;
            if (result == d) {
                return result;
            }
        }
    }
    level[u] = -1;
    return result;
}

int main()
{
    int n, m;
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", w + i);
        }
        for (int i = 0; i < n; ++ i) {
            scanf("%d", p + i);
            p[i] --;
        }
        std::vector <std::vector <int> > graph(n);
        for (int i = 0; i < m; ++ i) {
            scanf("%d%d", a + i, b + i);
            a[i] --;
            b[i] --;
            graph[a[i]].push_back(b[i]);
            graph[b[i]].push_back(a[i]);
        }
        memset(color, -1, sizeof(*color) * n);
        std::vector <int> color(n, -1);
        for (int s = 0; s < n; ++ s) {
            if (color[s] == -1) {
                color[s] = 0;
                std::queue <int> queue;
                queue.push(s);
                while (!queue.empty()) {
                    int u = queue.front();
                    queue.pop();
                    for (int v : graph[u]) {
                        if (color[v] == -1) {
                            color[v] = color[u] ^ 1;
                            queue.push(v);
                        }
                    }
                }
            }
        }
        int v = n + 1 << 1;
        int s = n << 1;
        int t = s + 1;
        edge_count = 0;
        memset(head, -1, sizeof(*head) * v);
        for (int i = 0; i < n; ++ i) {
            add_edge(i, n + i, w[i]);
            if (p[i] < 2) {
                if (p[i] == color[i]) {
                    add_edge(s, i, INF);
                } else {
                    add_edge(n + i, t, INF);
                }
            }
        }
        for (int i = 0; i < m; ++ i) {
            add_edge(n + a[i], b[i], INF);
            add_edge(n + b[i], a[i], INF);
        }
        int result = 0;
        while (bfs(v, s, t)) {
            result += dfs(s, t, INF);
        }
        printf("%d\n", result);
    }
}
