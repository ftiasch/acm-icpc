#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

const int N = 200000;
const int D = 20;

int u[N], v[N], edge_count, head[N], to[N << 1], next[N << 1], depth[N], used[N], comp_count, comp[N], bcc_count, bcc[N], jump[N][D], up[N], down[N];
std::vector <int> tree[N];

void add_edge(int u, int v)
{
    to[edge_count] = v;
    next[edge_count] = head[u];
    head[u] = edge_count ++;
}

std::vector <int> stack;

int dfs(int p, int u)
{
    comp[u] = comp_count;
    stack.push_back(u);
    int low = depth[u] = ~p ? depth[p] + 1 : 0;
    for (int i = head[u]; ~i; i = next[i]) {
        if (!used[i >> 1]) {
            used[i >> 1] = true;
            int v = to[i];
            if (depth[v] == -1) {
                low = std::min(low, dfs(u, v));
            } else {
                low = std::min(low, depth[v]);
            }
        }
    }
    if (low == depth[u]) {
        while (true) {
            int v = stack.back();
            stack.pop_back();
            bcc[v] = bcc_count;
            if (u == v) {
                break;
            }
        }
        bcc_count ++;
    }
    return low;
}

void prepare(int p, int u) {
    depth[u] = ~p ? depth[p] + 1 : 0;
    jump[u][0] = p;
    for (int i = 0; jump[u][i]; ++ i) {
        jump[u][i + 1] = jump[jump[u][i]][i];
    }
    for (int v : tree[u]) {
        if (v != p) {
            prepare(u, v);
        }
    }
}

int lca(int a, int b)
{
    if (depth[a] > depth[b]) {
        std::swap(a, b);
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (depth[b] - depth[a] >> i & 1) {
            b = jump[b][i];
        }
    }
    if (a == b) {
        return a;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (jump[a][i] != jump[b][i]) {
            a = jump[a][i];
            b = jump[b][i];
        }
    }
    return jump[a][0];
}

bool check(int p, int u)
{
    depth[u] = 0;
    for (int v : tree[u]) {
        if (v != p) {
            if (!check(u, v)) {
                return false;
            }
            if (up[v] && down[v]) {
                return false;
            }
            up[u] += up[v];
            down[u] += down[v];
        }
    }
    return true;
}

int main()
{
    int n, m, q;
    scanf("%d%d%d", &n, &m, &q);
    edge_count = 0;
    memset(head, -1, sizeof(head));
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", u + i, v + i);
        u[i] --;
        v[i] --;
        add_edge(u[i], v[i]);
        add_edge(v[i], u[i]);
    }
    memset(depth, -1, sizeof(depth));
    memset(used, 0, sizeof(used));
    comp_count = bcc_count = 0;
    for (int i = 0; i < n; ++ i) {
        if (depth[i] == -1) {
            dfs(-1, i);
            comp_count ++;
        }
    }
    for (int i = 0; i < m; ++ i) {
        int x = bcc[u[i]];
        int y = bcc[v[i]];
        if (x != y) {
            tree[x].push_back(y);
            tree[y].push_back(x);
        }
    }
    memset(depth, -1, sizeof(depth));
    memset(jump, -1, sizeof(jump));
    for (int i = 0; i < n; ++ i) {
        if (depth[i] == -1) {
            prepare(-1, i);
        }
    }
    memset(up, 0, sizeof(up));
    memset(down, 0, sizeof(down));
    for (int _ = 0; _ < q; ++ _) {
        int s, d;
        scanf("%d%d", &s, &d);
        s --;
        d --;
        if (comp[s] != comp[d]) {
            puts("No");
            return 0;
        }
        s = bcc[s];
        d = bcc[d];
        int i = lca(s, d);
        up[s] ++;
        up[i] --;
        down[i] --;
        down[d] ++;
    }
    memset(depth, -1, sizeof(depth));
    bool valid = true;
    for (int i = 0; i < n && valid; ++ i) {
        if (depth[i] == -1) {
            valid &= check(-1, i);
        }
    }
    puts(valid ? "Yes" : "No");
    return 0;
}
