#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>

auto get_primes(int n) {
    std::vector<bool> is_prime(n, true);
    std::vector<int> primes;
    for (int d = 2; d < n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p >= n) {
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

auto row_begin(int n)
{
    return 1 + (n - 1LL) * n / 2;
}

auto solve(int n)
{
    auto count = 5 * n;
    std::vector<bool> is_prime(count, true);
    {
        auto begin = row_begin(n - 2);
        for (auto&& p : get_primes((int)sqrt(begin + count) + 1)) {
            for (auto offset = std::max(((begin - 1) / p + 1), 2LL) * p - begin; offset < count; offset += p) {
                is_prime[offset] = false;
            }
        }
    }
    std::vector<bool> belongs(count);
    for (auto row = n - 1; row <= n + 1; ++ row) {
        for (auto index = 0; index < row; ++ index) {
            if (!is_prime[row_begin(row) + index - row_begin(n - 2)]) {
                continue;
            }
            std::vector<int> indices;
            for (auto x = row - 1; x <= row + 1; ++ x) {
                for (auto y = std::max(index - 1, 0); y <= index + 1 && y < x; ++ y) {
                    auto tmp = row_begin(x) + y - row_begin(n - 2);
                    if (is_prime[tmp]) {
                        indices.push_back(tmp);
                    }
                }
            }
            if ((int)indices.size() >= 3) {
                for (auto index : indices) {
                    belongs[index] = true;
                }
            }
        }
        // auto begin = row_begin(row);
    }
    auto result = 0LL;
    for (auto index = 0; index < n; ++ index) {
        if (belongs[row_begin(n) + index - row_begin(n - 2)]) {
            // printf("=> %lld\n", row_begin(n) + index);
            result += row_begin(n) + index;
        }
    }
    return result;
}

int main()
{
    printf("%lld\n", solve(8));
    printf("%lld\n", solve(9));
    printf("%lld\n", solve(10000));
    printf("%lld\n", solve(5678027) + solve(7208785));
}
