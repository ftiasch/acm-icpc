// Codeforces Round #144
// Problem A -- Cycles
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100;

int m, n;
bool graph[N][N];

int main() {
    scanf("%d", &m);
    memset(graph, 0, sizeof(graph));
    n = 0;
    while (m) {
        int a = 2;
        int b = 1;
        graph[n][n + a] = true;
        while (b <= m) {
            m -= b;
            graph[n + b][n + a] = true;
        }
        n += a;
    }
    printf("%d\n", n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            printf("%d", graph[i][j] || graph[j][i] ? 1 : 0);
        }
        puts("");
    }
    return 0;
}
