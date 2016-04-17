#include <vector>

namespace pe
{
    int polynomial_interpolation(std::vector<int> values, long long n, int mod)
    {
        auto residue = [mod](long long n) {
            return (n % mod + mod) % mod;
        };
        int d = (int)(values.size()) - 1;
        std::vector<int> inverse(std::max(2, d + 1));
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
        int result = values.at(d);
        for (int i = d - 1; i >= 0; -- i) {
            b = b * residue(-1) % mod * inverse.at(d - i) % mod * residue(n - i - 1) % mod;
            result += values.at(i) * b % mod;
            if (result >= mod) {
                result -= mod;
            }
        }
        return result;
    }

    std::vector<int> get_primes(int n) {
        std::vector<bool> is_prime(n + 1, true);
        std::vector<int> primes;
        for (int d = 2; d <= n; ++ d) {
            if (is_prime[d]) {
                primes.push_back(d);
            }
            for (auto&& &p : primes) {
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
}
