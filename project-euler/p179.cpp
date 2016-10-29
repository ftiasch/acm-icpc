#include <cstdio>
#include <vector>

auto solve(int n) {
    std::vector<int> sigma(n + 1);
    std::vector<int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (!sigma[d]) {
            sigma[d] = 2;
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p > n) {
                break;
            }
            sigma[d * p] = sigma[d] * 2;
            if (d % p == 0) {
                int tmp = d;
                int e = 0;
                while (tmp % p == 0) {
                    tmp /= p;
                    e ++;
                }
                sigma[d * p] = sigma[d] / (e + 1) * (e + 2);
                break;
            }
        }
    }
    int result = 0;
    for (int i = 2; i < n; ++ i) {
        result += sigma[i] == sigma[i + 1];
    }
    return result;
}

int main()
{
    printf("%d\n", solve(10000000));
}
