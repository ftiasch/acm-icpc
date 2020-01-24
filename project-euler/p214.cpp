#include <cstdio>
#include <vector>

int main()
{
    const int n = 40000000;
    std::vector<int> phi(n), length(n);
    std::vector<int> primes;
    length[1] = 1;
    auto result = 0LL;
    for (int d = 2; d < n; ++ d) {
        if (!phi[d]) {
            primes.push_back(d);
            phi[d] = d - 1;
        }
        length[d] = length[phi[d]] + 1;
        if (phi[d] == d - 1 && length[d] == 25) {
            result += d;
        }
        for (auto&& p : primes) {
            if (d * p >= n) {
                break;
            }
            phi[d * p] = phi[d] * (p - 1);
            if (d % p == 0) {
                phi[d * p] = phi[d] * p;
                break;
            }
        }
    }
    printf("%lld\n", result);
}
