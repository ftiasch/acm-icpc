//     n^3 + p * n^2 = (n + d)^3 = n^3 + 3 d n^2 + 3 d^2 n + d^3
// => 3d <= p
//  => (2p - 3d) * n^2 
//      (p - d)^2 + (p - d) sqrt{p^2 + 2dp - 3d^2}
//  n = -----------------------------------------
//                     2 * d
#include <cmath>
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

bool check(int p, int d)
{
    auto delta = d * (4LL * p - 3LL* d);
    auto r = static_cast<long long>(sqrt(delta));
    if (r * r != delta) {
        return false;
    }
    return d * (3LL * d + r) % (2LL * (p - 3LL * d)) == 0;
}

int main()
{
    int count = 0;
    for (auto&& p : get_primes(1000000)) {
        int d = 1;
        while (d + d + d < p && !check(p, d)) {
            d ++;
        }
        if (d + d + d < p) {
            count ++;
        }
    }
    printf("%d\n", count);
}
