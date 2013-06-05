// Codeforces Beta Round #34
// Problem D -- Road Map
#include <cstdio>
#include <cstring>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 50000;

int n, a, b, parent[N];
std::vector <int> edges[N];

void add_edge(int a, int b) {
    edges[a].push_back(b);
}

void dfs(int p, int u) {
    parent[u] = p;
    foreach (iter, edges[u]) {
        int v = *iter;
        if (v != p) {
            dfs(u, v);
        }
    }
}

int main() {
    scanf("%d%d%d", &n, &a, &b);
    a --, b --;
    for (int i = 0; i < n; ++ i) {
        if (i != a) {
            int p;
            scanf("%d", &p);
            p --;
            add_edge(p, i);
            add_edge(i, p);
        }
    }
    dfs(-1, b);
    for (int i = 0; i < n; ++ i) {
        if (i != b) {
            printf("%d\n", parent[i] + 1);
        }
    }
    return 0;
}
