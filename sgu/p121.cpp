// SGU 121 -- Bridges painting
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

const int N = 111;
const int M = N * N;

int tree[M], color[M], visit[N], n, label[N][N], edge_count, first_edge[N], to[M], next_edge[M], degree[N], parent[N];
vector <int> adjacents[N];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}


void dfs(int u, int d) {
    visit[u] = true;
    int tree_edge_count = 0;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        if (tree[iter >> 1] == -1) {
            int v = to[iter];
            tree[iter >> 1] = !visit[v];
            if (!visit[v]) {
                parent[v] = iter ^ 1;
                color[iter >> 1] = d ^ tree_edge_count;
                dfs(v, color[iter >> 1] ^ 1);
                tree_edge_count ^= 1;
            } else {
                color[iter >> 1] = d;
            }
        }
    }
}

bool construct() {
    memset(visit, 0, sizeof(visit));
    memset(tree, -1, sizeof(tree));
    memset(parent, -1, sizeof(parent));
    for (int i = 0; i < n; ++ i) {
        if (!visit[i]) {
            dfs(i, 0);
//for (int k = 0; k < (edge_count >> 1); ++ k) {
//    printf("%d = %d\n", k, color[k]);
//}
            bool is_satisfied = degree[i] <= 1;
            for (int iter = first_edge[i]; iter != -1; iter = next_edge[iter]) {
                if (color[iter >> 1] == 1) {
                    is_satisfied = true;
                }
            }
            if (!is_satisfied) {
                bool found = false;
                for (int iter = first_edge[i]; iter != -1; iter = next_edge[iter]) {
                    if (!tree[iter >> 1]) {
                        int p = to[iter];
                        while (p != i && degree[p] <= 2) {
                            p = to[parent[p]];
                        }
                        if (degree[p] > 2) {
                            found = true;
                            color[iter >> 1] ^= 1;
                            int q = to[iter];
                            while (q != p) {
                                color[parent[q] >> 1] ^= 1;
                                q = to[parent[q]];
                            }
                        }
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
    }
    return true;
}

int main() {
    scanf("%d", &n);
    memset(label, -1, sizeof(label));
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n; ++ i) {
        int j;
        while (scanf("%d", &j) == 1 && j != 0) {
            j --;
            adjacents[i].push_back(j);
            if (label[i][j] == -1) {
                label[i][j] = label[j][i] = edge_count >> 1;
//printf("%d: (%d, %d)\n", edge_count >> 1, i + 1, j + 1);
                add_edge(i, j);
                add_edge(j, i);
            }
        }
        degree[i] = (int)adjacents[i].size();
    }
    if (!construct()) {
        puts("No solution");
    } else {
        for (int i = 0; i < n; ++ i) {
            for (vector <int> :: iterator iter = adjacents[i].begin(); 
                    iter != adjacents[i].end(); ++ iter) {
                int j = *iter;
                printf("%d ", color[label[i][j]] + 1);
            }
            puts("0");
        }
    }
    return 0;
}
