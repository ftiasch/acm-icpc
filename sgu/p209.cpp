// SGU 209 -- Areas
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const double EPS = 1e-8;

int signum(double x) {
    return x < -EPS? -1: x > EPS;
}

bool equality(double x, double y) {
    return signum(x - y) == 0;
}

struct Point {
    double x, y;

    Point(double x = 0.0, double y = 0.0): x(x), y(y) {}

    double arg() const {
        return atan2(y, x);
    }

    double norm() const {
        return sqrt(x * x + y * y);
    }

    Point normalize() const {
        double d = norm();
        return Point(x / d, y / d);
    }
};

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

bool operator <(const Point &a, const Point &b) {
    if (signum(a.x - b.x) == 0) {
        return signum(a.y - b.y) < 0;
    }
    return signum(a.x - b.x) < 0;
}

bool operator ==(const Point &a, const Point &b) {
    return signum(a.x - b.x) == 0 && signum(a.y - b.y) == 0;
}

Point operator +(const Point &a, const Point &b) {
    return Point(a.x + b.x, a.y + b.y);
}

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

Point operator *(const Point &a, double k) {
    return Point(a.x * k, a.y * k);
}

Point operator /(const Point &a, double k) {
    return Point(a.x / k, a.y / k);
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

struct Line {
    Point a, b;

    Point dir() const {
        return b - a;
    }
};

istream &operator >>(istream &in, Line &l) {
    return in >> l.a >> l.b;
}

Point intersect(const Line &u, const Line &v) {
    double s_1 = det(u.a - v.a, v.b - v.a);
    double s_2 = det(u.b - v.a, v.b - v.a);
    return (u.a * s_2 - u.b * s_1) / (s_2 - s_1);
}

const int N = 80;
const int M = N * N * 2;

int n, edgeCount, firstEdge[M], to[M], nextEdge[M], next[M];
bool visit[M];
Line lines[N];
vector <Point> points;

int getPointID(const Point &p) {
    return lower_bound(points.begin(), points.end(), p) - points.begin();
}

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        cin >> lines[i];
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            if (signum(det(lines[i].dir(), lines[j].dir())) != 0) {
                points.push_back(intersect(lines[i], lines[j]));
            }
        }
    }
    sort(points.begin(), points.end());
    points.erase(unique(points.begin(), points.end()), points.end());
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n; ++ i) {
        vector <double> lambdas;
        Point d = lines[i].dir().normalize();
        for (int j = 0; j < n; ++ j) {
            if (signum(det(lines[i].dir(), lines[j].dir())) != 0) {
                Point p = intersect(lines[i], lines[j]);
                lambdas.push_back(dot(p - lines[i].a, d));
            }
        }
        sort(lambdas.begin(), lambdas.end());
        lambdas.erase(unique(lambdas.begin(), lambdas.end(), equality), lambdas.end());
        for (int j = 1; j < (int)lambdas.size(); ++ j) {
            int u = getPointID(lines[i].a + d * lambdas[j - 1]);
            int v = getPointID(lines[i].a + d * lambdas[j]);
            addEdge(u, v);
            addEdge(v, u);
        }
    }
    memset(next, -1, sizeof(next));
    for (int i = 0; i < (int)points.size(); ++ i) {
        vector <pair <double, int> > adjacent;
        for (int iter = firstEdge[i]; iter != -1; iter = nextEdge[iter]) {
            adjacent.push_back(make_pair((points[to[iter]] - points[i]).arg(), iter));
        }
        sort(adjacent.begin(), adjacent.end());
        for (int j = 0; j < (int)adjacent.size(); ++ j) {
            next[adjacent[(j + 1) % (int)adjacent.size()].second ^ 1] = adjacent[j].second;
        }
    }
    memset(visit, 0, sizeof(visit));
    vector <double> areas;
    for (int i = 0; i < edgeCount; ++ i) {
        if (!visit[i]) {
            vector <int> boundary;
            int j = i;
            do {
                if (!boundary.empty() && (boundary.back() ^ j) == 1) {
                    boundary.pop_back();
                } else {
                    boundary.push_back(j);
                }
                visit[j] = true;
                j = next[j];
            } while (!visit[j]);
            if (i == j) {
                double area = 0;
                for (int k = 0; k < (int)boundary.size(); ++ k) {
                    area += det(points[to[boundary[k] ^ 1]], points[to[boundary[k]]]);
                }
                area /= 2.0;
                if (signum(area) > 0) {
                    areas.push_back(area);
                }
            }
        }
    }
    sort(areas.begin(), areas.end());
    printf("%d\n", (int)areas.size());
    for (int i = 0; i < (int)areas.size(); ++ i) {
        printf("%.4f\n", areas[i]);
    }
    return 0;
}
