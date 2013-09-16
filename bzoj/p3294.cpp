#include <cstdio>
#include <cstring>

const int K = 10;
const int N = 30;
const int MOD = (int)1e9 + 9;

int n, m, k, binom[N * N + 1][N * N + 1], iways[K][N + 1][N + 1], tways[K + 1][N + 1][N + 1];

void solvei(int count, int ways[N + 1][N + 1]) {
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= m; ++ j) {
            ways[i][j] = binom[i * j][count];
            for (int x = 0; x <= i; ++ x) {
                for (int y = 0; y <= j; ++ y) {
                    if (x + y > 0) {
                        (ways[i][j] += MOD - (long long)ways[i - x][j - y] * binom[i][x] % MOD * binom[j][y] % MOD) %= MOD;
                    }
                }
            }
        }
    }
}

int main() {
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= N * N; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    scanf("%d%d%d", &n, &m, &k);
    memset(iways, 0, sizeof(iways));
    for (int i = 0; i < k; ++ i) {
        int count;
        scanf("%d", &count);
        solvei(count, iways[i]);
    }
    memset(tways, 0, sizeof(tways));
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= m; ++ j) {
            tways[0][i][j] = 1;
        }
    }
    for (int p = 0; p < k; ++ p) {
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                for (int x = 0; i + x <= n; ++ x) {
                    for (int y = 0; j + y <= m; ++ y) {
                        (tways[p + 1][i + x][j + y] += (long long)tways[p][i][j] * iways[p][x][y] % MOD * binom[i + x][x] % MOD * binom[j + y][y] % MOD) %= MOD;
                    }
                }
            }
        }
    }
    printf("%d\n", tways[k][n][m]);
    return 0;
}
