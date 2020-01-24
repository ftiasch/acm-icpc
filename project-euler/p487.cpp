#include <cassert>
#include <cstdio>
#include <vector>

int is_prime(int n)
{
    for (int d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

int polynomial_interpolation(std::vector<int> values, long long n, int mod)
{
    auto residue = [mod](long long n) {
        return (n % mod + mod) % mod;
    };
    int d = (int)(values.size()) - 1;
    std::vector<long long> inverse(std::max(2, d + 1));
    inverse.at(1) = 1;
    for (int i = 2; i <= d; ++ i) {
        inverse.at(i) = (long long)(mod - mod / i) * inverse.at(mod % i) % mod;
    }
    long long b = 1;
    for (int i = 1; i <= d; ++ i) {
        b = b * residue(n - i + 1) % mod * inverse.at(i) % mod;
        values.at(i) = b * values.at(i) % mod;
    }
    b = 1;
    long long result = values.at(d);
    for (int i = d - 1; i >= 0; -- i) {
        b = b * residue(-1) % mod * inverse.at(d - i) % mod * residue(n - i - 1) % mod;
        result += values.at(i) * b % mod;
        if (result >= mod) {
            result -= mod;
        }
    }
    return result;
}

int power(int a, int n, int mod)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % mod;
        }
        a = (long long)a * a % mod;
        n >>= 1;
    }
    return result;
}

int get_sum(int k, long long n, int mod)
{
    // k + 3
    std::vector<int> values(k + 3);
    long long s1 = 0;
    long long s2 = 0;
    for (int i = 1; i < k + 3; ++ i) {
        auto pw = power(i, k, mod);
        (s1 += pw) %= mod;
        (s2 += s1) %= mod;
        values[i] = s2;
    }
    return polynomial_interpolation(values, n, mod);
}

int main()
{
    printf("%d\n", get_sum(4, 100, 1000000007));
    long long result = 0;
    for (int p = 2000000000; p <= 2000002000; ++ p) {
        if (is_prime(p)) {
            auto q = get_sum(10000, 1000000000000LL, p);
            assert(q >= 0);
            result += q;
        }
    }
    printf("%lld\n", result);
}
