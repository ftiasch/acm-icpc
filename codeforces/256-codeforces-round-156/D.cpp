// Codeforces Round #156
// Problem D -- Liars and Serge
#include <cstdio>
#include <cstring>

const int N = 1 << 8;
const int MOD = 777777777;

int n, m, binom[N + 1][N + 1], ways[N + 1][N + 1], new_ways[N + 1][N + 1];

void increase(int &x, int a) {
    x += a;
    x %= MOD;
}

int main() {
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= N; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j] + binom[i - 1][j - 1]) % MOD;
        }
    }
    scanf("%d%d", &n, &m);
    memset(ways, 0, sizeof(ways));
    ways[0][0] = 1;
    for (int index = 1; index <= n; ++ index) {
        memset(new_ways, 0, sizeof(new_ways));
        for (int total = 0; total <= n; ++ total) {
            for (int liar = 0; liar <= total; ++ liar) {
                if (ways[total][liar] != 0) {
                    for (int size = 0; total + size <= n; ++ size) {
                        increase(new_ways[total + size][liar + (index == size ? 0 : size)],
                                (long long)ways[total][liar] * binom[n - total][size] % MOD);
                    }
                }
            }
        }
        for (int total = 0; total <= n; ++ total) {
            for (int liar = 0; liar <= n; ++ liar) {
                ways[total][liar] = new_ways[total][liar];
            }
        }
    }
    printf("%d\n", ways[n][m]);
    return 0;
}
