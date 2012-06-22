// SGU 172 -- eXam
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 200;

int n, m, color[N];
bool graph[N][N], fail;

void dfs(int i, int c) {
    if (color[i] == -1) {
        color[i] = c;
        for (int j = 0; j < n; ++ j) {
            if (graph[i][j]) {
                dfs(j, c ^ 1);
            }
        }
    } else {
        fail |= color[i] != c;
    }
}

int main() {
    scanf("%d%d", &n, &m);
    memset(graph, 0, sizeof(graph));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        graph[a - 1][b - 1] = graph[b - 1][a - 1] = true;
    }
    fail = false;
    memset(color, -1, sizeof(color));
    for (int i = 0; i < n; ++ i) {
        if (color[i] == -1) {
            dfs(i, 0);
        }
    }
    if (fail) {
        puts("no");
    } else {
        puts("yes");
        int count = 0;
        for (int i = 0; i < n; ++ i) {
            if (color[i] == 0) {
                count ++;
            }
        }
        printf("%d\n", count);
        for (int i = 0; i < n; ++ i) {
            if (color[i] == 0) {
                printf("%d ", i + 1);
            }
        }
        puts("");
    }
    return 0;
}
