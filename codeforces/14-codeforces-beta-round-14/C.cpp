// Codeforces Beta Round #14
// Problem C -- Four Segments
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

struct Point {
    long long x, y;

    Point(long long x = 0, long long y = 0): x(x), y(y) {
    }
};

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

bool operator !=(const Point &a, const Point &b) {
    return !(a == b);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

long long det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

long long dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

istream &operator >>(istream &in, Point &p) {
    return cin >> p.x >> p.y;
}


int main() {
    Point segments[4][2];
    for (int i = 0; i < 4; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            cin >> segments[i][j];
        }
    }
    bool found = false;
    for (int mask = 0; mask < (1 << 4); ++ mask) {
        for (int i = 0; i < 4; ++ i) {
            if (mask >> i & 1) {
                swap(segments[i][0], segments[i][1]);
            }
        }
        int permutation[4];
        for (int i = 0; i < 4; ++ i) {
            permutation[i] = i;
        }
        do {
            bool check = true;
            for (int iter = 0; iter  < 4; ++ iter) {
                int i = permutation[iter];
                if (segments[i][0] == segments[i][1]) {
                    check = false;
                }
                int type = -1;
                if (segments[i][0].x == segments[i][1].x) {
                    type = 0;
                }
                if (segments[i][0].y == segments[i][1].y) {
                    type = 1;
                }
                if ((iter & 1) != type) {
                    check = false;
                }
                int j = permutation[(iter + 1) & 3];
                if (segments[i][1] != segments[j][0]) {
                    check = false;
                }
            }
            if (check) {
                found = true;
            }
        } while (next_permutation(permutation, permutation + 4));
        for (int i = 0; i < 4; ++ i) {
            if (mask >> i & 1) {
                swap(segments[i][0], segments[i][1]);
            }
        }
    }
    puts(found? "YES": "NO");
    return 0;
}
