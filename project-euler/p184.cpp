#include <algorithm>
#include <iostream>
#include <vector>
#include <map>

struct Point
{
    Point(int x, int y) : x(x), y(y) {}

    Point& operator -= (const Point& other)
    {
        x -= other.x;
        y -= other.y;
        return *this;
    }

    int quadrant() const
    {
        if (y) {
            return y > 0;
        }
        return x > 0;
    }

    int x, y;
};

auto det(const Point& a, const Point& b)
{
    return a.x * b.y - a.y * b.x;
}

auto dot(const Point& a, const Point& b)
{
    return a.x * b.x + a.y * b.y;
}

Point operator - (Point a, const Point& b)
{
    return a -= b;
}

auto operator < (const Point& a, const Point& b)
{
    if (a.quadrant() != b.quadrant()) {
        return a.quadrant() < b.quadrant();
    }
    auto d = det(a, b);
    if (d) {
        return d > 0;
    }
    return dot(a, a) > dot(b, b);
}

int gcd(int a, int b)
{
    return b ? gcd(b, a % b) : a;
}

auto solve(int m)
{
    std::vector<Point> points;
    std::map<std::pair<int, int>, int> count;
    for (auto i = -m; i < m; ++ i) {
        for (auto j = -m; j < m; ++ j) {
            if ((i || j) && i * i + j * j < m * m) {
                points.emplace_back(i, j);
                auto g = gcd(std::abs(i), std::abs(j));
                count[{i / g, j / g}] ++;
            }
        }
    }
    std::sort(points.begin(), points.end());
    // for (auto&& p : points) {
    //     printf("(%d,%d)\n", p.x, p.y);
    // }
    // puts("");
    auto n = (int)points.size();
    auto result = 1LL * n * (n - 1) * (n - 2) / 6;
    for (auto i = 0, j = 0; i < n; ++ i) {
        j = std::max(j - 1, 0);
        while (j < n && det(points[i], points[(i + j) % n]) >= 0) {
            j ++;
        }
        result -= (j - 1LL) * (j - 2LL) / 2;
    }
    for (auto&& iterator : count) {
        auto k1 = iterator.first;
        auto k2 = std::make_pair(-k1.first, -k1.second);
        if (k1 < k2) {
            auto x = iterator.second;
            result += x * (x - 1LL)  * x;
        }
    }
    return result;
}

int main()
{
    std::cout << solve(2) << std::endl;
    std::cout << solve(3) << std::endl;
    std::cout << solve(5) << std::endl;
    std::cout << solve(105) << std::endl;
}
