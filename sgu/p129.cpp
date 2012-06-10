// SGU 129 -- Inheritance
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const double EPS = 1e-8;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}

    double norm() const {
        return sqrt(max(x * x + y * y, 0));
    }
};

ostream &operator <<(ostream &out, const Point &p) {
    return out << "(" << p.x << ", " << p.y << ")";
}

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

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

const int N = 500;

int n, m;
Point origin, points[N], hull[N];

bool compare(const Point &a, const Point &b) {
    int ret = det(a - origin, b - origin);
    if (ret == 0) {
        return sgn((a - origin).norm() - (b - origin).norm()) < 0;
    }
    return ret > 0;
}


double solve(const Point &a, const Point &b) {
    double lambda_min = 0;
    double lambda_max = 1;
    for (int i = 0; i < m; ++ i) {
        Point p = hull[i];
        Point q = hull[(i + 1) % m];
        if (det(q - p, a - p) <= 0 && det(q - p, b - p) <= 0) {
            return 0;
        }
        if (det(q - p, b - a) != 0) {
            double s_1 = det(q - p, a - p);
            double s_2 = det(q - p, b - p);
            double lambda = s_1 / (s_1 - s_2);
//printf("%d: %.2f\n", i, lambda);
            if (sgn(lambda - 0) >= 0 && sgn(lambda - 1) <= 0) {
                if (det(q - p, a - p) > 0) {
                    lambda_max = min(lambda_max, lambda);
                }
                if (det(q - p, b - p) > 0) {
                    lambda_min = max(lambda_min, lambda);
                }
            }
        }
    }
//printf("%.2f, %.2f\n", lambda_min, lambda_max);
    return (b - a).norm() * max(lambda_max - lambda_min, 0.0);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &points[i].x, &points[i].y);
    }
    origin = *min_element(points, points + n);
    sort(points, points + n, compare);
    m = 0;
    for (int i = 0; i < n; ++ i) {
        while (m >= 2 && det(hull[m - 1] - hull[m - 2], points[i] - hull[m - 1]) <= 0) {
            m --;
        }
        hull[m ++] = points[i];
    }
//for (int i = 0; i < m; ++ i) {
//    cout << hull[i] << endl;
//}
    int query_count;
    scanf("%d", &query_count);
    while (query_count > 0) {
        query_count --;
        Point a, b;
        scanf("%d%d%d%d", &a.x, &a.y, &b.x, &b.y);
        double ret = solve(a, b);
        if (fabs(ret) < EPS) {
            ret = 0;
        }
        printf("%.2f\n", ret);
    }
    return 0;
}
