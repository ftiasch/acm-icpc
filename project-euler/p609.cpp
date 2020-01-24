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

const int MOD = (int)1e9 + 7;

int solve(int n)
{
    std::vector<int> pi(n + 1);
    for (auto&& p : get_primes(n)) {
        pi[p] ++;
    }
    for (int i = 1; i <= n; ++ i) {
        pi[i] += pi[i - 1];
    }
    std::vector<int> sum(n + 1);
    for (int i = 1; i <= n; ++ i) {
        int len = 0;
        int cnt = 0;
        for (int j = i; j >= 1; j = pi[j]) {
            len ++;
            cnt += (pi[j] == pi[j - 1]);
            if (len >= 2) {
                sum[cnt] ++;
            }
        }
    }
    auto result = 1LL;
    for (int i = 0; i <= n; ++ i) {
        if (sum[i]) {
            result = result * sum[i] % MOD;
        }
    }
    return result;
}

int main()
{
    printf("%d\n", solve(10));
    printf("%d\n", solve(100));
    printf("%d\n", solve(100000000));
}
