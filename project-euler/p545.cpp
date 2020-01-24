// https://www.wikiwand.com/en/Von_Staudt%E2%80%93Clausen_theorem
#include <cstdio>
#include <cstring>
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

const int N = 1000000000;

bool ok[N + 1];

int main()
{
    memset(ok, true, sizeof(ok));
    for (auto&& p : get_primes(N)) {
        if (p != 2 && p != 3 && p != 5 && p != 23 && p != 29) {
            for (int d = p - 1; d <= N; d += p - 1) {
                ok[d] = false;
            }
        }
    }
    int count = 100000;
    for (int d = 308; d <= N; d += 308) {
        if (ok[d]) {
            count --;
            if (count == 0) {
                printf("%d\n", d);
                return 0;
            }
        }
    }
    printf("!!%d\n", count);
}
