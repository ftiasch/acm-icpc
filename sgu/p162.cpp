// SGU 162 -- Pyramids
#include <cmath>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

typedef long double Double;

const Double EPS = 1e-8;

int sgn(Double x) {
    return x < -EPS?-1: x > EPS;
}

Double sqr(Double x) {
    return x * x;
}

Double my_sqrt(double x) {
    return sqrt(max(x, 0.0));
}

struct Point {
    Double x, y;

    Point(Double x = 0, Double y = 0): x(x), y(y) {}

    Double norm() const {
        return my_sqrt(sqr(x) + sqr(y));
    }
};

Double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

struct Line {
    Double a, b, c;

    Line(Double a = 0, Double b = 0, Double c = 0): a(a), b(b), c(c) {}
};

Line intersect(const Point &a, Double r_a, const Point &b, Double r_b) {
    return Line(2.0 * (b.x - a.x), 2.0 * (b.y - a.y), sqr(a.x) + sqr(a.y) - sqr(b.x) - sqr(b.y) + sqr(r_b) - sqr(r_a));
}

Point intersect(const Line &l_1, const Line &l_2) {
    Double det = l_1.a * l_2.b - l_2.a * l_1.b;
    return Point((l_1.c * l_2.b - l_2.c * l_1.b) / det, (l_1.a * l_2.c - l_2.a * l_1.c) / det);
}

int main() {
    double ab, ac, ad, bc, bd, cd;
    scanf("%lf%lf%lf%lf%lf%lf", &ab, &ac, &ad, &bc, &bd, &cd);
    Point a(0, 0);
    Point b(ab, 0);
    Point c((sqr(ac) + sqr(ab) - sqr(bc)) / (2.0 * ab), 0);
    c.y = my_sqrt(sqr(ac) - sqr(c.x));
    Line l_1 = intersect(a, ad, b, bd);
    Line l_2 = intersect(a, ad, c, cd);
    Point o = intersect(l_1, l_2);
    Double h = my_sqrt(sqr(ad) - sqr((o - a).norm()));
    Double volume = abs(det(b, c)) * h / 6.0;
    if (sgn(volume) == 0) {
        volume = 0;
    }
    printf("%.4f\n", (double)volume);
    return 0;
}
