#include <cassert>
#include <cstdio>
#include <vector>

typedef long long Long;

const int MOD = (int)1e9 + 7;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int power(int a, int n, int mod)
{
    assert(n >= 0);
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (Long)result * a % mod;
        }
        a = (Long)a * a % mod;
        n >>= 1;
    }
    return result;
}

int C(int n, int m)
{
    std::vector<int> r(n + 1);
    for (int i = 0; i <= n; ++ i) {
        r[i] = power(2, power(2, i, MOD - 1) - 1, MOD) - 1;
    }
    std::vector<int> covered(n + 1);
    {
        std::vector<int> binom(n + 1);
        binom[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            for (int j = i; j >= 1; -- j) {
                update(binom[j], binom[j - 1]);
            }
            covered[i] = r[i];
            for (int j = 1; j < i; ++ j) {
                update(covered[i], MOD - (Long)covered[j] * binom[j] % MOD);
            }
        }
    }
    std::vector<int> connected(n + 1);
    {
        std::vector<int> binom(n + 1);
        binom[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            connected[i] = covered[i];
            for (int j = 1; j < i; ++ j) {
                update(connected[i], MOD - (Long)binom[j - 1] * connected[j] % MOD * covered[i - j] % MOD);
            }
            for (int j = i; j >= 1; -- j) {
                update(binom[j], binom[j - 1]);
            }
            // for (int j = 1; j < i; ++ j) {
            //     update(connected[i], MOD - (Long)binom[j] * connected[j] % MOD);
            // }
        }
    }
    std::vector<int> ways(n + 1, 1);
    for (int _ = 0; _ < m; ++ _) {
        std::vector<int> new_ways(n + 1);
        std::vector<int> binom(n + 1);
        binom[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            new_ways[i] = new_ways[i - 1];
            for (int j = 1; j <= i; ++ j) {
                update(new_ways[i], (Long)binom[j - 1] * connected[j] % MOD * ways[i - j] % MOD);
            }
            for (int j = i; j >= 1; -- j) {
                update(binom[j], binom[j - 1]);
            }
        }
        ways.swap(new_ways);
    }
    return ways[n];
}

int main()
{
    printf("%d\n", C(2, 1));
    printf("%d\n", C(3, 1));
    printf("%d\n", C(4, 2));
    printf("%d\n", C(100, 10));
    printf("%d\n", C(10000, 10));
}
