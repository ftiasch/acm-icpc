// VK Cup 2012 Round 1
// Problem D -- Distance in Tree
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 111111;
const int M = 555;

int n, m, edge_count, first_edge[N], to[N], next_edge[N], parent[N], depth_count[N][M];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void dfs(int p, int u) {
    parent[u] = p;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            dfs(u, v);
        }
    }
}

void dfs(int depth_count[], int u, int d) {
    depth_count[d] ++;
    if (d < m) {
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
            if (v != parent[u]) {
                dfs(depth_count, v, d + 1);
            }
        }
    }
}

long long count_pairs(int u, int m) {
    long long result = 0;
    for (int i = 0; i <= m; ++ i) {
        long long a = depth_count[u][i];
        long long b = depth_count[u][m - i];
        if (i == m - i) {
            result += a * (b - 1);
        } else {
            result += a * b;
        }
    }
    return result >> 1;
}

int main() {
    scanf("%d%d", &n, &m);
    if (m == 1) {
        cout << n - 1 << endl;
        return 0;
    }
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        add_edge(a, b);
        add_edge(b, a);
    }
    dfs(-1, 0);
    memset(depth_count, 0, sizeof(depth_count));
    for (int i = 0; i < n; ++ i) {
        dfs(depth_count[i], i, 0);
    }
    long long result = 0;
    for (int u = 0; u < n; ++ u) {
        result += count_pairs(u, m);
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
            if (v != parent[u]) {
                result -= count_pairs(v, m - 2);
            }
        }
    }
    cout << result << endl;
    return 0;
}
