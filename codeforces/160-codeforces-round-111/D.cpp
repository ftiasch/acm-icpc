// Codeforces Round #111
// Problem D -- Edges in MST
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 222222;
const char RESULT_STRING[3][22] = {"any", "at least one", "none"};

int n, m, a[N], b[N], weight[N], order[N], parent[N], result[N], \
        vertex_mapping[N], first_edge[N], to[N], next_edge[N], \
        depth[N], lowest[N];
bool visit[N];

bool compare(int i, int j) {
    return weight[i] < weight[j];
}

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

void insert_edge(int i, int a, int b) {
//printf("%d, %d -> %d\n", (i >> 1) + 1, a, b);
    to[i] = b;
    next_edge[i] = first_edge[a];
    first_edge[a] = i;
}

void dfs(int d, int u) {
    depth[u] = lowest[u] = d;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        if (!visit[iter >> 1]) {
            visit[iter >> 1] = true;
            int v = to[iter];
            if (depth[v] == -1) {
                dfs(d + 1, v);
                if (lowest[v] > depth[u]) {
                    result[iter >> 1] = 0;
                }
                lowest[u] = min(lowest[u], lowest[v]);
            } else {
                lowest[u] = min(lowest[u], depth[v]);
            }
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", a + i, b + i, weight + i);
        order[i] = i;
    }
    sort(order, order + m, compare);
    for (int i = 1; i <= n; ++ i) {
        parent[i] = i;
    }
    for (int i = 0; i < m; ++ i) {
        result[i] = 1;
    }
    memset(visit, 0, sizeof(visit));
    for (int begin = 0; begin < m; ) {
        int end = begin + 1;
        while (end < m && weight[order[end]] == weight[order[begin]]) {
            end ++;
        }
        vector <int> vertices;
        vector <int> edge_ids;
        for (int i = begin; i < end; ++ i) {
            if (find(a[order[i]]) == find(b[order[i]])) {
                result[order[i]] = 2;
            } else {
                edge_ids.push_back(order[i]);
                vertices.push_back(find(a[order[i]]));
                vertices.push_back(find(b[order[i]]));
            }
        }
        sort(vertices.begin(), vertices.end());
        vertices.erase(unique(vertices.begin(), vertices.end()), vertices.end());
        for (int i = 0; i < (int)vertices.size(); ++ i) {
            depth[i] = -1;
            first_edge[i] = -1;
            vertex_mapping[vertices[i]] = i;
        }
        for (vector <int> :: iterator iter = edge_ids.begin(); \
                iter != edge_ids.end(); ++ iter) {
            int u = vertex_mapping[find(a[*iter])];
            int v = vertex_mapping[find(b[*iter])];
            insert_edge((*iter) << 1, u, v);
            insert_edge(((*iter) << 1) + 1, v, u);
        }
        for (int i = 0; i < (int)vertices.size(); ++ i) {
            if (depth[i] == -1) {
                dfs(0, i);
            }
        }
        for (int i = begin; i < end; ++ i) {
            if (find(a[order[i]]) != find(b[order[i]])) {
                parent[find(a[order[i]])] = find(b[order[i]]);
            }
        }
        begin = end;
    }
    for (int i = 0; i < m; ++ i) {
        printf("%s\n", RESULT_STRING[result[i]]);
    }
    return 0;
}
