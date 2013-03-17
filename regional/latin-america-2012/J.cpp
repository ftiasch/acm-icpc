// Latin America 2012
// Problem J -- Joining Couples
#include <cstdio>
#include <cstring>
#include <queue>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;
const int D = 20;

int n, next[N], degree[N];
std::vector <int> to[N];

bool in_cycle[N], visit[N];
int ring_count, ring_size[N], ring_id[N], tree_count, tree_id[N];

int go[N][D], depth[N];

void prepare(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    memset(go[u], -1, sizeof(go[u]));
    go[u][0] = p;
    for (int i = 0; i + 1 < D && go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    foreach (iter, to[u]) {
        int v = *iter;
        if (!in_cycle[v]) {
            ring_id[v] = ring_id[u];
            tree_id[v] = tree_id[u];
            prepare(u, v);
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

int query(int a, int b) {
    if (ring_id[a] != ring_id[b]) {
        return -1;
    }
    if (tree_id[a] != tree_id[b]) {
        int r = ring_size[ring_id[a]];
        int d = std::abs(tree_id[a] - tree_id[b]);
        return depth[a] + depth[b] + std::min(d, r - d);
    }
    int c = lca(a, b);
    return depth[a] + depth[b] - 2 * depth[c];
}

int main() {
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            to[i].clear();
        }
        memset(degree, 0, sizeof(degree));
        for (int i = 0; i < n; ++ i) {
            scanf("%d", next + i);
            to[-- next[i]].push_back(i);
            degree[next[i]] ++;
        }
        std::queue <int> queue;
        memset(in_cycle, true, sizeof(in_cycle));
        for (int i = 0; i < n; ++ i) {
            if (!degree[i]) {
                queue.push(i);
            }
        }
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            in_cycle[u] = false;
            if (!-- degree[next[u]]) {
                queue.push(next[u]);
            }
        }
        ring_count = tree_count = 0;
        memset(visit, 0, sizeof(visit));
        for (int i = 0; i < n; ++ i) {
            if (in_cycle[i]) {
                if (!visit[i]) {
                    ring_size[ring_count] = 0;
                    int j = i;
                    do {
                        visit[j] = true;
                        ring_id[j] = ring_count;
                        ring_size[ring_count] ++;
                        tree_id[j] = tree_count ++;
                        j = next[j];
                    } while (j != i);
                    ring_count ++;
                }
                prepare(-1, i);
            }
        }
        int q;
        scanf("%d", &q);
        while (q --) {
            int a, b;
            scanf("%d%d", &a, &b);
            printf("%d\n", query(-- a, -- b));
        }
    }
    return 0;
}
