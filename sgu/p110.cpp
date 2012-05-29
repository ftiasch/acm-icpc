// SGU 110 -- Dungeon
#include <cstdio>
#include <cmath>
#include <cassert>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const double EPS = 1e-8;
const double INF = 1e9;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

double sqr(double x) {
    return x * x;
}

struct Point {
    double x, y, z;

    Point(double x = 0, double y = 0, double z = 0): x(x), y(y), z(z) {}

    double norm() const {
        return sqrt(max(x * x + y * y + z * z, 0.0));
    }

    Point normalize() const {
        double l = norm();
        return Point(x / l, y / l, z / l);
    }

    const char* show() {
        char *buffer = new char[22];
        sprintf(buffer, "(%.2f %.2f %.2f)", x, y, z);
        return buffer;
    }
};

Point operator+(const Point &a, const Point &b) {
    return Point(a.x + b.x, a.y + b.y, a.z + b.z);
}

Point operator-(const Point &a) {
    return Point(-a.x, -a.y, -a.z);
}

Point operator-(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y, a.z - b.z);
}

Point operator*(const Point &a, double k) {
    return Point(a.x * k, a.y * k, a.z * k);
}

istream &operator>>(istream &in, Point &p) {
    return in >> p.x >> p.y >> p.z;
}

Point det(const Point &a, const Point &b) {
    return Point(a.y * b.z - b.y * a.z, a.z * b.x - b.z * a.x, a.x * b.y - b.x * a.y);
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
}

Point projection(const Point &a, const Point &b) {
    assert(sgn(b.norm() - 1) == 0);
    return b * dot(a, b);
}

Point reflection(const Point &a, const Point &b) {
    Point p = projection(a, b);
    return p - (a - p);
}

const int N = 50;

int n;
Point source, center[N], direction;
double radius[N];
vector <int> result;

double get_distance(Point source, Point direction, Point center, double radius) {
    center = center - source;
    Point p = projection(center, direction);
    double h = (center - p).norm();
    if (sgn(h - radius) > 0) {
        return INF;
    }
    double d = sqrt(max(sqr(radius) - sqr(h), 0.0));
    double result = dot(p, direction) - d;
    return sgn(result) <= 0? INF: result;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        cin >> center[i] >> radius[i];
    }
    cin >> source >> direction;
    direction = (direction - source).normalize();
    while ((int)result.size() < 11) {
//printf("%s %s\n", source.show(), direction.show());
        int index = -1;
        double min_distance = INF;
        for (int i = 0; i < n; ++ i) {
            double ret = get_distance(source, direction, center[i], radius[i]);
            //printf("%d: %.2f\n", i, ret);
            if (sgn(ret - min_distance) < 0) {
                index = i;
                min_distance = ret;
            }
        }
        if (index == -1) {
            break;
        }
        result.push_back(index);
        source = source + direction * min_distance;
        Point normal = (source - center[index]).normalize();
        direction = reflection(-direction, normal);
    }
    int total = min(10, (int)result.size());
    for (int i = 0; i < total; ++ i) {
        printf("%d", result[i] + 1);
        if (i != total - 1) {
            printf(" ");
        }
    }
    if ((int)result.size() > 10) {
        printf(" etc.");
    }
    puts("");
    return 0;
}
