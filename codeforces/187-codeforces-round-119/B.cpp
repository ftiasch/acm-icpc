// Codeforces Round #119
// Problem B -- AlgoRace
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 66;
const int INF = 1000000000;

int n, m, r, weight[N][N], graph[N][N][N];

int main() {
    scanf("%d%d%d", &n, &m, &r);
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= n; ++ j) {
            graph[0][i][j] = i == j? 0: INF;
        }
    }
    while (m > 0) {
        m --;
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= n; ++ j) {
                scanf("%d", &weight[i][j]);
            }
        }
        for (int k = 1; k <= n; ++ k) {
            for (int i = 1; i <= n; ++ i) {
                for (int j = 1; j <= n; ++ j) {
                    weight[i][j] = min(weight[i][j], weight[i][k] + weight[k][j]);
                }
            }
        }
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= n; ++ j) {
                graph[0][i][j] = min(graph[0][i][j], weight[i][j]);
            }
        }
    }
    //for (int i = 1; i <= n; ++ i) {
    //    for (int j = 1; j <= n; ++ j) {
    //        printf("%d, ", graph[0][i][j]);
    //    }
    //    puts("");
    //}
    for (int p = 1; p <= n; ++ p) {
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= n; ++ j) {
                graph[p][i][j] = graph[p - 1][i][j];
                for (int k = 1; k <= n; ++ k) {
                    graph[p][i][j] = min(graph[p][i][j], \
                            graph[p - 1][i][k] + graph[0][k][j]);
                }
            }
        }
    }
    while (r > 0) {
        r --;
        int s, t, k;
        scanf("%d%d%d", &s, &t, &k);
        k = min(k, n);
        printf("%d\n", graph[k][s][t]);
    }
    return 0;
}
