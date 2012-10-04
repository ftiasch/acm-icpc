// Codeforces Round #138 
// Problem C -- Partial Sums
#include <cstdio>
#include <cstring>
using namespace std;

const int MOD = 1000000000 + 7;

const int N = 2000;

int n, m, a[N], b[N], inverse[N + 1];

int main() {
    scanf("%d%d", &n, &m);
    inverse[1] = 1;
    for (int i = 2; i <= n; ++ i) {
        inverse[i] = (long long)(MOD - MOD / i) * inverse[MOD % i] % MOD;
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    memset(b, 0, sizeof(b));
    if (m == 0) {
        for (int i = 0; i < n; ++ i) {
            b[i] = a[i];
        }
    } else {
        m --;
        for (int i = 0; i < n; ++ i) {
            long long coefficient = 1;
            for (int j = 0; i + j < n; ++ j) {
                if (j > 0) {
                    coefficient = coefficient * (m + j) % MOD;
                    coefficient = coefficient * inverse[j] % MOD;
                }
                b[i + j] += coefficient * a[i] % MOD;
                b[i + j] %= MOD;
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", b[i], i == n - 1? '\n': ' ');
    }
    return 0;
}
