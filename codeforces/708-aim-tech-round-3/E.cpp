#include <cstdio>
#include <cstring>
#include <functional>
#include <vector>

const int MOD = (int)1e9 + 7;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int power(int a, int n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = 1LL * result * a % MOD;
        }
        a = 1LL * a * a % MOD;
        n >>= 1;
    }
    return result;
}

std::vector<int> partial_sum(int n, std::function<int(int)> f)
{
    std::vector<int> s(n + 1);
    for (int i = 0; i < n; ++ i) {
        s[i + 1] = (s[i] + f(i)) % MOD;
    }
    return s;
}

int main()
{
    // freopen("E.in", "r", stdin);
    int n, m, a, b, l;
    scanf("%d%d%d%d%d", &n, &m, &a, &b, &l);
    int p = static_cast<long long>(a) * power(b, MOD - 2) % MOD;
    std::vector<int> fact(l + 1);
    fact[0] = 1;
    for (int i = 1; i <= l; ++ i) {
        fact[i] = 1LL * fact[i - 1] * i % MOD;
    }
    std::vector<int> prob(m);
    for (int i = 0; i < m && i <= l; ++ i) {
        prob[i] = 1LL * fact[l] * power(1LL * fact[i] * fact[l - i] % MOD, MOD - 2) % MOD * power(p, i) % MOD * power(1 + MOD - p, l - i) % MOD;
        //printf("%d: %d\n", i, prob[i]);
    }
    auto prefix = partial_sum(m, [&](int i) { return prob[i]; });
    auto ways = partial_sum(m, [&](int i) { return 1LL * prob[i] * prefix[m - i] % MOD; });
    for (int _ = 1; _ < n; ++ _) {
        auto tmp_sum = partial_sum(m, [&](int i) { return 1LL * prob[i] * ways[m - i] % MOD; });
        ways = partial_sum(m, [&](int i)
        {
            return 1LL * prob[i] * (1LL * (ways[m - i] + MOD - ways[m]) * prefix[m - i] % MOD + tmp_sum[m - i]) % MOD;
        });
    }
    printf("%d\n", ways[m]);
}
