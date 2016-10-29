#include <cstdio>

using LL = long long;

LL count(int n, int a, int b)
{
    auto result = 0LL;
    n /= a * a + b * b;
    for (auto d = 1; d <= n; ++ d) {
        result += 2LL * d * (a + b) * (n / d);
    }
    if (a == b) {
        result /= 2;
    }
    return result;
}

LL generate(int n, int a1, int b1, int a2, int b2)
{
    auto a = a1 + a2;
    auto b = b1 + b2;
    auto result = 0LL;
    if (a * a + b * b <= n) {
        result += count(n, a, b);
        result += generate(n, a1, b1, a, b);
        result += generate(n, a, b, a2, b2);
    }
    return result;
}

LL solve(int n)
{
    LL result = 0;
    for (int d = 1; d <= n; ++ d) {
        result += n / d * d;
    }
    result += generate(n, 0, 1, 1, 1);
    result += count(n, 1, 1);
    return result;
}

int main()
{
    printf("%lld\n", solve(5));
    printf("%lld\n", solve(100000));
    printf("%lld\n", solve(100000000));
}
