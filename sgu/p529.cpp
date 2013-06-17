// SGU 529 -- It's Time to Repair the Roads
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)
#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) ((int)(v).size())

using std::vector;
using std::pair;

const int N = 40000;
const int D = 20;

int n, m, a[N], b[N], weight[N], edge_id[N], new_weight[N];

bool by_weight(int i, int j) {
    return weight[i] < weight[j];
}

int parent[D][N];

int find(int i, int u) {
    if (parent[i][u] != u) {
        parent[i][u] = find(i, parent[i][u]);
    }
    return parent[i][u];
}

bool merge(int i, int u, int v) {
    if (find(i, u) == find(i, v)) {
        return false;
    }
    parent[i][find(i, u)] = find(i, v);
    return true;
}

int visit_count, visit[N];

void copy_parent(int depth, vector <int> &edges) {
    vector <pair <int, int> > nodes;
    foreach (iter, edges) {
#define ADD(i) nodes.push_back(std::make_pair(find(depth - 1, i), i))
        ADD(a[*iter]);
        ADD(b[*iter]);
#undef ADD
    }
    std::sort(ALL(nodes));
    nodes.erase(std::unique(ALL(nodes)), nodes.end());
    for (int i = 0; i < SIZE(nodes); ) {
        int j = i;
        while (j < SIZE(nodes) && nodes[i].first == nodes[j].first) {
            parent[depth][nodes[j].second] = nodes[i].second;
            j ++;
        }
        i = j;
    }
}

void solve(int depth, int l, int r, vector <int> edges, int extra_cost) {
    copy_parent(depth, edges);
    for (int i = l; i < r; ++ i) {
        merge(depth, a[edge_id[i]], b[edge_id[i]]);
    }
    std::sort(ALL(edges), by_weight);
    vector <int> new_edges;
    foreach (iter, edges) {
        if (merge(depth, a[*iter], b[*iter])) {
            // necessary
            extra_cost += weight[*iter];
            new_edges.push_back(*iter);
        }
    }
    copy_parent(depth, edges);
    foreach (iter, new_edges) {
        merge(depth, a[*iter], b[*iter]);
    }
    new_edges.clear();
    visit_count ++;
    for (int i = l; i < r; ++ i) {
        if (visit[edge_id[i]] != visit_count) {
            new_edges.push_back(edge_id[i]);
            visit[edge_id[i]] = visit_count;
        }
    }
    copy_parent(depth + 1, edges);
    foreach (iter, edges) {
        if (visit[*iter] != visit_count) {
            if (merge(depth + 1, a[*iter], b[*iter])) {
                // possible
                new_edges.push_back(*iter);
            }
        }
    }
//printf("[%d, %d) : %d\n -> ", l, r, extra_cost);
//foreach (iter, new_edges) {
//    printf("%d, ", *iter);
//}
//puts("");
    if (r - l > 1) {
        int m = l + r >> 1;
        solve(depth + 1, l, m, new_edges, extra_cost);
        solve(depth + 1, m, r, new_edges, extra_cost);
    } else {
        weight[edge_id[l]] = new_weight[l];
        std::sort(ALL(new_edges), by_weight);
        copy_parent(depth + 1, new_edges);
        int cost = extra_cost;
        foreach (iter, new_edges) {
            if (merge(depth + 1, a[*iter], b[*iter])) {
                cost += weight[*iter];
            }
        }
        printf("%d\n", cost);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", a + i, b + i, weight + i);
        a[i] --, b[i] --;
    }
    int q;
    scanf("%d", &q);
    for (int i = 0; i < q; ++ i) {
        scanf("%d%d", edge_id + i, new_weight + i);
        edge_id[i] --;
    }
    for (int i = 0; i < n; ++ i) {
        parent[0][i] = i;
    }
    vector <int> edges;
    for (int i = 0; i < m; ++ i) {
        edges.push_back(i);
    }
    visit_count = 0;
    memset(visit, 0, sizeof(visit));
    solve(1, 0, q, edges, 0);
    return 0;
}
