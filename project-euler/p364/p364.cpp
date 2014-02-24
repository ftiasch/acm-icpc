#include <cassert>
#include <cstdio>

const int MOD = (int)1e8 + 7;
const int N = 1000000;

typedef long long Long;

int pow[N + 1], fact[N + 1], invFact[N + 1];

int binom(int n, int k)
{
    return (Long)fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
}

int solve(int n)
{
    int result = 0;
    for (int mask = 0; mask < 1 << 2; ++ mask) {
        int n2 = n - __builtin_popcount(mask);
        for (int k = 2; k <= n2; ++ k) {
            int two = n2 - k - k + 1;
            int one = k - 1 - two;
            if (one >= 0 && two >= 0) {
                assert(one + 2 * two + k == n2);
                assert(one + two == k - 1);
                (result += (Long)fact[k]
                         * binom(one + two, one) % MOD
                         * pow[two] % MOD * fact[two + __builtin_popcount(mask)] % MOD
                         * fact[one + two] % MOD) %= MOD;
            }
        }
    }
    return result;
}

int main()
{
    invFact[1] = 1;
    for (int i = 2; i <= N; ++ i) {
        invFact[i] = (Long)(MOD - MOD / i) * invFact[MOD % i] % MOD;
    }
    pow[0] = fact[0] = invFact[0] = 1;
    for (int i = 1; i <= N; ++ i) {
        pow[i] = pow[i - 1] * 2 % MOD;
        fact[i] = (Long)fact[i - 1] * i % MOD;
        invFact[i] = (Long)invFact[i] * invFact[i - 1] % MOD;
    }
    printf("%d\n", solve(4));
    printf("%d\n", solve(10));
    printf("%d\n", solve(1000));
    printf("%d\n", solve(1000000));
}
