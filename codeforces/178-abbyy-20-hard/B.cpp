// ABBYY Cup 2.0 Hard
// Problem B -- Greedy Merchants
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

#define foreach(i, v) for(__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)


const int N = 100000;
const int D = 20;

int n, m;
std::vector <int> graph[N];

int depth[N], go[N][D], delta[N], sum[N];

std::vector <std::pair <int, int> > tree[N];

int dfs(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    go[u][0] = p;
    for (int i = 0; go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    int sum = 0;
    foreach (iter, graph[u]) {
        int v = *iter;
        if (v == p) {
            continue;
        }
        if (depth[v] == -1) {
            int tmp = dfs(u, v);
            sum += tmp;
            tree[u].push_back(std::make_pair(v, tmp));
        } else if (depth[v] < depth[u]) {
            delta[u] ++;
            delta[v] --;
        }
    }
    return sum + delta[u];
}

void prepare(int u) {
    foreach (iter, tree[u]) {
        int v = iter->first;
        sum[v] = sum[u] + (iter->second == 0);
        prepare(v);
    }
}

int jump(int u, int d) {
    for (int i = 0; i < D; ++ i) {
        if (d >> i & 1) {
            u = go[u][i];
        }
    }
    return u;
}

int lca(int u, int v) {
    if (depth[u] > depth[v]) {
        std::swap(u, v);
    }
    v = jump(v, depth[v] - depth[u]);
    if (u == v) {
        return u;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (go[u][i] != go[v][i]) {
            u = go[u][i];
            v = go[v][i];
        }
    }
    return go[u][0];
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    memset(depth, -1, sizeof(depth));
    memset(go, -1, sizeof(go));
    memset(delta, 0, sizeof(delta));
    dfs(-1, 0);
    sum[0] = 0;
    prepare(0);
    int q;
    scanf("%d", &q);
    while (q --) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        printf("%d\n", sum[a] + sum[b] - 2 * sum[lca(a, b)]);
    }
    return 0;
}
