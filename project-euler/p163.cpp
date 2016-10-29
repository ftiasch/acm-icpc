#include <cmath>
#include <cstdio>
#include <tuple>
#include <utility>
#include <vector>

const double EPS = 1e-9;

int signum(double x)
{
    return x < -EPS ? -1 : x > EPS;
}

struct Point
{
    Point(double x = 0., double y = 0.) : x(x), y(y) {}

    bool on(const Point&, const Point&) const;

    Point& operator += (const Point& other)
    {
        x += other.x;
        y += other.y;
        return *this;
    }

    Point& operator -= (const Point& other)
    {
        x -= other.x;
        y -= other.y;
        return *this;
    }

    double x, y;
};

Point operator - (Point a, const Point& b)
{
    return a -= b;
}

double det(const Point& a, const Point& b)
{
    return a.x * b.y - a.y * b.x;
}

Point operator * (double k, const Point& a)
{
    return Point(a.x * k, a.y * k);
}

Point operator / (const Point& a, double k)
{
    return Point(a.x / k, a.y / k);
}

double dot(const Point& a, const Point& b)
{
    return a.x * b.x + a.y * b.y;
}

bool Point::on(const Point& a, const Point& b) const
{
    auto&& p = *this;
    return signum(det(p - a, p - b)) == 0 && signum(dot(p - a, p - b)) <= 0;
}

bool check(const Point& a, const Point& b, const Point& c, const Point& d)
{
    return signum(det(b - a, c - a)) * signum(det(b - a, d - a)) < 0;
}

using Segment = std::pair<Point, Point>;

// bool intersect(const Point& a, const Point& b, const Point& c, const Point& d)
bool intersect(const Segment& u, const Segment& v)
{
    Point a, b, c, d;
    std::tie(a, b) = u;
    std::tie(c, d) = v;
    // if (signum(det(b - a, d - c)) == 0) {
    //     return false;
    // }
    if (a.on(c, d) || b.on(c, d) || c.on(a, b) || d.on(a, b)) {
        return true;
    }
    return check(a, b, c, d) && check(c, d, a, b);
}

auto get_intersection(const Segment& u, const Segment& v)
{
    Point a, b, c, d;
    std::tie(a, b) = u;
    std::tie(c, d) = v;
    auto s1 = det(b - a, c - a);
    auto s2 = det(b - a, d - a);
    return (s1 * d - s2 * c) / (s1 - s2);
}

int solve(int n)
{
    static const double S3 = sqrt(3);
    std::vector<Point> points;
    Point p(0., 0.);
    for (auto d : {Point(2., 0.), Point(-1., S3), Point(-1, -S3)}) {
        for (int _ = 0; _ < n + n; ++ _) {
            points.push_back(p);
            p += d;
        }
    }
    std::vector<Segment> segments;
    auto generate = [&](int n, int p, int q, int dp, int dq)
    {
        auto m = points.size();
        for (int _ = 0; _ < n; ++ _) {
            segments.emplace_back(points.at(p), points.at(q));
            (p += m + dp) %= m;
            (q += m + dq) %= m;
        }
    };
    generate(n, 0, 2 * n, -2,  2);
    generate(n, 0, 4 * n,  2, -2);
    generate(n, 2 * n, 4 * n, -2, 2);
    generate(n + n - 1, 1, 6 * n - 2, 1, -2);
    generate(n + n - 1, 2, 6 * n - 1, 2, -1);
    generate(n + n - 1, 2 * n - 2, 2 * n + 1, -2, 1);
    // printf("!! %d\n", (int)segments.size());
    int result = 0;
    // printf("%d\n", get_intersection(segments[4], segments[2]).on(segments[1].first, segments[1].second));
    // for (auto&& segment : segments) {
    //     Point p, q;
    //     std::tie(p, q) = segment;
    //     printf("(%.2f, %.2f), (%.2f, %.2f)\n", p.x, p.y, q.x, q.y);
    // }
    for (int i = 0; i < (int)segments.size(); ++ i) {
        for (int j = 0; j < i; ++ j) {
            if (!intersect(segments[i], segments[j])) {
                continue;
            }
            auto p = get_intersection(segments[i], segments[j]);
            // printf("%d,%d,(%.10f,%.10f)\n", i, j, p.x, p.y);
            for (int k = 0; k < j; ++ k) {
                if (intersect(segments[i], segments[k])
                    && intersect(segments[j], segments[k])
                    && !p.on(segments[k].first, segments[k].second)) {
                    // printf("%d,%d,%d\n", i, j, k);
                    result ++;
                }
                // if (intersect(segments[i], segments[k])
                //     && intersect(segments[j], segments[k])) {
                //     // && !p.on(segments[k].first, segments[k].second)) {
                //     printf("%d,%d,%d\n", i, j, k);
                //     result ++;
                // }
            }
        }
    }
    return result;
}

int main()
{
    printf("%d\n", solve(1));
    printf("%d\n", solve(2));
    printf("%d\n", solve(36));
}
