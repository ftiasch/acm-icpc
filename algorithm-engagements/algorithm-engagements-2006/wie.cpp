// Algorithm Engagements 2006 Round 6 -- Polygons
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

struct Point {
    long long x, y;

    Point(long long x = 0, long long y = 0): x(x), y(y) {}

    double quadrant() const {
        if (x > 0 && y >= 0) {
            return 0;
        }
        if (y > 0 && x <= 0) {
            return 1;
        }
        if (x < 0 && y <= 0) {
            return 2;
        }
        return 3;
    }

    long long det(const Point &other) const {
        return x * other.y - y * other.x;
    }

    static Point next_point() {
        int x, y;
        scanf("%d%d", &x, &y);
        return Point(x, y);
    }
};

Point operator +(const Point &a, const Point &b) {
    return Point(a.x + b.x, a.y + b.y);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

bool operator <(const Point &a, const Point &b) {
    if (a.quadrant() == b.quadrant()) {
        return a.det(b) > 0;
    }
    return a.quadrant() < b.quadrant();
}

const int N = 222222;

Point a[N], b[N], edges[N];

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        a[i] = Point::next_point();
    }
    for (int i = 0; i < m; ++ i) {
        b[i] = Point::next_point();
    }
    for (int i = 0; i < n; ++ i) {
        edges[i] = a[(i + 1) % n] - a[i];
    }
    for (int i = 0; i < m; ++ i) {
        edges[n + i] = b[(i + 1) % m] - b[i];
    }
    sort(edges, edges + (n + m));
//for (int i = 0; i < (int)edges.size(); ++ i) {
//    printf("%lld, %lld\n", edges[i].x, edges[i].y);
//}
    long long result = 0;
    Point current = Point(0, 0);
    for (int i = 0; i < n + m; ++ i) {
        result += current.det(current + edges[i]);
        current = current + edges[i];
    }
    cout << result << endl;
    return 0;
}
