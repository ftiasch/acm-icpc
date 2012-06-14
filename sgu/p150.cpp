// SGU 150 -- Mr. Beetle II
#include <cstdio>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

struct Point {
    Long x, y;

    Point(Long x = 0LL, Long y = 0LL): x(x), y(y) {}
};

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

ostream &operator <<(ostream &out, Point &p) {
    return out << p.x << " " << p.y;
}

Long gcd(Long a, Long b) {
    return b == 0? a: gcd(b, a % b);
}

Long det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

Point solve(const Point &a, Long n) {
    if (a.x + a.y - gcd(a.x, a.y) < n) {
        return Point(-1, -1);
    }
    Point p(1, 1);
    while (n > 1) {
        n --;
        Long ret = det(Point((p.x + 1) >> 1, (p.y + 1) >> 1), a);
        if (ret <= 0) {
            p.x += 2;
        }
        if (ret >= 0) {
            p.y += 2;
        }
    }
    return p;
}

int n;
Point p_1, p_2;

int main() {
    cin >> p_1 >> p_2 >> n;
    bool swap_x = p_1.x > p_2.x;
    bool swap_y = p_1.y > p_2.y;
    if (swap_x) {
        p_1.x = -p_1.x;
        p_2.x = -p_2.x;
    }
    if (swap_y) {
        p_1.y = -p_1.y;
        p_2.y = -p_2.y;
    }
    Point ret = solve(Point(abs(p_1.x - p_2.x), abs(p_1.y - p_2.y)), n);
    if (ret.x == -1 && ret.y == -1) {
        puts("no solution");
    } else {
        ret.x += p_1.x * 2;
        ret.y += p_1.y * 2;
        if (swap_x) {
            ret.x = -ret.x;
        }
        if (swap_y) {
            ret.y = -ret.y;
        }
        ret.x = ret.x >= 0? ret.x / 2: (ret.x - 1) / 2;
        ret.y = ret.y >= 0? ret.y / 2: (ret.y - 1) / 2;
        cout << ret << endl;
    }
    return 0;
}
