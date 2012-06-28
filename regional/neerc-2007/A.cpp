// Northeastern Europe 2007
// Problem A -- Ants
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 200;

int n, match[N];

struct Point {
    int x, y, id;

    Point(int x = 0, int y = 0): x(x), y(y) {}

    bool type() const {
        return id < n;
    }
};

Point points[N], origin;

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        return a.y < b.y;
    }
    return a.x < b.x;
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

int det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

bool compare(const Point &a, const Point &b) {
    return det(a - origin, b - origin) > 0;
}

void solve(int begin, int end) {
    if (begin < end) {
        int choice = min_element(points + begin, points + end) - points;
        swap(points[begin], points[choice]);
        origin = points[begin];
        sort(points + begin + 1, points + end, compare);
        int delta = 0;
        for (int i = begin + 1; i < end; ++ i) {
            if (points[begin].type() != points[i].type() && delta == 0) {
                if (points[begin].type()) {
                    match[points[begin].id] = points[i].id;
                } else {
                    match[points[i].id] = points[begin].id;
                }
                solve(begin + 1, i);
                solve(i + 1, end);
                break;
            }
            if (points[begin].type() != points[i].type()) {
                delta ++;
            } else {
                delta --;
            }
        }
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < (n << 1); ++ i) {
        cin >> points[i];
        points[i].id = i;
    }
    memset(match, -1, sizeof(match));
    solve(0, n << 1);
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", match[i] + 1 - n);
    }
    return 0;
}
