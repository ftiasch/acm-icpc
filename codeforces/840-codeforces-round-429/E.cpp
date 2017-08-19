#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>

const int N = 50000;
const int K = 8;
const int Q = 150000;

int n, q, a[N], depth[N], parent[N], jump[N], leaf[1 << K], subtree[K][1 << K], table[N][1 << K];
std::vector<int> tree[N], stack;

void add(int v, int d)
{
    for (int i = 0; i < K; ++ i) {
        subtree[i][v >> (K << 1) - 1 - i] += d;
    }
}

void solve(int* t, int d, int p, int q)
{
    if (d < K) {
        int qq = q << 1;
        solve(t, d + 1, p << 1, qq | !!subtree[d][qq | 1]);
        solve(t, d + 1, p << 1 | 1, qq | (1 ^ !!subtree[d][qq]));
    } else {
        t[p] = ((p ^ q) << K) | leaf[q];
    }
}

void dfs(int p, int u)
{
    depth[u] = ~p ? depth[p] + 1 : 0;
    parent[u] = p;
    jump[u] = (int)stack.size() >= (1 << K) ? stack[(int)stack.size() - (1 << K)] : -1;
    stack.push_back(u);
    add(a[u], 1);
    if (~jump[u]) {
        add(a[jump[u]], -1);
    }
    if ((int)stack.size() >= 1 << K) {
        memset(leaf, 0, sizeof(leaf));
        for (int i = 0; i < 1 << K; ++ i) {
            int v = a[stack[(int)stack.size() - 1 - i]];
            auto& ref = leaf[v >> K];
            ref = std::max(ref, i ^ v & (1 << K) - 1);
        }
        solve(table[u], 0, 0, 0);
    }
    for (auto v : tree[u]) {
        if (v != p) {
            dfs(u, v);
        }
    }
    if (~jump[u]) {
        add(a[jump[u]], 1);
    }
    add(a[u], -1);
    stack.pop_back();
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("E.in", "r", stdin);
#endif
    scanf("%d%d", &n, &q);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 1; i < n; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    dfs(-1, 0);
    while (q --) {
        int u, v;
        scanf("%d%d", &u, &v);
        u --;
        v --;
        int result = 0;
        int p = v;
        while (~p && depth[u] <= depth[p] - (1 << K) + 1) {
            result = std::max(result, table[p][depth[v] - depth[p] >> K]);
            p = jump[p];
        }
        while (~p && depth[u] <= depth[p]) {
            result = std::max(result, a[p] ^ (depth[v] - depth[p]));
            p = parent[p];
        }
        printf("%d\n", result);
    }
}
