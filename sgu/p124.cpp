// SGU 124 -- Broken line
#include <cmath>
#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}
};

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        return a.y < b.y;
    }
    return a.x < b.x;
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

ostream &operator <<(ostream &out, Point &p) {
    return out << "(" << p.x << ", " << p.y << ")";
}

int det(const Point &a, const Point &b) {
    return a.x * b.y - b.x * a.y;
}

int dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

bool is_on_line(const Point &p, const Point &a, const Point &b) {
    return det(p - a, p - b) == 0 && dot(p - a, p - b) <= 0;
}

double arg(const Point &a, const Point &b) {
    return atan2(det(a, b), dot(a, b));
}

const int N = 10000;

int n;
Point segmenets[N][2];
vector <int> adjacent[N], polygon_id;
vector <Point> points, polygon;

int get_id(const Point &p) {
    return lower_bound(points.begin(), points.end(), p) - points.begin();
}

const double PI = acos(-1.0);
const double EPS = 1e-8;

int check(const Point &p) {
    double result = 0;
    for (int i = 0; i < n; ++ i) {
        Point a = polygon[i];
        Point b = polygon[(i + 1) % n];
        if (is_on_line(p, a, b)) {
            return 1;
        }
        result += arg(a - p, b - p);
    }
    return fabs(fabs(result) - 2 * PI) < EPS? 0: 2;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            cin >> segmenets[i][j];
            points.push_back(segmenets[i][j]);
        }
    }
    sort(points.begin(), points.end());
    points.erase(unique(points.begin(), points.end()), points.end());
    for (int i = 0; i < n; ++ i) {
        int a = get_id(segmenets[i][0]);
        int b = get_id(segmenets[i][1]);
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    polygon_id.push_back(0);
    while ((int)polygon_id.size() < n) {
        int size = (int)polygon_id.size();
        int a = size >= 2? polygon_id[size - 2]: -1;
        int b = polygon_id[size - 1];
        for (vector <int> :: iterator iter = adjacent[b].begin();
                iter != adjacent[b].end(); ++ iter) {
            int c = *iter;
            if (c != a) {
                polygon_id.push_back(c);
                break;
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        polygon.push_back(points[polygon_id[i]]);
    }
    Point p_0;
    cin >> p_0;
    int ret = check(p_0);
    if (ret == 0) {
        puts("INSIDE");
    } else if (ret == 1) {
        puts("BORDER");
    } else {
        puts("OUTSIDE");
    }
    return 0;
}
