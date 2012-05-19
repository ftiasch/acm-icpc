// Croc Champ 2012 Final
// Problem C -- Cyclic Coloring
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 200000;

int n, m, first_edge[N], to[N], next_edge[N], depth[N], result;
bool visit[N];

void dfs(int u, int d) {
    if (visit[u]) {
        result = __gcd(result, abs(d - depth[u]));
    } else {
        visit[u] = true;
        depth[u] = d;
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
            int w = iter & 1? 1: -1;
            dfs(v, d + w);
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        to[i << 1] = b;
        next_edge[i << 1] = first_edge[a];
        first_edge[a] = i << 1;
        to[(i << 1) | 1] = a;
        next_edge[(i << 1) | 1] = first_edge[b];
        first_edge[b] = (i << 1) | 1;
    }
    memset(visit, 0, sizeof(visit));
    result = 0;
    for (int i = 0; i < n; ++ i) {
        if (!visit[i]) {
            dfs(i, 0);
        }
    }
    printf("%d\n", result == 0? n: result);
    return 0;
}
