#include <cstdio>
#include <vector>

// 2 + 3 + 5 + sum_{5 < prime p < 10^5} p * [forall n >= 1, 10^n mod ord_p(10) != 0]

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

int main()
{
    int sum = 2 + 3 + 5;
    for (auto&& p : get_primes(100000)) {
        if (p > 5) {
            int tmp = p - 1;
            int ord = 1;
            for (auto&& q : std::vector<int>{2, 5}) {
                while (tmp % q == 0) {
                    ord *= q;
                    tmp /= q;
                }
            }
            if (power(10, ord, p) != 1) {
                sum += p;
            } else {
                // printf("%d\n", p);
            }
        }
    }
    printf("%d\n", sum);
}
