#include <cmath>
#include <cstdio>

long long sqr(long long x)
{                                                                                                                                                                                     return x * x;
}
long long solve(int r)
{
    auto result = 0LL;
    // sum_{-r <= s < 0} sum_{-r <= d <= r}
    result += (r / 2LL) * (2LL * r);
    auto R2 = sqr(r / 4);
    for (auto s = 1, d = 0; s <= r >> 2; ++ s) {
        while (sqr(s - r / 4) + sqr(d + 1) < R2) {
            d ++;
        }
        result += (d + (s & 1) >> 1) * 2 * (s == (r >> 1) - s ? 1 : 2);
    }
    // sum_{r/2 < s <= r}
    result += (r / 4LL) * (2LL * r);
    return result;
}

int main()
{
    printf("%lld\n", solve(4));
    printf("%lld\n", solve(8));
    printf("%lld\n", solve(1000000000));                                                                                                                                          }
