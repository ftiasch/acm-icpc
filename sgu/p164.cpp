// SGU 164 -- Airlines
#include <cstdio>
#include <cstring>
#include <utility>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 200;
const int INF = 1000000000;

int n, m, color[N][N], graph[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &color[i][j]);
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            graph[i][j] = INF;
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (i != j && color[i][j] <= (m + 1) / 2) {
                graph[i][j] = 1;
            }
        }
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
            }
        }
    }
    bool check = true;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            check &= graph[i][j] <= 3;
        }
    }
    if (check) {
        printf("%d\n", (m + 1) / 2);
        for (int i = 1; i <= (m + 1) / 2; ++ i) {
            printf("%d ", i);
        }
        puts("");
    } else {
        printf("%d\n", m - (m + 1) / 2);
        for (int i = (m + 1) / 2 + 1; i <= m; ++ i) {
            printf("%d ", i);
        }
        puts("");
    }
    return 0;
}
