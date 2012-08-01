// Codeforces Round #117
// Problem A -- Battlefield
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const double EPS = 1e-9;
const double INF = 1e10;

// {{{ Point class

double signum(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Segment;

struct Point {
    double x, y;

    Point(double x = 0, double y = 0): x(x), y(y) {}

    double norm() const;
    double to(const Segment&) const;
    bool cute(const Point &, const Point &) const;
};

double Point::norm() const {
    return sqrt(x * x + y * y);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

bool Point::cute(const Point &a, const Point &b) const {
    return signum(dot(a - *this, b - *this) / (a - *this).norm()) > 0;
}

static const Point ORIGIN;

struct Segment {
    Point a, b;

    Segment(Point a = ORIGIN, Point b = ORIGIN): a(a), b(b) {}

    double norm() const;
};

istream &operator >>(istream &in, Segment &s) {
    return in >> s.a >> s.b;
}

double Segment::norm() const {
    return (a - b).norm();
}

double Point::to(const Segment &s) const {
    if (s.a.cute(s.b, *this) && s.b.cute(s.a, *this)) {
        return abs(det(s.a - *this, s.b - *this)) / s.norm();
    }
    return min((*this - s.a).norm(), (*this - s.b).norm());
}

double getDistance(const Segment &u, const Segment &v) {
    double result = u.a.to(v);
    result = min(result, u.b.to(v));
    result = min(result, v.a.to(u));
    result = min(result, v.b.to(u));
    return result;
}

// }}}

const int N = 1000;

int chargeTime, shootTime, n;
double minimum[N];
bool visit[N];
Point source, target;
Segment trench[N];

int main() {
    cin >> chargeTime >> shootTime >> source >> target >> n;
    for (int i = 0; i < n; ++ i) {
        cin >> trench[i];
    }
    if (signum((source - target).norm() - chargeTime) <= 0) {
        printf("%.10f\n", (source - target).norm());
    } else {
        for (int i = 0; i < n; ++ i) {
            if (signum(source.to(trench[i]) - chargeTime) <= 0) {
                minimum[i] = chargeTime + shootTime;
            } else {
                minimum[i] = INF;
            }
        }
        memset(visit, 0, sizeof(visit));
        for (int k = 0; k < n; ++ k) {
            int i = 0;
            while (visit[i]) {
                i ++;
            }
            for (int j = 0; j < n; ++ j) {
                if (!visit[j] && minimum[j] < minimum[i]) {
                    i = j;
                }
            }
            if (signum(minimum[i] - INF) == 0) {
                break;
            }
            visit[i] = true;
            for (int j = 0; j < n; ++ j) {
                if (!visit[j] && signum(getDistance(trench[i], trench[j]) - chargeTime) <= 0) {
                    minimum[j] = min(minimum[j], minimum[i] + chargeTime + shootTime);
                }
            }
        }
        double result = INF;
        for (int i = 0; i < n; ++ i) {
            if (signum(target.to(trench[i]) - chargeTime) <= 0) {
                result = min(result, minimum[i] + target.to(trench[i]));
            }
        }
        if (signum(result - INF) == 0) {
            puts("-1");
        } else {
            printf("%.10f\n", result);
        }
    }
    return 0;
}
