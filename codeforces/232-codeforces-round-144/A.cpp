#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100;

int m;
bool graph[N][N];

int main() {
    scanf("%d", &m);
    memset(graph, 0, sizeof(graph));
#define ADD(a, b) graph[a][b] = graph[b][a] = true
    int n = 0;
    int i = 0;
    while ((i + 1) * i * (i - 1) <= 6 * m) {
        i ++;
    }
    m -= i * (i - 1) * (i - 2) / 6;
    for (int x = 0; x < i; ++ x) {
        for (int y = x + 1; y < i; ++ y) {
            ADD(x, y);
        }
    }
    n += i;
    while (m) {
        int j = 0;
        while ((j + 1) * j / 2 <= m) {
            j ++;
        }
        m -= j * (j - 1) / 2;
        for (int k = 0; k < j; ++ k) {
            ADD(k, n);
        }
        n ++;
    }
#undef ADD
    printf("%d\n", n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            printf("%d", (int)graph[i][j]);
        }
        puts("");
    }
    return 0;
}
