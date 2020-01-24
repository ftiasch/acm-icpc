#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

const int MOD = 101 * 101 * 101 * 101;

const int N = 1000000;

int sigma[N + 1], mu[N + 1], mumu[N + 1];

int pow(int a, long long n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % MOD;
        }
        a = (long long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int solve(int g, long long l, long long n)
{
    int R = l / g;
    std::vector<int> p(R + 1), f(R + 1);
    for (int i = 1; i <= R; ++ i) {
        p[i] = pow(sigma[i], n);
    }
    for (int i = 1; i <= R; ++ i) {
        for (int j = 1; i * j <= R; ++ j) {
            update(f[i * j], (long long)p[i] * mumu[j] % MOD);
        }
    }
    int result = 0;
    for (int r = 1; r <= R; ++ r) {
        // g <= x, x * r <= l
        update(result, (long long)f[r] * ((l / r - g + 1) % MOD) % MOD);
    }
    return result;
}

int main()
{
    mu[1] = 1;
    for (int i = 1; i <= N; ++ i) {
        for (int j = i + i; j <= N; j += i) {
            update(mu[j], MOD - mu[i]);
        }
    }
    for (int i = 1; i <= N; ++ i) {
        for (int j = 1; i * j <= N; ++ j) {
            sigma[i * j] ++;
            update(mumu[i * j], (long long)mu[i] * mu[j] % MOD);
        }
    }
    printf("%d\n", solve(10, 100, 1));
    printf("%d\n", solve(10, 100, 2));
    printf("%d\n", solve(10, 100, 3));
    printf("%d\n", solve(10, 100, 1000));
    printf("%d\n", solve(10, 100, 1000));
    printf("%d\n", solve(1000000, 1000000000000LL, 1000000000000000000LL));
}
