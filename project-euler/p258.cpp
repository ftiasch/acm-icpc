#include <array>
#include <cstdio>

const auto N   = 2000;
const auto MOD = 20092010;

using Poly = std::array<int, N>;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

Poly operator * (const Poly& a, const Poly& b)
{
    static std::array<int, N + N> c;
    for (auto i = 0; i < N + N; ++ i) {
        c.at(i) = 0;
    }
    for (auto i = 0; i < N; ++ i) {
        for (auto j = 0; j < N; ++ j) {
            update(c.at(i + j), (long)a.at(i) * b.at(j) % MOD);
        }
    }
    for (auto i = N + N - 1; i >= N; -- i) {
        update(c.at(i - N),     c.at(i));
        update(c.at(i - N + 1), c.at(i));
    }
    Poly c_;
    for (auto i = 0; i < N; ++ i) {
        c_.at(i) = c.at(i);
    }
    return c_;
}

int solve(long n)
{
    Poly result, a;
    for (auto i = 0; i < N; ++ i) {
        result.at(i) = a.at(i) = 0;
    }
    result.at(0) = 1;
    a.at(1) = 1;
    while (n) {
        if (n & 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    auto answer = 0;
    for (auto&& x : result) {
        update(answer, x);
    }
    return answer;
}

int brute(long n)
{
    std::array<int, N> result;
    for (auto i = 0; i < N; ++ i) {
        result.at(i) = 1;
    }
    for (auto i = N; i <= n; ++ i) {
        update(result.at(i % N), result.at((i + 1) % N));
    }
    return result.at(n % N);
}

int main()
{
    printf("%d\n", solve(1000000000000000000L));
}
