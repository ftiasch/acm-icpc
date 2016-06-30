#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>

const int N = 5000;

int n, n2, a[N], b[N], dfn[N], low[N], scc[N], sink[N], result[N];
std::vector<int> graph[N];

void dfs(int u)
{
    static int count = 0;
    static std::vector<int> stack;

    if (!~dfn[u]) {
        stack.push_back(u);
        int tmp = low[u] = dfn[u] = count ++;
        for (int v : graph[u]) {
            dfs(v);
            tmp = std::min(tmp, low[v]);
        }
        low[u] = tmp;
        if (dfn[u] == low[u]) {
            int v;
            do {
                v = stack.back();
                stack.pop_back();
                scc[v] = n2;
                low[v] = INT_MAX;
            } while (u != v);
            n2 ++;
        }
    }
}

int bfs(int source, int target)
{
    int s = scc[source];
    std::vector<int> distance(n, INT_MAX);
    distance[source] = 0;
    std::vector<int> queue;
    queue.push_back(source);
    for (int h = 0; h < static_cast<int>(queue.size()); ++ h) {
        auto u = queue.at(h);
        for (int v : graph[u]) {
            if (scc[v] == s && distance[v] == INT_MAX) {
                distance[v] = distance[u] + 1;
                queue.push_back(v);
            }
        }
    }
    return distance[target] == INT_MAX ? INT_MAX : distance[target] + 1;
}

int main()
{
    int m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
        graph[a[i]].push_back(b[i]);
    }
    n2 = 0;
    memset(dfn, -1, sizeof(dfn));
    for (int i = 0; i < n; ++ i) {
        dfs(i);
    }
    for (int i = 0; i < n2; ++ i) {
        sink[i] = true;
        result[i] = INT_MAX;
    }
    for (int i = 0; i < m; ++ i) {
        if (scc[a[i]] != scc[b[i]]) {
            sink[scc[a[i]]] = false;
        }
    }
    for (int i = 0; i < m; ++ i) {
        int s = scc[a[i]];
        if (scc[b[i]] == s && sink[s]) {
            result[s] = std::min(result[s], bfs(b[i], a[i]));
        }
    }
    int answer = n;
    for (int i = 0; i < n2; ++ i) {
        if (sink[i]) {
            answer += 1 + (result[i] == INT_MAX ? -1 : 998 * result[i]);
        }
    }
    printf("%d\n", answer);
}
