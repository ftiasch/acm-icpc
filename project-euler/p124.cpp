#include <algorithm>
#include <cstdio>
#include <utility>
#include <vector>

const int N = 100000;

int rad[N + 1];

int main()
{
    std::fill(rad, rad + (N + 1), 1);
    std::vector<int> primes;
    for (int d = 2; d <= N; ++ d) {
        if (rad[d] == 1) {
            primes.push_back(d);
            rad[d] = d;
        }
        for (auto&& p : primes) {
            if (d * p > N) {
                break;
            }
            if (d % p == 0) {
                rad[d * p] = rad[d];
                break;
            } else {
                rad[d * p] = rad[d] * p;
            }
        }
    }
    std::vector<std::pair<int, int>> order;
    for (int i = 1; i <= N; ++ i) {
        order.emplace_back(rad[i], i);
    }
    std::sort(order.begin(), order.end());
    printf("%d\n", order.at(10000 - 1).second);
}
