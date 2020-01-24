#include <algorithm>
#include <cstdio>
#include <vector>

long sum(long n, long d)
{
    n /= d;
    return (1 + n) * n / 2 * d;
}

long sum2(long n, long p, long q)
{
    return sum(n, p) + sum(n, q) - 2 * sum(n, p * q);
}

std::vector<int> get_primes(int n) {
    std::vector<bool> is_prime(n, true);
    std::vector<int> primes;
    for (auto d = 2; d < n; ++ d) {
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

long sqr(long x)
{
    return x * x;
}

bool is_prime(int n)
{
    for (auto d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

long solve(long n)
{
    int max_p = 1;
    while ((long)max_p * max_p <= n) {
        max_p ++;
    }
    auto primes = get_primes(max_p);
    auto last = primes.back() + 1;
    while (!is_prime(last)) {
        last ++;
    }
    primes.push_back(last);
    auto result = 0L;
    for (auto i = 1; i < (int)primes.size(); ++ i) {
        auto&& p1 = primes[i - 1];
        auto&& p2 = primes[i];
        auto a = sqr(p1);
        auto b = std::min(sqr(p2) - 1, n);
        // printf("%d %d %ld %ld\n", p1, p2, a, b);
        result += sum2(b, p1, p2) - sum2(a, p1, p2);
    }
    return result;
}

int main()
{
    printf("%ld\n", solve(15));
    printf("%ld\n", solve(1000));
    printf("%ld\n", solve(999966663333L));
}
