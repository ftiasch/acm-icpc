#include <algorithm>
#include <cmath>
#include <cassert>
#include <array>
#include <cstdio>
#include <limits>
#include <vector>

const int N = 64000000;

using Int = long long;

constexpr auto MAX = std::numeric_limits<Int>::max();

std::array<Int, N> sigma;

Int multiply(const Int& a, const Int& b)
{
    assert(a <= MAX / b);
    return a * b;
}

int main()
{
    std::vector<int> primes;
    sigma[1] = 1;
    for (auto d = 2; d < N; ++ d) {
        if (!sigma[d]) {
            primes.push_back(d);
            sigma[d] = 1 + static_cast<Int>(d) * d;
        }
        for (auto&& p : primes) {
            if (d * p >= N) {
                break;
            }
            sigma[d * p] = multiply(sigma[d], 1 + static_cast<Int>(p) * p);
            if (d % p == 0) {
                auto tmp = d * p;
                auto sum = static_cast<Int>(1);
                while (tmp % p == 0) {
                    sum *= p;
                    sum *= p;
                    sum += 1;
                    tmp /= p;
                }
                sigma[d * p] = multiply(sigma[tmp], sum);
                break;
            }
        }
    }
    auto result = 0LL;
    for (auto n = 1; n < N; ++ n) {
        auto r = static_cast<Int>(floor(sqrt(sigma[n]))) - 1;
        while (r * r < sigma[n]) {
            r ++;
        }
        if (r * r == sigma[n]) {
            result += n;
        }
    }
    printf("%lld\n", result);
}
