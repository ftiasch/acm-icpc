#include <cstdio>
#include <cstring>
#include <utility>
#include <stack>
#include <vector>
#include <queue>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

const int N = 1000 + 1;

int n;
bool graph[N][N], used[N][N];

int depth[N], lowest[N];
std::stack <std::pair <int, int> > stack;

bool in[N];
int color[N];

void solve(std::vector <int> nodes) {
    std::sort(nodes.begin(), nodes.end());
    nodes.erase(std::unique(nodes.begin(), nodes.end()), nodes.end());
    foreach (iter, nodes) {
        color[*iter] = -1;
    }
    bool valid = true;
    foreach (iter, nodes) {
        if (color[*iter] == -1) {
            std::queue <int> queue;
            queue.push(*iter);
            color[*iter] = 0;
            while (!queue.empty()) {
                int u = queue.front();
                queue.pop();
                foreach (iter, nodes) {
                    int v = *iter;
                    if (graph[u][v]) {
                        if (color[v] == -1) {
                            color[v] = color[u] ^ 1;
                            queue.push(v);
                        } else {
                            valid &= color[u] != color[v];
                        }
                    }
                }
            }
        }
    }
    if (!valid) {
        foreach (iter, nodes) {
            in[*iter] = true;
        }
    }
}

void dfs(int p, int u) {
    lowest[u] = depth[u] = depth[p] + 1;
    for (int v = 1; v <= n; ++ v) {
        if (graph[u][v] && !used[u][v]) {
            used[u][v] = used[v][u] = true;
            if (depth[v] == -1) {
                stack.push(std::make_pair(u, v));
                dfs(u, v);
                if (lowest[v] >= depth[u]) {
                    std::vector <int> nodes;
                    while (true) {
                        std::pair <int, int> ret = stack.top();
                        stack.pop();
                        nodes.push_back(ret.first);
                        nodes.push_back(ret.second);
                        if (ret.first == u && ret.second == v) {
                            break;
                        }
                    }
                    solve(nodes);
                }
                lowest[u] = std::min(lowest[u], lowest[v]);
            } else {
                lowest[u] = std::min(lowest[u], depth[v]);
            }
        }
    }
}

int main() {
    int m;
    while (scanf("%d%d", &n, &m) == 2 && n + m) {
        memset(graph, true, sizeof(graph));
        for (int i = 1; i <= n; ++ i) {
            graph[i][i] = false;
        }
        for (int i = 0, a, b; i < m; ++ i) {
            scanf("%d%d", &a, &b);
            graph[a][b] = graph[b][a] = false;
        }
        memset(depth, -1, sizeof(depth));
        memset(used, 0, sizeof(used));
        memset(in, 0, sizeof(in));
        for (int i = 1; i <= n; ++ i) {
            if (depth[i] == -1) {
                dfs(0, i);
            }
        }
        int count = 0;
        for (int i = 1; i <= n; ++ i) {
            count += !in[i];
        }
        printf("%d\n", count);
    }
    return 0;
}
