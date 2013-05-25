// Codeforces Beta Round #18
// Problem A -- Triangle
#include <cstdio>
#include <iostream>

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point &operator += (const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    Point &operator -= (const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }
};

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

Point operator - (Point a, const Point &b) {
    return a -= b;
}

int det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

int dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

bool is_right(const Point &a, const Point &b, const Point &c) {
    if (det(b - a, c - a) == 0) {
        return false;
    }
    Point points[3] = {a, b, c};
    for (int i = 0; i < 3; ++ i) {
        if (dot(points[(i + 1) % 3] - points[i], points[(i + 2) % 3] - points[i]) == 0) {
            return true;
        }
    }
    return false;
}

const Point DELTA[4] = {Point(-1, 0), Point(0, -1), Point(0, 1), Point(1, 0)};

int main() {
    Point points[3];
    std::cin >> points[0] >> points[1] >> points[2];
#define CHECK is_right(points[0], points[1], points[2])
    if (CHECK) {
        puts("RIGHT");
    } else {
        bool found = false;
        for (int i = 0; i < 3; ++ i) {
            for (int k = 0; k < 4; ++ k) {
                points[i] += DELTA[k];
                found |= CHECK;
                points[i] -= DELTA[k];
            }
        }
        puts(found ? "ALMOST" : "NEITHER");
    }
    return 0;
}
