// SGU 120 -- Archipelag
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const double PI = acos(-1.0);
const double EPS = 1e-8;

double sqr(double x) {
    return x * x;
}

struct Point {
    double x, y;

    Point(double x = 0, double y = 0): x(x), y(y) {
    }

    double norm() const {
        return sqrt(x * x + y * y);
    }

    Point normalize() const {
        double l = norm();
        return Point(x / l, y / l);
    }
};

Point operator +(const Point &a, const Point &b) {
    return Point(a.x + b.x, a.y + b.y);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

Point operator *(const Point &a, const Point &b) {
    return Point(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x);
}

Point operator /(const Point &a, double k) {
    return Point(a.x / k, a.y / k);
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

const int N = 150;

int n, n_1, n_2;
Point p_1, p_2, polygon[N];

int main() {
    cin >> n >> n_1 >> n_2 >> p_1 >> p_2;
    n_1 --;
    n_2 --;
    int delta_n = min(abs(n_1 - n_2), n - abs(n_1 - n_2));
    if ((n_1 + delta_n) % n != n_2) {
        swap(n_1, n_2);
        swap(p_1, p_2);
    }
    double angle = 2 * PI / n;
    double radius = (p_1 - p_2).norm() / 2.0 / cos((PI - angle * delta_n) / 2.0);
    double half = (p_2 - p_1).norm() / 2.0;
    Point normal = ((p_2 - p_1) * Point(0, -1)).normalize();
    Point middle = (p_1 + p_2) / 2.0;
    double d = sqrt(max(sqr(radius) - sqr(half), 0.0));
    Point center = middle + normal * d;
    Point p = p_1 - center;
    Point r = Point(cos(-angle), sin(-angle));
    for (int i = 0; i < n; ++ i) {
        polygon[(n_1 + i) % n] = center + p;
        p = p * r;
    }
    for (int i = 0; i < n; ++ i) {
        if (fabs(polygon[i].x) < EPS) {
            polygon[i].x = 0;
        }
        if (fabs(polygon[i].y) < EPS) {
            polygon[i].y = 0;
        }
        printf("%.6f %.6f\n", polygon[i].x, polygon[i].y);
    }
    return 0;
}
