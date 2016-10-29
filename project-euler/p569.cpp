#include <cstdio>
#include <iostream>
#include <vector>

std::vector<int> get_primes(int n) {
    std::vector<bool> is_prime(n + 1, true);
    std::vector<int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p > n) {
                break;
            }
            is_prime[d * p] = false;
            if (d % p == 0) {
                break;
            }
        }
    }
    return primes;
}

const int N = 2500000;

using Long = long long;
using LongLong = __int128;

struct Point
{
    Point(Long x = 0, Long y = 0) : x(x), y(y) {}

    Long x, y;
};

Point operator + (const Point& a, const Point& b)
{
    return Point(a.x + b.x, a.y + b.y);
}

Point operator - (const Point& a, const Point& b)
{
    return Point(a.x - b.x, a.y - b.y);
}

LongLong det(const Point& a, const Point& b)
{
    return (LongLong)a.x * b.y - (LongLong)a.y * b.x;
}

Point points[N];

int solve(int l, int r)
{
    if (r - l <= 1) {
        return 0;
    }
    int top = l + 1;
    int result = 1;
    for (int i = l + 2; i < r; ++ i) {
        if (det(points[top] - points[l], points[i] - points[l]) > 0) {
            top = i;
            result ++;
        }
    }
    result += solve(l + 1, top + 1);
    result += solve(top, r);
    return result;
}

int main()
{
    auto primes = get_primes(86028121);
    points[0] = Point(2, 2);
    for (int i = 1; i < N; ++ i) {
        auto&& p1 = primes.at(i * 2 - 1);
        auto&& p2 = primes.at(i * 2);
        points[i] = points[i - 1] + Point(p2 + p1, p2 - p1);
    }
    printf("%d\n", solve(0, N));
}
