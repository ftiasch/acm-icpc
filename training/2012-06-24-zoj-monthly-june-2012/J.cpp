// Problem J -- Escape Time II
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int INF = 1111111;
const int N = 10;

int n, m, limit, source, target, weight[N], graph[N][N], sum[1 << N], minimum[1 << N][N];

void update(int &x, int a) {
    x = min(x, a);
}

int main() {
    while (scanf("%d%d%d", &n, &m, &limit) == 3) {
        scanf("%d%d", &source, &target);
        for (int i = 0; i < n; ++ i) {
            scanf("%d", weight + i);
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = INF;
            }
            graph[i][i] = 0;
        }
        for (int i = 0; i < m; ++ i) {
            int a, b, c;
            scanf("%d%d%d", &a, &b, &c);
            graph[a][b] = graph[b][a] = min(graph[a][b], c);
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] = min(graph[i][j], graph[i][k]+ graph[k][j]);
                }
            }
        }
        memset(sum, 0, sizeof(sum));
        for (int i = 0; i < n; ++ i) {
            sum[1 << i] = weight[i];
        }
        for (int mask = 1; mask < (1 << n); ++ mask) {
            if (((mask - 1) & mask) != 0) {
                int sub = (mask - 1) & mask;
                sum[mask] = sum[sub] + sum[mask ^ sub];
            }
        }
        for (int mask = 0; mask < (1 << n); ++ mask) {
            for (int i = 0; i < n; ++ i) {
                minimum[mask][i] = INF;
            }
        }
        minimum[1 << source][source] = 0;
        for (int mask = 0; mask < (1 << n); ++ mask) {
            for (int i = 0; i < n; ++ i) {
                if ((mask >> i & 1) == 1) {
                    for (int j = 0; j < n; ++ j) {
                        if ((mask >> j & 1) == 0) {
                            update(minimum[mask | (1 << j)][j], minimum[mask][i] + graph[i][j]);
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int mask = 0; mask < (1 << n); ++ mask) {
            if (minimum[mask][target] <= limit) {
                result = max(result, sum[mask]);
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
