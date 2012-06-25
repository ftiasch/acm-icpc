// SGU 183 -- Painting the balls
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 11111;
const int M = 111;
const int INF = 1000000000;

int n, m, cost[N], minimum[M][M], prefix_minimum[M][M];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", cost + i);
    }
    int result = INF;
    for (int i = n - 1; i >= 0; -- i) {
        prefix_minimum[i % M][0] = INF;
        for (int j = 1; j < m; ++ j) {
            if (i + j < n) {
                if (i + m >= n) {
                    minimum[i % M][j] = cost[i] + cost[i + j];
                } else {
                    minimum[i % M][j] = cost[i] + prefix_minimum[(i + j) % M][m - j];
                }
            } else {
                minimum[i % M][j] = INF;
            }
            if (i + j < m) {
                result = min(result, minimum[i % M][j]);
            }
            prefix_minimum[i % M][j] = min(prefix_minimum[i % M][j - 1], minimum[i % M][j]);
        }
    }
    printf("%d\n", result);
    return 0;
}
