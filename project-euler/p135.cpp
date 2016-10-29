#include <cassert>
#include <cstdio>
#include <set>
#include <utility>
#include <vector>

const int N = 1000000;

std::vector<int> divisors[N];

int main()
{
    for (int d = 1; d * d < N; ++ d) {
        for (int n = d * d; n < N; n += d) {
            divisors[n].push_back(d);
        }
    }
    int result = 0;
    for (int n = 1; n < N; ++ n) {
        std::set<std::pair<int, int>> solutions;
        for (auto&& a : divisors[n]) {
            auto b = n / a;
            if ((a + b) % 4 == 0) {
                auto d = (b + a) / 4;
                auto r = (b - a) / 2;
                solutions.emplace(d + d + r, d);
                // auto y = d + d + r;
                // printf("%d:\n", n);
                // printf("%d, %d, %d\n", y + d, y, y - d);
                if (d > r) {
                    solutions.emplace(d + d - r, d);
                }
            }
        }
        result += (int)solutions.size() == 10;
    }
    printf("%d\n", result);
}
