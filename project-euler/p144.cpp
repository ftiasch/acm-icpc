#include <algorithm>
#include <cmath>
#include <cstdlib>
#include <cstdio>

struct Point
{
    Point(double x, double y) : x(x), y(y) {}

    double norm() const
    {
        return sqrt(x * x + y * y);
    }

    Point normalize() const
    {
        auto n = norm();
        return Point(x / n, y / n);
    }

    double x, y;
};

double dot(const Point& a, const Point& b)
{
    return a.x * b.x + a.y * b.y;
}

Point operator + (const Point& a, const Point &b)
{
    return Point(a.x + b.x, a.y + b.y);
}

Point operator - (const Point& a, const Point &b)
{
    return Point(a.x - b.x, a.y - b.y);
}

Point operator * (double k, const Point& a)
{
    return Point(a.x * k, a.y * k);
}

bool exit(const Point& p)
{
    return std::abs(p.x) <= 0.01 && p.y > 0;
}

double eval(const Point& p)
{
    return 4 * p.x * p.x + p.y * p.y - 100;
}

int main()
{
    Point p {0  , 10.1};
    Point q {1.4, -9.6};
    int count = 0;
    while (!exit(q)) {
        count ++;
        std::swap(p, q);
        auto n = Point(4 * p.x, p.y).normalize();
        auto d = q - p;
        n = 2 * dot(d, n) * n - d;
        double low = 0.;
        double high = 20.;
        for (int _ = 0; _ < 50; ++ _) {
            auto middle = .5 * (low + high);
            if (eval(p + middle * n) <= 0) {
                low = middle;
            } else {
                high = middle;
            }
        }
        q = p + .5 * (low + high) * n;
    }
    printf("%d\n", count);
}
