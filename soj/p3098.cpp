#include <cstdio>
#include <cstring>
#include <utility>
#include <climits>
#include <algorithm>

using std::pair;

const int N = 50000 + 1;
const int M = 100000;

int n, m;

int parent[N];
pair <int, pair <int, int> > edges[M];

int find_parent(int i) {
    if (!parent[i]) {
        return i;
    }
    return parent[i] = find_parent(parent[i]);
}

int edge_count, first_edge[N], to[N << 1], weight[N << 1], next_edge[N << 1];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    weight[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

const int D = 16;

int depth[N], go[N][D], limit[N][D];

void dfs(int p, int u) {
    depth[u] = depth[p] + 1;
    go[u][0] = p;
    for (int i = 0; i + 1 < D && go[u][i]; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    for (int i = 0; i + 1 < D; ++ i) {
        limit[u][i + 1] = std::max(limit[u][i], limit[go[u][i]][i]);
    }
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            limit[v][0] = weight[iter];
            dfs(u, v);
        }
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

int query(int u, int d) {
    int ret = INT_MIN;
    for (int i = 0; i < D; ++ i) {
        if (d >> i & 1) {
            ret = std::max(ret, limit[u][i]);
            u = go[u][i];
        }
    }
    return ret;
}

int main() {
    int test_count = 0;
    while (scanf("%d%d", &n, &m) == 2) {
        if (test_count ++) {
            puts("");
        }
        for (int i = 0; i < m; ++ i) {
            scanf("%d%d%d", &edges[i].second.first, &edges[i].second.second, &edges[i].first);
        }
        memset(parent, 0, sizeof(parent));
        std::sort(edges, edges + m);
        edge_count = 0;
        memset(first_edge, -1, sizeof(first_edge));
        for (int i = 0; i < m; ++ i) {
            int a = edges[i].second.first;
            int b = edges[i].second.second;
            if (find_parent(a) != find_parent(b)) {
                parent[find_parent(a)] = find_parent(b);
                add_edge(a, b, edges[i].first);
                add_edge(b, a, edges[i].first);
            }
        }
        memset(go, 0, sizeof(go));
        memset(depth, -1, sizeof(depth));
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j < D; ++ j) {
                limit[i][j] = INT_MIN;
            }
        }
        for (int i = 1; i <= n; ++ i) {
            if (depth[i] == -1) {
                dfs(0, i);
            }
        }
        int q;
        scanf("%d", &q);
        while (q --) {
            int a, b;
            scanf("%d%d", &a, &b);
            int c = lca(a, b);
            printf("%d\n", std::max(query(a, depth[a] - depth[c]), query(b, depth[b] - depth[c])));
        }
    }
    return 0;
}
