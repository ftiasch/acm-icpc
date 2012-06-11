// SGU 143 -- Long Live the Queen
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 50000;

int n, weight[N], first_edge[N], edge_count, to[N], next_edge[N], maximum[N];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void dfs(int p, int u) {
    maximum[u] = weight[u];
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            dfs(u, v);
            if (maximum[v] > 0) {
                maximum[u] += maximum[v];
            }
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", weight + i);
    }
    memset(first_edge, -1, sizeof(first_edge));
    edge_count = 0;
    for (int i = 1; i <= n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        add_edge(a, b);
        add_edge(b, a);
    }
    dfs(0, 1);
    printf("%d\n", *max_element(maximum + 1, maximum + 1 + n));
    return 0;
}
