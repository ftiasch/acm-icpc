#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1111;
const int MOD = 1000000007;

void increase(int &x, int a) {
    x += a;
    x %= MOD;
}

int tmp_ways[N][N];

void count_ways(int ways[], int n, int k, int x) {
    memset(tmp_ways, 0, sizeof(tmp_ways));
    tmp_ways[0][x] = 1;
    for (int i = 0; i < k; ++ i) {
        for (int j = 1; j <= n; ++ j) {
            if (j > 1) {
                increase(tmp_ways[i + 1][j - 1], tmp_ways[i][j]);
            }
            if (j < n) {
                increase(tmp_ways[i + 1][j + 1], tmp_ways[i][j]);
            }
        }
    }
    memset(ways, 0, sizeof(ways));
    for (int i = 0; i <= k; ++ i) {
        for (int j = 1; j <= n; ++ j) {
            increase(ways[i], tmp_ways[i][j]);
        }
    }
}

int ways[2][N], comb[N][N];

int main() {
    int n, m, k, x, y;
    scanf("%d%d%d%d%d", &n, &m, &k, &x, &y);
    count_ways(ways[0], n, k, x);
    count_ways(ways[1], m, k, y);
    memset(comb, 0, sizeof(comb));
    for (int i = 0; i <= k; ++ i) {
        comb[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
        }
    }
    int result = 0;
    for (int i = 0; i <= k; ++ i) {
        increase(result, (long long)comb[k][i] * ways[0][i] % MOD * ways[1][k - i] % MOD);
    }
    printf("%d\n", result);
    return 0;
}
