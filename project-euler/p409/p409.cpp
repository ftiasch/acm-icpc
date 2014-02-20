// Project Euler 409 -- Nim Extreme
#include <iostream>

const int MOD = (int)1e9 + 7;

const int N = 10000000;

int falling[N + 1], ways[N + 1];

int solve(int n)
{
    int seed = 1;
    for (int i = 0; i < n; ++ i) {
        (seed *= 2) %= MOD;
    }
    (seed += MOD - 1) %= MOD;
    falling[0] = 1;
    for (int i = 0; i < n; ++ i) {
        falling[i + 1] = (long long)falling[i] * (seed - i) % MOD;
    }
    ways[0] = 1;
    ways[1] = 0;
    for (int i = 2; i <= n; ++ i) {
        ways[i] = ((falling[i - 1] + MOD - ways[i - 1]) % MOD + MOD - (long long)ways[i - 2] * (i - 1) % MOD * (seed - i + 2) % MOD) % MOD;
    }
    int all = 1;
    for (int i = 0; i < n; ++ i) {
        all = (long long)all * (seed - i) % MOD;
    }
    return (all + MOD - ways[n]) % MOD;
}

int main()
{
    std::cout << solve(100) << std::endl;
    std::cout << solve(10000000) << std::endl;
}
