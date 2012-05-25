// Codeforces Beta Round #13
// Problem B -- Letter A
#include <cstdio>
#include <cstring>
#include <iostream>
#include <iostream>
using namespace std;

struct Point {
    long long x, y;

    Point(long long x = 0, long long y = 0): x(x), y(y) {
    }

    long long norm2() {
        return x * x + y * y;
    }
};

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

istream &operator >>(istream &in, Point &p) {
    return cin >> p.x >> p.y;
}

long long det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

long long dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

Point segments[3][2];

bool check_angle(const Point &a, const Point &b) {
    return det(a, b) != 0 && dot(a, b) >= 0;
}

bool online(const Point &p, const Point &a, const Point &b) {
    return det(p - a, p - b) == 0 && dot(p - a, p - b) <= 0;
}

bool check_segment(const Point &p, const Point &a, const Point &b) {
    if (online(p, a, b)) {
        long long large_part = (p - a).norm2();
        long long small_part = (p - b).norm2();
        if (large_part < small_part) {
            swap(large_part, small_part);
        }
        return small_part * 16 >= large_part;
    }
    return false;
}

bool check() {
    for (int first = 0; first < 3; ++ first) {
        for (int second = first + 1; second < 3; ++ second) {
            bool found = false;
            Point common;
            for (int i = 0; i < 2 && !found; ++ i) {
                for (int j = 0; j < 2 && !found; ++ j) {
                    if (segments[first][i] == segments[second][j]) {
                        found = true;
                        common = segments[first][i];
                    }
                }
            }
            if (found) {
                if (segments[first][0] == common) {
                    swap(segments[first][0], segments[first][1]);
                }
                if (segments[second][0] == common) {
                    swap(segments[second][0], segments[second][1]);
                }
                if (check_angle(segments[first][0] - common, segments[second][0] - common)) {
                    int thrid = 3 ^ first ^ second;
                    for (int k = 0; k < 2; ++ k) {
                        if (check_segment(segments[thrid][k], segments[first][0], segments[first][1]) && check_segment(segments[thrid][k ^ 1], segments[second][0], segments[second][1])) {
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
}

int main() {
    int test_count;
    cin >> test_count;
    while (test_count > 0) {
        test_count --;
        for (int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 2; ++ j) {
                cin >> segments[i][j];
            }
        }
        puts(check()? "YES": "NO");
    }
    return 0;
}
