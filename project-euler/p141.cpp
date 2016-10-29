#include <algorithm>
#include <cmath>
#include <cstdio>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using Long = long long;

// const Long N = 100000;
const Long N = 1000000000000LL;

int main()
{
    std::vector<Long> result;
    for (auto y = 1LL; y * y * y < N; ++ y) {
        for (auto x = 1LL; x < y && x * y * y * y + x * x < N; ++ x) {
            if (std::__gcd(x, y) > 1) {
                continue;
            }
            for (auto d = 1LL; d * d * x * y * y * y + d * x * x < N; ++ d) {
                auto n2 = d * d * x * y * y * y + d * x * x;
                auto n = (Long)sqrt(n2);
                if (n * n == n2) {
                    result.push_back(n2);
                }
            }
        }
    }
    std::sort(ALL(result));
    result.erase(std::unique(ALL(result)), result.end());
    auto sum = 0LL;
    for (auto&& r : result) {
        sum += r;
    }
    printf("%lld\n", sum);
}
