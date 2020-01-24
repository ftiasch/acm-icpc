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

int count(int n, int p)
{
    int result = 0;
    while (n) {
        result += n /= p;
    }
    return result;
}

long long solve(int n, int k)
{
    auto primes = get_primes(n);
    auto result = 0LL;
    for (auto&& p : primes) {
        auto e = count(n, p) - count(k, p) - count(n - k, p);
        result += 1LL * p * e;
    }
    return result;
}

int main()
{
    printf("%lld\n", solve(10, 3));
    printf("%lld\n", solve(20000000, 15000000));
}
