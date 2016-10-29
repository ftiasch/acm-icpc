#include <algorithm>
#include <cassert>
#include <cstdio>

long long result = 0;

void generate(int p1, int q1, int p2, int q2)
{
    auto pp = 1LL * p1 * q2 + 1LL * p2 * q1;
    auto qq = 2LL * q1 * q2;
    if (qq > 100000000) {
        return;
    }
    result += pp * 100LL < qq;
    auto p = p1 + p2;
    auto q = q1 + q2;
    generate(p1, q1, p, q);
    if (100LL * p < q) {
        generate(p, q, p2, q2);
    }
}

int main()
{
    generate(0, 1, 1, 1);
    printf("%lld\n", result);
}
