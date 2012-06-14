// SGU 149 -- Computer Network
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 22222;

int n, edge_count, first_edge[N], to[N], next_edge[N], length[N], dist[2][N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    length[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void dfs(int dist[], int u, int l) {
    if (l < dist[u]) {
        dist[u] = l;
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            dfs(dist, to[iter], l + length[iter]);
        }
    }
}


int main() {
    scanf("%d", &n);
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 2; i <= n; ++ i) {
        int p, c;
        scanf("%d%d", &p, &c);
        add_edge(p, i, c);
        add_edge(i, p, c);
    }
    for (int i = 1; i <= n; ++ i) {
        dist[0][i] = dist[1][i] = INT_MAX;
    }
    dfs(dist[0], 1, 0);
    int u = max_element(dist[0] + 1, dist[0] + 1 + n) - dist[0];
    dfs(dist[1], u, 0);
    int v = max_element(dist[1] + 1, dist[1] + 1 + n) - dist[1];
    for (int i = 1; i <= n; ++ i) {
        dist[0][i] = INT_MAX;
    }
    dfs(dist[0], v, 0);
    for (int i = 1; i <= n; ++ i) {
        printf("%d\n", max(dist[0][i], dist[1][i]));
    }
    return 0;
}
