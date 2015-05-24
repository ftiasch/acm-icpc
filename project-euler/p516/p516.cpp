#include <cstdio>
#include <vector>

typedef long long Long;

bool is_prime(Long n)
{
    for (int i = 2; (Long)i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

std::vector <Long> gen_primes(Long n)
{
    std::vector <Long> result;
    for (Long p2 = 1; p2 < n; p2 *= 2) {
        for (Long p3 = 1; p2 * p3 < n; p3 *= 3) {
            for (Long p5 = 1; p2 * p3 * p5 < n; p5 *= 5) {
                if (is_prime(p2 * p3 * p5 + 1)) {
                    result.push_back(p2 * p3 * p5 + 1);
                }
            }
        }
    }
    std::sort(result.begin(), result.end());
    return result;
}

unsigned search(std::vector <Long> const &primes, int k, Long n, unsigned pd)
{
    if (~k) {
        Long p = primes[k];
        unsigned result = search(primes, k - 1, n, pd);
        if (p <= n) {
            result += search(primes, k - (p > 5), n / p, pd * p);
        }
        return result;
    } else {
        return pd;
    }
}

unsigned solve(Long n)
{
    std::vector <Long> primes(gen_primes(n));
    return search(primes, (int)primes.size() - 1, n, 1U);
}

int main()
{
    printf("%u\n", solve(1000000000000));
    return 0;
}
