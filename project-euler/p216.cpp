#include <cstdio>
#include <vector>

using LL = long long;

namespace zimpha
{
    // mod should be not greater than 4e18
    inline LL mul_mod(LL a, LL b, LL mod) {
        return (__int128)a * b % mod;
    }

    inline LL add_mod(LL x, LL y) {
        return (x % y + y) % y;
    }

    LL pow_mod(LL a, LL n, LL m) {
        LL res = 1;
        for (a %= m; n; n >>= 1) {
            if (n & 1) res = res * a % m;
            a = a * a % m;
        }
        return res;
    }

    // 适合m超过int的时候
    // 条件允许的话使用__int128会比较快
    LL pow_mod_LL(LL a, LL n, LL m) {
        LL res = 1;
        for (a %= m; n; n >>= 1) {
            if (n & 1) res = mul_mod(res, a, m);
            a = mul_mod(a, a, m);
        }
        return res;
    }

    LL ToneLLi_Shanks(LL n, LL p) {
        if (p == 2) return (n & 1) ? 1 : -1;
        if (pow_mod(n, p >> 1, p) != 1) return -1;
        if (p & 2) return pow_mod(n, p + 1 >> 2, p);
        int s = __builtin_ctzll(p ^ 1);
        LL q = p >> s, z = 2;
        for (; pow_mod(z, p >> 1, p) == 1; ++z);
        LL c = pow_mod(z, q, p);
        LL r = pow_mod(n, q + 1 >> 1, p);
        LL t = pow_mod(n, q, p), tmp;
        for (int m = s, i; t != 1;) {
            for (i = 0, tmp = t; tmp != 1; ++i) tmp = tmp * tmp % p;
            for (; i < --m;) c = c * c % p;
            r = r * c % p;
            c = c * c % p;
            t = t * c % p;
        }
        return r;
    }
}

std::vector<int> get_primes(int n) {
    std::vector<bool> is_prime(n + 1, true);
    std::vector<int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p > n) {
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

int inverse(int a, int p)
{
    return a == 1 ? 1 : 1LL * (p - p / a) * inverse(p % a, p) % p;
}

int solve(int n)
{
    int r = 0;
    while (1LL * r * r <= 2LL * n * n - 1) {
        r ++;
    }
    r --;
    auto primes = get_primes(r);
    std::vector<bool> valid(n + 1, true);
    auto mark = [&](int r, int p)
    {
        while (r <= n && 2LL * r * r - 1 <= p) {
            r += p;
        }
        for (; r <= n; r += p) {
            valid[r] = false;
        }
    };
    for (auto&& p : primes) {
        if (p == 2) {
            continue;
        }
        auto r = zimpha::ToneLLi_Shanks(inverse(2, p), p);
        if (~r) {
            mark(r, p);
            mark(p - r, p);
        }
    }
    auto result = 0;
    for (auto i = 2; i <= n; ++ i) {
        result += valid[i];
    }
    return result;
}

int main()
{
    printf("%d\n", solve(10000));
    printf("%d\n", solve(50000000));
}
