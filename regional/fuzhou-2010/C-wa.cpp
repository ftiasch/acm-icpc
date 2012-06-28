// Fuzhou 2010
// Problem C -- Shade of Hallelujah Mountain
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const double INF = 1e9;
const double EPS = 1e-8;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    double x, y, z;

    Point(double x = INF, double y = INF, double z = INF): x(x), y(y), z(z) {
    }

    void read() {
        scanf("%lf%lf%lf", &x, &y, &z);
    }

    void show() {
        printf("(%.2f, %.2f, %.2f)\n", x, y, z);
    }

    double norm() {
        return sqrt(x * x + y * y + z * z);
    }

    Point normalize() {
        double d = this->norm();
        x /= d;
        y /= d;
        z /= d;
        return *this;
    }
};

bool operator ==(const Point &a, const Point &b) {
    return sgn(a.x - b.x) == 0 && sgn(a.y - b.y) == 0 && sgn(a.z - b.z) == 0;
}

bool operator !=(const Point &a, const Point &b) {
    return !(a == b);
}

bool operator <(const Point &a, const Point &b) {
    if (sgn(a.x - b.x) == 0) {
        if (sgn(a.y - b.y) == 0) {
            return sgn(a.z - b.z) < 0;
        }
        return sgn(a.y - b.y) < 0;
    }
    return sgn(a.x - b.x) < 0;
}

Point operator +(const Point &a, const Point &b) {
    return Point(a.x + b.x, a.y + b.y, a.z + b.z);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y, a.z - b.z);
}

Point operator *(const Point &a, double k) {
    return Point(a.x * k, a.y * k, a.z * k);
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
}

Point det(const Point &a, const Point &b) {
    return Point(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
}

const int N = 222;

int n;
double a, b, c, d;
Point points[N];

Point get_intersection(const Point &o, const Point &p) {
    Point w = p - o;
    double det = a * w.x + b * w.y + c * w.z;
    if (sgn(det) == 0) {
        return Point();
    }
    double lambda = (d - a * o.x - b * o.y - c * o.z) / det;
    if (sgn(lambda) <= 0) {
        return Point();
    }
    return o + w * lambda;
}

bool check(const Point &a, const Point &b, const Point &c) {
    return sgn(det(b - a, c - b).z) < 0;
}

int main() {
    while (true) {
        scanf("%lf%lf%lf%lf", &a, &b, &c, &d);
        if (sgn(a) == 0 && sgn(b) == 0 && sgn(c) == 0 && sgn(d) == 0) {
            break;
        }
        scanf("%d", &n);
        for (int i = 1; i <= n; ++ i) {
            points[i].read();
        }
        points[0].read();
        bool infinity = false;
        for (int i = 1; i <= n; ++ i) {
            points[i] = get_intersection(points[0], points[i]);
//points[i].show();
            infinity |= points[i] == Point();
        }
        if (infinity) {
            puts("Infi");
        } else {
            if (n >= 3) {
                Point ZERO(0, 0, 0);
                Point base_x = ZERO;
                for (int i = 2; i <= n; ++ i) {
                    if (points[i] != points[1]) {
                        base_x = points[i] - points[1];
                    }
                }
                Point base_y = base_x;
                for (int i = 2; i <= n; ++ i) {
                    if (det(points[i] - points[1], base_x) != ZERO) {
                        base_y = points[i] - points[1];
                    }
                }
                if (base_x == ZERO || base_x == base_y) {
                    puts("0.00");
                } else {
                    Point base_z = det(base_x, base_y);
                    base_y = det(base_z, base_x);
                    base_x.normalize();
                    base_y.normalize();
                    for (int i = 2; i <= n; ++ i) {
                        points[i] = Point(dot(points[i] - points[1], base_x), dot(points[i] - points[1], base_y), 0);
                    }
                    points[1] = ZERO;
                    sort(points + 1, points + 1 + n);
                    for (int i = 1; i <= n; ++ i) {
                        points[2 * n - i + 1] = points[i];
                    }
                    vector <Point> hulls;
                    for (int i = 1; i <= 2 * n; ++ i) {
                        int s = hulls.size();
                        while (s >= 2 && !check(hulls[s - 2], hulls[s - 1], points[i])) {
                            s --;
                            hulls.pop_back();
                        }
                        hulls.push_back(points[i]);
                    }
                    double area = 0;
                    int m = hulls.size();
                    for (int i = 1; i < m; ++ i) {
                        area += det(hulls[i - 1], hulls[i]).z;
                    }
                    printf("%.2f\n", abs(area) / 2.0);
                }
            } else {
                puts("0.00");
            }
        }
    }
    return 0;
}
