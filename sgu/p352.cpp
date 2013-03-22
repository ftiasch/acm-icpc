// SGU 352 -- Beerland Attacks
#include <cstdio>
#include <cstring>
#include <climits>
#include <set>
#include <utility>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 4000;
const int M = 100000;

int n, m;

int edge_count, first_edge[N], to[M << 1], length[M], type[M], next_edge[M << 1];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

typedef std::set <std::pair <int, int> > Heap;

int parent[N], answer[N], depth[N];
Heap* heaps[N];

std::pair <int, int> get_edge(int id) {
    return std::make_pair(depth[to[id]] + length[id >> 1] + depth[to[id ^ 1]], id >> 1);
}

void prepare(int p, int u) {
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (type[iter >> 1] && v != p) {
            depth[v] = depth[u] + length[iter >> 1];
            prepare(u, v);
        }
    }
}

int find(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i] = find(parent[i]);
}

void dfs(int p, int u) {
    int max_child = -1;
    int max_child_size = 0;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (type[iter >> 1] && v != p) {
            dfs(u, v);
            if ((int)heaps[v]->size() >= max_child_size) {
                max_child = v;
                max_child_size = heaps[v]->size();
            }
        }
    }
    if (max_child == -1) {
        heaps[u] = new Heap();
    } else {
        heaps[u] = heaps[max_child];
    }
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (type[iter >> 1]) {
            if (v != p && v != max_child) {
                foreach (e, *heaps[v]) {
                    heaps[u]->insert(*e);
                }
            }
        } else {
            heaps[u]->insert(get_edge(iter));
        }
    }
    while (!heaps[u]->empty() && answer[u] == -1) {
        Heap::iterator iter = heaps[u]->begin();
        int id = iter->second << 1;
        if (find(to[id]) == find(to[id ^ 1])) {
            heaps[u]->erase(iter);
        } else {
            answer[u] = heaps[u]->begin()->first - depth[u];
            break;
        }
    }
    if (p != -1) {
        parent[u] = p;
    }
}

int main() {
    scanf("%d%d", &n, &m);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0, a, b; i < m; ++ i) {
        scanf("%d%d%d%d", &a, &b, length + i, type + i);
        a --;
        b --;
        add_edge(a, b);
        add_edge(b, a);
    }
    depth[0] = 0;
    prepare(-1, 0);
    memset(answer, -1, sizeof(answer));
    memset(parent, -1, sizeof(parent));
    dfs(-1, 0);
    for (int i = 1; i < n; ++ i) {
        printf("%d%c", answer[i], i == n - 1 ? '\n' : ' ');
    }
    return 0;
}
