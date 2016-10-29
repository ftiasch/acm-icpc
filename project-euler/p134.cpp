#include <cassert>
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

int inverse(int a, int p)
{
    return a == 1 ? 1 : (long long)(p - p / a) * inverse(p % a, p) % p;
}

int main()
{
    auto primes = get_primes(1000003);
    auto ten = 10;
    auto result = 0LL;
    for (int i = 1; i < (int)primes.size(); ++ i) {
        auto p1 = primes.at(i - 1);
        auto p2 = primes.at(i);
        if (5 <= p1) {
            assert(p1 <= 1000000);
            while (ten <= p1) {
                ten *= 10;
            }
            auto prefix = (long long)(p2 - p1) * inverse(ten % p2, p2) % p2;
            result += prefix * ten + p1;
            // x * ten = -p1 (mod p2)
        }
    }
    printf("%lld\n", result);
}
