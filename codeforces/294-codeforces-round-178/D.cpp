// Codeforces Round #178
// Problem D -- Shaass and Painter Robot
#include <cstdio>
#include <cstring>
#include <set>
#include <iostream>
#include <algorithm>

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point& operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }
};

Point operator +(Point a, const Point &b) {
    return a += b;
}

Point operator *(Point a, int k) {
    return Point(a.x * k, a.y * k);
}

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        return a.y < b.y;
    }
    return a.x < b.x;
}

Point DELTA[4] = {Point(1, 1), Point(-1, 1), Point(-1, -1), Point(1, -1)};

const int LIMIT = 1000000;

int n, m, d;
Point p;
char buffer[3];

std::set <Point> set;

int main() {
    scanf("%d%d%d%d%s", &n, &m, &p.x, &p.y, buffer);
    for (int i = 1; i <= n; ++ i) {
        int d = i == 1 || i == n ? 1 : m - 1;
        for (int j = 1; j <= m; j += d) {
            if ((i + j & 1) == (p.x + p.y & 1)) {
                set.insert(Point(i, j));
            }
        }
    }
    d = 0;
    if (buffer[0]  == 'U') {
        d ^= 1;
    }
    if (buffer[1] == 'L') {
        d ^= 3;
    }
    set.erase(p);
    long long answer = 1;
    for (int _ = 0; _ < LIMIT && !set.empty(); ++ _) {
        int low = 0;
        int high = std::max(n, m);
        while (low < high) {
            int middle = low + high + 1 >> 1;
            Point q = p + DELTA[d] * middle;
            if (1 <= q.x && q.x <= n && 1 <= q.y && q.y <= m) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        if (low == 0) {
            if (p.x + DELTA[d].x > n || p.x + DELTA[d].x < 1) {
                d ^= 1;
            }
            if (p.y + DELTA[d].y > m || p.y + DELTA[d].y < 1) {
                d ^= 3;
            }
        } else {
            answer += low;
            p += DELTA[d] * low;
            set.erase(p);
        }
    }
    std::cout << (set.empty() ? answer : -1) << std::endl;
    return 0;
}
