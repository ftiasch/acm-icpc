#include <algorithm>
#include <cmath>
#include <cstdio>
#include <iostream>

long long sqr(long long x)
{
    return x * x;
}

struct Point {
    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point &operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }

    double norm() const {
        return sqrt(sqr(x) + sqr(y));
    }

    int x, y;
};

long long det(const Point &a, const Point &b)
{
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

long long dot(const Point &a, const Point &b)
{
    return (long long)a.x * b.x + (long long)a.y * b.y;
}

Point operator - (Point a, const Point &b)
{
    return a -= b;
}

const int N = 40000;

int n;
Point p[N];
long long sum[N + 1];

long long get_sum(int i, int j)
{
    long long s = sum[j] - sum[i];
    return i <= j ? s : sum[n] + s;
}

int next(int i)
{
    return (i + 1) % n;
}

double solve()
{
    sum[0] = 0;
    for (int i = 0; i < n; ++ i) {
        sum[i + 1] = sum[i] + det(p[i], p[(i + 1) % n]);
    }
    double result = INFINITY;
    for (int i = 0, j = 0; i < n; ++ i) {
        while (dot(p[next(i)] - p[i], p[next(j)] - p[j]) > 0) {
            j = next(j);
        }
        Point a = p[next(i)] - p[i];
        Point b = p[j]       - p[i];
        double n = a.norm();
        result = std::min(result, det(a, b) / n * dot(a, b) / n - (get_sum(i, j) + det(p[j], p[i])));
        if (i == j) {
            return 0.;
        }
    }
    return .5 * result;
}

int main()
{
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &p[i].x, &p[i].y);
    }
    solve();
    if (sum[n] < 0) {
        std::reverse(p, p + n);
    }
    double result = solve();
    for (int i = 0; i < n; ++ i) {
        p[i].x *= -1;
    }
    std::reverse(p, p + n);
    result = std::min(result, solve());
    printf("%.10f\n", result);
    return 0;
}
