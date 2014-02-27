#include <cstdio>
#include <cstring>

const int N = 1000000;
const int MOD = (int)1e9 + 7;
const int INV_TWO = (int)5e8 + 4;

int n, p[N], count[N], fact[N + 1];

int main()
{
    scanf("%d", &n);
    fact[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        fact[i] = (long long)fact[i - 1] * i % MOD;
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", p + i);
        p[i] --;
    }
    int result = 0;
    int total = 0;
    memset(count, 0, sizeof(count));
    for (int i = 0; i < n; ++ i) {
        int smaller = p[i];
        for (int k = p[i]; k >= 0; k -= ~k & k + 1) {
            smaller -= count[k];
        }
        (result += ((long long)total * smaller % MOD + (long long)smaller * (smaller - 1) / 2 % MOD) * fact[n - 1 - i] % MOD) %= MOD;
        (result += (long long)(n - 1 - i) * (n - 2 - i) / 2 % MOD * fact[n - 1 - i] % MOD * INV_TWO % MOD * smaller % MOD) %= MOD;
        (total += smaller) %= MOD;
        for (int k = p[i]; k < n; k += ~k & k + 1) {
            count[k] ++;
        }
    }
    (result += total) %= MOD;
    printf("%d\n", result);
    return 0;
}
