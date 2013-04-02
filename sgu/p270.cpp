// SGU 270 -- Thimbles
#include <cstdio>
#include <cstring>

const int N = 100;
const int M = 1000;

int n, m, a[M], b[M], count[N];
bool valid[N];

int edge_count, first_edge[N], to[M << 1], next_edge[M << 1];
bool used[M];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int depth[N];
bool back[N];

void dfs(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        if (!used[iter >> 1]) {
            used[iter >> 1] = true;
            int v = to[iter];
            if (depth[v] == -1) {
                dfs(u, v);
            } else {
                back[v] = true;
            }
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
        if (a[i] == 0) {
            count[b[i]] ++;
        }
    }
    memset(valid, false, sizeof(valid));
    valid[0] = true;
    for (int i = 1; i < n; ++ i) {
        valid[0] &= count[i] == 0;
    }
    for (int i = 1; i < n; ++ i) {
        valid[0] |= count[i] > 0 && !(count[i] & 1);
    }
    for (int i = 1; i < n; ++ i) {
        edge_count = 0;
        memset(first_edge, -1, sizeof(first_edge));
        for (int j = 0; j < m; ++ j) {
            if (a[j] != 0 || b[j] != i) {
                add_edge(a[j], b[j]);
                add_edge(b[j], a[j]);
            }
        }
#define DFS(u) memset(depth, -1, sizeof(depth)); \
        memset(back, 0, sizeof(back)); \
        memset(used, 0, sizeof(used)); \
        dfs(-1, u);
        DFS(0);
        if (depth[i] != -1) {
            valid[i] = true;
            if (count[i] > 0) {
                valid[0] = true;
            }
        }
        if (count[i] & 1) {
            valid[i] = true;
        } else if (count[i] > 0) {
            valid[0] = true;
        }
        if (count[i] > 0) {
            if (back[0]) {
                valid[i] = true;
                if (count[i] >= 2) {
                    valid[0] = true;
                }
            }
            DFS(i);
            if (back[i]) {
                valid[i] = true;
                if (count[i] >= 2) {
                    valid[0] = true;
                }
            }
        }
#undef DFS
    }
    for (int i = 0; i < n; ++ i) {
        if (valid[i]) {
            printf("%d ", i + 1);
        }
    }
    puts("");
    return 0;
}
