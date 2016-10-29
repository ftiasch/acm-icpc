#include <cstdio>
#include <vector>

const auto N = 1LL << 25;

int main()
{
    auto result = N * N;
    std::vector<int> mu(N + 1, 2);
    std::vector<int> primes;
    for (auto d = 2LL; d <= N; ++ d) {
        if (mu[d] == 2) {
            primes.push_back(d);
            mu[d] = -1;
        }
        for (auto&& p : primes) {
            if (d * p > N) {
                break;
            }
            mu[d * p] = -mu[d];
            if (d % p == 0) {
                mu[d * p] = 0;
                break;
            }
        }
        result += mu[d] * (N * N) / (d * d);
    }
    printf("%lld\n", result);
}
