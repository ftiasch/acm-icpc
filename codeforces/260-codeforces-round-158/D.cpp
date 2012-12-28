// Codeforces Round #158
// Problem D -- Black and White Tree
#include <cstdio>
#include <cstring>
#include <queue>
#include <utility>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

using std::pair;
using std::make_pair;
using std::priority_queue;
using std::vector;

int n, color[N], weight[N], pointer[2];
priority_queue <pair <int, int> > heap;
vector <int> orders[2];
vector <pair <pair <int, int>, int> > edges;

int parent[N];

int find_parent(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i] = find_parent(parent[i]);
}

void merge(int a, int b) {
    parent[find_parent(a)] = find_parent(b);
}

bool by_weight(int i, int j) {
    return weight[i] < weight[j];
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", color + i, weight + i);
        if (weight[i]) {
            orders[color[i]].push_back(i);
            heap.push(make_pair(-weight[i], i));
        }
    }
    for (int c = 0; c < 2; ++ c) {
        pointer[c] = 0;
        std::sort(orders[c].begin(), orders[c].end(), by_weight);
    }
    while (!heap.empty()) {
        pair <int, int> ret = heap.top();
        heap.pop();
        if (-ret.first != weight[ret.second]) {
            continue;
        }
        int u = ret.second;
        int c = color[u] ^ 1;
        while (weight[orders[c][pointer[c]]] < weight[u]) {
            pointer[c] ++;
        }
        int v = orders[c][pointer[c]];
        edges.push_back(make_pair(make_pair(u, v), weight[u]));
        weight[v] -= weight[u];
        if (weight[v]) {
            heap.push(make_pair(-weight[v], v));
        }
        weight[u] = 0;
    }
    memset(parent, -1, sizeof(parent));
    foreach (iter, edges) {
        merge(iter->first.first, iter->first.second);
    }
    int flag[2];
    for (int i = 0; i < n; ++ i) {
        flag[color[i]] = i;
    }
    for (int i = 0; i < n; ++ i) {
        if (find_parent(i) != find_parent(flag[color[i] ^ 1])) {
            edges.push_back(make_pair(make_pair(i, flag[color[i] ^ 1]), 0));
            parent[find_parent(i)] = find_parent(flag[color[i] ^ 1]);
        }
    }
    foreach (iter, edges) {
        printf("%d %d %d\n", 1 + iter->first.first, 1 + iter->first.second, iter->second);
    }
    return 0;
}
