// SGU 286 -- Ancient decoration
#include <cassert>
#include <cstdio>
#include <cstring>

const int N = 300;

int n, d, edge[N][N], graph[N][N], match[N];
bool visit[N];

void dfs(int u) {
    for (int v = 0; v < n; ++ v) {
        if (edge[u][v] != -1) {
            graph[u][v] = edge[u][v];
            edge[u][v] = edge[v][u] = -1;
            dfs(v);
        }
    }
}

bool find(int u) {
    if (visit[u]) {
        return false;
    }
    visit[u] = true;
    for (int v = 0; v < n; ++ v) {
        if (graph[u][v] != -1 && (match[v] == -1 || find(match[v]))) {
            match[v] = u;
            return true;
        }
    }
    return false;
}

int main() {
    scanf("%d%d", &n, &d);
    memset(edge, -1, sizeof(edge));
    for (int i = 0; i < n * d / 2; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        edge[a][b] = edge[b][a] = i;
    }
    memset(graph, -1, sizeof(graph));
    for (int i = 0; i < n; ++ i) {
        dfs(i);
    }
    memset(match, -1, sizeof(match));
    for (int i = 0; i < n; ++ i) {
        memset(visit, 0, sizeof(visit));
        assert(find(i));
    }
    puts("YES");
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", 1 + graph[match[i]][i]);
    }
    return 0;
}
