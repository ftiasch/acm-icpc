#include <cstdio>
#include <vector>

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

int inv(int a, int p)
{
    return a == 1 ? 1 : (long long)(p - p / a) * inv(p % a, p) % p;
}

long long solve(int n)
{
    auto primes = get_primes(n + 1);
    n = primes.size();
    std::vector<int> c(n);
    long long result = 0;
    for (int i = 0; i < n; ++ i) {
        auto&& p = primes.at(i);
        int m = 1;
        int r = 0;
        bool ok = false;
        for (int j = 0; j < i; ++ j) {
            r = ((long long)m * c[j] + r) % p;
            ok |= r == 0;
            m = (long long)m * primes[j] % p;
        }
        c[i] = ((long long)(i + 1 - r) * inv(m, p) % p + p) % p;
        if (ok) {
            result += p;
        }
    }
    return result;
}

int main()
{
    printf("%lld\n", solve(50));
    printf("%lld\n", solve(300000));
}
