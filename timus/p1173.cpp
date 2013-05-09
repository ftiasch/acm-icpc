// Timus 1173 -- Lazy Snail
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 1000;

struct Point {
    int id, x, y;

    Point(int id = -1, int x = 0, int y = 0) : id(id), x(x), y(y) {
    }

    Point &operator -=(const Point &o) {
        this->x -= o.x;
        this->y -= o.y;
        return *this;
    }

    int quadrant() const {
        return x > 0 || (x == 0 && y < 0);
    }
};

Point operator -(Point a, const Point &b) {
    return a -= b;
}

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

bool operator <(const Point &a, const Point &b) {
    if (a.quadrant() == b.quadrant()) {
        return det(a, b) > 0;
    }
    return a.quadrant() < b.quadrant();
}

Point start, points[N];

int main() {
    double x, y;
    scanf("%lf%lf", &x, &y);
    start.x = (int)(x * 1000);
    start.y = (int)(y * 1000);
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int id;
        scanf("%lf%lf%d", &x, &y, &id);
        points[i].x = (int)(x * 1000);
        points[i].y = (int)(y * 1000);
        points[i].id = id;
        points[i] -= start;
    }
    std::sort(points, points + n);
    for (int i = 0; i < n; ++ i) {
        bool valid = true;
        for (int j = 0; j < n; ++ j) {
            valid &= det(points[i], points[j]) >= 0;
        }
        if (valid) {
            std::rotate(points, points + i, points + n);
            break;
        }
    }
    puts("0");
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", points[i].id);
    }
    puts("0");
    return 0;
}
