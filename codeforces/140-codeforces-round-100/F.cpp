// Codeforces Round #100
// Problem F -- New Year Snowflake
#include <cstdio>
#include <cstring>
#include <queue>
#include <vector>
#include <set>
#include <map>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::vector;

struct Point {
    int x, y;

    Point(int x, int y): x(x), y(y) {}

    Point &negate() {
        return *this = Point(-x, -y);
    }

    Point &operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }
};

bool operator <(const Point &a, const Point &b) {
    return a.x < b.x || a.x == b.x && a.y < b.y;
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

Point operator -(Point a, const Point &b) {
    return a -= b;
}

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

int n, m;
vector <Point> points, reversed_points;

const int N = 200000;
const int M = 10;

int main() {
    std::cin >> n >> m;
    for (int i = 0; i < n; ++ i) {
        Point point(0, 0);
        std::cin >> point;
        points.push_back(point);
    }
    std::sort(points.begin(), points.end());
    reversed_points = points;
    std::reverse(reversed_points.begin(), reversed_points.end());
    for (int i = 0; i < n; ++ i) {
        reversed_points[i].negate();
    }
    if (n <= m) {
        puts("-1");
    } else {
        vector <Point> answer;
        for (int i = 0; i <= m; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                Point delta = points[i] - reversed_points[j];
                int miss = 0;
                for (int x = 0, y = 0; x < n && miss <= m; ++ x) {
                    if (n - x > n - y + m - miss) {
                        miss = m + 1;
                    }
                    Point need = points[x] - delta;
                    while (y < n && reversed_points[y] < need) {
                        y ++;
                    }
                    if (y < n && reversed_points[y] == need) {
                        y ++;
                    } else {
                        miss ++;
                    }
                }
                if (miss <= m) {
                    answer.push_back(delta);
                }
            }
        }
        std::sort(answer.begin(), answer.end());
        answer.erase(std::unique(answer.begin(), answer.end()), answer.end());
        printf("%d\n", (int)answer.size());
        foreach (iter, answer) {
            printf("%.1f %.1f\n", iter->x / 2.0, iter->y / 2.0);
        }
    }
    return 0;
}
