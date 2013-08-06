// 1978. E-Lite
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) ((int)(v).size())

struct Point {
    int x, y;

    Point() : x(0), y(0) {}
    Point(int x, int y) : x(x), y(y) {}

    Point &operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    Point &operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }

    int quadrant() const {
        if (y != 0) {
            return y > 0;
        }
        return x < 0;
    }

    Point rotate90() {
        return Point(-y, x);
    }

    long long norm2() const {
        return (long long)x * x + (long long)y * y;
    }
};

Point operator +(Point a, const Point &b) {
    return a += b;
}

Point operator -(Point a, const Point &b) {
    return a -= b;
}

long long dot(const Point &a, const Point &b) {
    return (long long)a.x * b.x + (long long)a.y * b.y;
}

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

bool operator ==(const Point &a, const Point &b) {
    return a.quadrant() == b.quadrant() && det(a, b) == 0;
}

bool operator <(const Point &a, const Point &b) {
    if (a.quadrant() != b.quadrant()) {
        return a.quadrant() < b.quadrant();
    }
    return det(a, b) > 0;
}

const int N = 1000;

typedef std::pair <Point, std::pair <int, int> > Pair;

int n, order[N], position[N];
Point points[N];

bool by_dot(int i, int j) {
    static Point p(-1000000000, -1);
    return dot(points[i], p) > dot(points[j], p);
}

Point sum[N + 1];
long long answer[N + 1];

void reverse(int l, int r) {
    std::reverse(order + l, order + r);
    for (int i = l; i < r; ++ i) {
        position[order[i]] = i;
    }
    for (int i = l; i < r; ++ i) {
        sum[i + 1] = sum[i] + points[order[i]];
        answer[i + 1] = std::max(answer[i + 1], sum[i + 1].norm2());
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &points[i].x, &points[i].y);
    }
    std::vector <Pair> events;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (points[i].x != points[j].x || points[i].y != points[j].y) {
                events.push_back(std::make_pair((points[i] - points[j]).rotate90(), std::make_pair(i, j)));
            }
        }
    }
    std::sort(ALL(events));
    for (int i = 0; i < n; ++ i) {
        order[i] = i;
    }
    std::sort(order, order + n, by_dot);
    for (int i = 0; i < n; ++ i) {
        position[order[i]] = i;
    }
    sum[0] = Point(0, 0);
    for (int i = 0; i < n; ++ i) {
        sum[i + 1] = sum[i] + points[order[i]];
        answer[i + 1] = sum[i + 1].norm2();
    }
    for (int begin = 0; begin < SIZE(events); ) {
        int end = begin;
        while (end < SIZE(events) && events[begin].first == events[end].first) {
            end ++;
        }
        std::vector <int> indices;
        for (int i = begin; i < end; ++ i) {
            const std::pair <int, int> &e = events[i].second;
            indices.push_back(std::min(position[e.first], position[e.second]));
            indices.push_back(std::max(position[e.first], position[e.second]) - 1);
        }
        std::sort(ALL(indices));
        indices.erase(std::unique(ALL(indices)), indices.end());
        for (int i = 0; i < SIZE(indices); ) {
            int j = i;
            while (j < SIZE(indices) && indices[i] - indices[j] == i - j) {
                j ++;
            }
            reverse(indices[i], indices[i] + j - i + 1);
            i = j;
        }
        begin = end;
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%.10f\n", (double)sqrt((long double)answer[i]));
    }
    return 0;
}
