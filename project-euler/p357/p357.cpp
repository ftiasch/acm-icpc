// Project Euler 357 -- Prime generating integers
#include <cstdio>
#include <bitset>
#include <iostream>
#include <vector>

const int N = 100000001;

typedef std::bitset <N + 1> Bitset;

Bitset is_prime;
std::vector <int> primes;

int main()
{
    is_prime.flip();
    for (int i = 2; i <= N; ++ i) {
        if (is_prime.test(i)) {
            primes.push_back(i);
        }
        for (int j = 0; j < (int)primes.size() && i * primes[j] <= N; ++ j) {
            is_prime.reset(i * primes[j]);
            if (i % primes[j] == 0) {
                break;
            }
        }
    }
    long long result = 0;
    for (auto prime : primes) {
        int n = prime - 1;
        bool valid = true;
        for (int d = 1; d * d <= n && valid; ++ d) {
            if (n % d == 0) {
                valid &= is_prime.test(d + n / d);
            }
        }
        if (valid) {
            result += n;
        }
    }
    std::cout << result << std::endl;
}
