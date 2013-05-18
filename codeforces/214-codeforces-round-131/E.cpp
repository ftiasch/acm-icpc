// Codeforces Round #131
// Problem E -- Relay Race
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 300;
const int INF = 1000000000;
const int DELTA[2][2] = {{0, 1}, {1, 0}};

int n, matrix[N][N], memory[N + N][N][N];

int solve(int x_1, int y_1, int x_2, int y_2) {
    int &ret = memory[x_1 + y_1][x_1][x_2];
    if (ret < -INF) {
        ret = -INF;
        for (int d_1 = 0; d_1 < 2; ++ d_1) {
            for (int d_2 = 0; d_2 < 2; ++ d_2) {
                int xx_1 = x_1 + DELTA[d_1][0];
                int yy_1 = y_1 + DELTA[d_1][1];
                int xx_2 = x_2 + DELTA[d_2][0];
                int yy_2 = y_2 + DELTA[d_2][1];
                if (xx_1 < n && yy_1 < n && xx_2 < n && yy_2 < n) {
                    ret = std::max(ret, solve(xx_1, yy_1, xx_2, yy_2));
                }
            }
        }
        ret += matrix[x_1][y_1];
        if (x_1 != x_2 || y_1 != y_2) {
            ret += matrix[x_2][y_2];
        }
    }
    return ret;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &matrix[i][j]);
        }
    }
    memset(memory, 128, sizeof(memory));
    memory[n - 1 << 1][n - 1][n - 1] = matrix[n - 1][n - 1];
    printf("%d\n", solve(0, 0, 0, 0));
    return 0;
}
