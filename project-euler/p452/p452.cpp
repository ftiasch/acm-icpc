#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

std::vector <int> get_primes(int n) {
    std::vector <bool> is_prime(n + 1, true);
    std::vector <int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (int &p : primes) {
            if (d * p > n) {
                break;
            }
            is_prime[d * p] = false;
            if (d % p == 0) {
                break;
            }
        }
    }
    return std::move(primes);
}

const int MOD = 1234567891;

int inverse(int a) {
    return a > 1 ? (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD : 1;
}

int search(const std::vector <int> &primes,
           const std::vector <int> &partition,
           const int &count,
           int m, int i) {
    if (i --) {
        int result = search(primes, partition, count, m, i);
        for (int k = 1; m >= primes[i]; k ++) {
            result = (result + (long long)search(primes, partition, count, m /= primes[i], i) * partition[k] % MOD) % MOD;
        }
        return result;
    } else {
        int number = std::max((int)(std::upper_bound(primes.begin(), primes.end(), m) - primes.begin()) - count, 0);
        return ((long long)number * partition[1] + 1) % MOD;
    }
}

int solve(int m, int n) {
    std::vector <int> partition;
    for (int i = 0; 1 << i <= m; ++ i) {
        int result = 1;
        for (int j = 0; j < i; ++ j) {
            result = (long long)result * (n + j) % MOD * inverse(j + 1) % MOD;
        }
        partition.push_back(result);
    }
    std::vector <int> primes = get_primes(m);
    int count = 0;
    while (primes[count] * primes[count] < m) {
        count ++;
    }
    return search(primes, partition, count, m, count);
}

int main() {
    //assert(solve(10, 10) == 571);
    //assert(solve(1000000, 1000000) == 252903833);
    printf("%d\n", solve(1000000000, 1000000000));
    return 0;
}
