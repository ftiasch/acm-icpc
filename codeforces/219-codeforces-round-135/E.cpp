// Codeforces Beta Round #94
// Problem E -- Games with Rectangle
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1000;
const int MOD = 1000000000 + 7;

int n, m, k;
int binom[N + 1][(N << 1) + 1];

int main() {
    scanf("%d%d%d", &n, &m, &k);
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= N; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    printf("%d\n", (int)((long long)binom[n - 1][k << 1] * binom[m - 1][k << 1] % MOD));
    return 0;
}
