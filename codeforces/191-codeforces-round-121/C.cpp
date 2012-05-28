// Codeforces Round #121
// Problem C -- Fools and Roads
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 222222;
const int D = 22;

int n, edge_count, first_edge[N], to[N], next_edge[N], jump[N][D], depth[N], weight[N], result[N];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void dfs(int p, int u) {
    depth[u] = p == 0? 0: (depth[p] + 1);
    jump[u][0] = p;
    for (int i = 0; jump[u][i] != 0; ++ i) {
        jump[u][i + 1] = jump[jump[u][i]][i];
    }
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            dfs(u, v);
        }
    }
}

int jump_up(int u, int d) {
    for (int i = D - 1; i >= 0; -- i) {
        if (d >= (1 << i)) {
            u = jump[u][i];
            d -= 1 << i;
        }
    }
    return u;
}

int lca(int u, int v) {
    if (depth[u] > depth[v]) {
        return lca(v, u);
    }
    // depth[u] <= depth[v]
    v = jump_up(v, depth[v] - depth[u]);
    if (u == v) {
        return u;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (jump[u][i] != jump[v][i]) {
            u = jump[u][i];
            v = jump[v][i];
        }
    }
    return jump[u][0];
}

void dfs2(int p, int u) {
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            dfs2(u, v);
            weight[u] += weight[v];
            result[iter >> 1] = weight[v];
        }
    }
}

int main() {
    scanf("%d", &n);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        add_edge(a, b);
        add_edge(b, a);
    }
    memset(jump, 0, sizeof(jump));
    dfs(0, 1);
    memset(weight, 0, sizeof(weight));
    int m;
    scanf("%d", &m);
    while (m > 0) {
        m --;
        int a, b;
        scanf("%d%d", &a, &b);
        weight[a] ++;
        weight[b] ++;
        weight[lca(a, b)] -= 2;
    }
    memset(result, 0, sizeof(result));
    dfs2(0, 1);
    for (int i = 0; i < n - 1; ++ i) {
        printf("%d%c", result[i], i == n - 2? '\n': ' ');
    }
    return 0;
}
