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

const int N = 100000000;

int main()
{
    auto primes = get_primes(N / 2);
    auto result = 0;
    for (int i = 0; i < (int)primes.size(); ++ i) {
        for (int j = i; j < (int)primes.size() && 1LL * primes[i] * primes[j] <= N; ++ j) {
            result ++;
        }
    }
    printf("%d\n", result);
}
