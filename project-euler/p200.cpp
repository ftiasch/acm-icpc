#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>

using LL = long long;

inline LL mul_mod(LL a, LL b, LL mod) {
    return __int128(a) * b % mod;
}

namespace zimpha
{
    LL pow_mod_LL(LL a, LL n, LL m) {
        LL res = 1;
        for (a %= m; n; n >>= 1) {
            if (n & 1) res = mul_mod(res, a, m);
            a = mul_mod(a, a, m);
        }
        return res;
    }

    // 用miller rabin素数测试判断n是否为质数
    bool is_prime(LL n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (~n & 1) return false;
        const int u[] = {2,3,5,7,325,9375,28178,450775,9780504,1795265022,0};
        LL e = n - 1, a, c = 0; // 原理：http://miller-rabin.appspot.com/
        while (~e & 1) e >>= 1, ++c;
        for (int i = 0; u[i]; ++i) {
            if (n <= u[i]) return true;
            a = pow_mod_LL(u[i], e, n);
            if (a == 1) continue;
            for (int j = 1; a != n - 1; ++j) {
                if (j == c) return false;
                a = mul_mod(a, a, n);
            }
        }
        return true;
    }
};

auto check_200(LL n)
{
    auto zeros = 0;
    while (n) {
        auto d = n % 10;
        n /= 10;
        if (d == 0) {
            zeros = std::min(zeros + 1, 2);
        } else {
            if (zeros == 2 && d == 2) {
                return true;
            }
            zeros = 0;
        }
    }
    return false;
}

auto check(LL n)
{
    if (!check_200(n)) {
        return false;
    }
    auto power = 1LL;
    while (power <= n) {
        auto tmp = n - (n / power % 10) * power;
        for (auto d = 0; d < 10; ++ d) {
            if (zimpha::is_prime(tmp)) {
                return false;
            }
            tmp += power;
        }
        if (n % 2 == 0 || n % 5 == 0) {
            break;
        }
        power *= 10;
    }
    return true;
}

auto get_primes(int n) {
    std::vector<bool> is_prime(n, true);
    std::vector<int> primes;
    for (int d = 2; d < n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p >= n) {
                break;
            }
            is_prime[d * p] = false;
            if (d % p == 0) {
                break;
            }
        }
    }
    return primes;
}

auto count(LL n, int m)
{
    auto primes = get_primes((int)sqrt(n) + 1);
    for (auto i = 0; i < (int)primes.size() && 1LL * primes[i] * primes[i] * primes[i] <= n; ++ i) {
        for (auto j = 0; j < (int)primes.size() && 1LL * primes[i] * primes[i] * primes[i] * primes[j] * primes[j] <= n; ++ j) {
            if (i == j) {
                continue;
            }
            m -= check(1LL * primes[i] * primes[i] * primes[i] * primes[j] * primes[j]);
            if (!m) {
                return true;
            }
        }
    }
    return false;
}

int main()
{
    printf("%d\n", check(1992008));
    const auto N = 200;
    auto low = 1LL; // 1992008LL;
    auto high = low;
    while (!count(high, N)) {
        high *= 2;
    }
    while (low < high) {
        auto middle = low + high >> 1;
        if (count(middle, N)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    printf("%lld\n", high);
}
