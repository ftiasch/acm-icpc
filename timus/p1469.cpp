#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <set>
#include <vector>

typedef long long Long;

const int N = 50000;
const int A = 20011;
const int B = 20021;

int n, x[N << 1], y[N << 1], current_x;

long double at(int i, int x0) {
    return y[i << 1] + (long double)(y[i << 1 | 1] - y[i << 1]) / (x[i << 1 | 1] - x[i << 1]) * (x0 - x[i << 1]);
}

struct Comparator {
    bool operator()(int i, int j) {
        long double y0 = at(i, ::current_x);
        long double y1 = at(j, ::current_x);
        if (std::abs(y0 - y1) < 1e-8) {
            return false;
        }
        return y0 < y1;
    }
};

struct Point {
    Point(int x, int y) : x(x), y(y) {}

    Point &operator -= (const Point &o) {
        x -= o.x, y -= o.y;
        return *this;
    }

    bool on(const Point &, const Point &) const;

    int x, y;
};

Point operator - (Point a, const Point &b) {
    return a -= b;
}

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

long long dot(const Point &a, const Point &b) {
    return (long long)a.x * b.x + (long long)a.y * b.y;
}

bool Point::on(const Point &a, const Point &b) const {
    const Point &p = *this;
    return det(p - a, p - b) == 0 && dot(p - a, p - b) <= 0;
}

int signum(long long x) {
    return x < 0 ? -1 : x > 0;
}

bool is_cross(const Point &a, const Point &b, const Point &c, const Point &d) {
    return signum(det(b - a, c - a)) * signum(det(b - a, d - a)) < 0;
}

bool _check(int i, int j) {
    Point a(x[i << 1], y[i << 1]);
    Point b(x[i << 1 | 1], y[i << 1 | 1]);
    Point c(x[j << 1], y[j << 1]);
    Point d(x[j << 1 | 1], y[j << 1 | 1]);
    if (a.on(c, d) || b.on(c, d) || c.on(a, b) || d.on(a, b)) {
        return true;
    }
    if (is_cross(a, b, c, d) && is_cross(c, d, a, b)) {
        return true;
    }
    return false;
}

bool check(int i, int j) {
    bool valid = _check(i, j);
    if (valid) {
        printf("YES\n%d %d\n", i + 1, j + 1);
    }
    return valid;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n << 1; ++ i) {
        int x0, y0;
        scanf("%d%d", &x0, &y0);
        x[i] = A * x0 + B * y0;
        y[i] = A * x0 - B * y0;
    }
    std::vector <std::pair <Long, int>> events;
    for (int i = 0; i < n << 1; ++ i) {
        events.push_back(std::make_pair(x[i], (x[i] > x[i ^ 1]) * n + (i >> 1)));
    }
    std::sort(events.begin(), events.end());
    std::set <int, Comparator> set;
    for (auto event : events) {
        ::current_x = event.first;
        int i = event.second % n;
        if (event.second / n) {
            set.erase(i);
            auto iterator = set.lower_bound(i);
            auto next_iterator = iterator;
            if (next_iterator != set.end() && iterator != set.begin()) {
                iterator --;
                if (check(*iterator, *next_iterator)) {
                    return 0;
                }
            }
        } else {
            auto iterator = set.lower_bound(i);
            if (iterator != set.end() && check(i, *iterator)) {
                return 0;
            }
            if (iterator != set.begin()) {
                iterator --;
                if (check(i, *iterator)) {
                    return 0;
                }
            }
            set.insert(i);
        }
    }
    puts("NO");
    return 0;
}
