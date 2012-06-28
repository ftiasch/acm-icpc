// SGU 192 -- RGB
#include <cmath>
#include <cstdio>
#include <cstring>
#include <string>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 300;
const double EPS = 1e-9;
const string COLORS = "RGB";

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    double x, y;

    Point(double x = 0, double y = 0): x(x), y(y) {}
};

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

Point operator *(const Point &a, double k) {
    return Point(a.x * k, a.y * k);
}

Point operator /(const Point &a, double k) {
    return Point(a.x / k, a.y / k);
}

double det(const Point &a, const Point &b) {
    return a.x * b.y - b.x * a.y;
}

Point intersect(const Point &a, const Point &b, const Point &c, const Point &d) {
    double areaABC = det(b - a, c - a);
    double areaABD = det(b - a, d - a);
    return (c * areaABD - d * areaABC) / (areaABD - areaABC);
}

int n, color[N];
double length[3];
Point ends[N][2];

double getY(int i, double x_0) {
    return ends[i][0].y + (x_0 - ends[i][0].x) / (ends[i][1].x - ends[i][0].x) * (ends[i][1].y - ends[i][0].y);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            scanf("%lf%lf", &ends[i][j].x, &ends[i][j].y);
        }
        if (sgn(ends[i][0].x - ends[i][1].x) > 0) {
            swap(ends[i][0], ends[i][1]);
        }
        char buffer[2];
        scanf("%s", buffer);
        color[i] = COLORS.find(*buffer);
    }
    memset(length, 0, sizeof(length));
    for (int i = 0; i < n; ++ i) {
        if (sgn(ends[i][0].x - ends[i][1].x) == 0) {
            continue;
        }
        vector <pair <double, double> > intervals;
        for (int j = 0; j < n; ++ j) {
            if (i == j || sgn(ends[j][0].x - ends[j][1].x) == 0) {
                continue;
            }
            double begin = ends[j][0].x;
            double end = ends[j][1].x;
            if (sgn(det(ends[i][1] - ends[i][0], ends[j][1] - ends[j][0])) == 0) {
                if (sgn(getY(j, 0) - getY(i, 0)) < 0) {
                    intervals.push_back(make_pair(begin, end));
                }
            } else {
                Point p = intersect(ends[i][0], ends[i][1], ends[j][0], ends[j][1]);
                if (sgn(getY(j, p.x - 1) - getY(i, p.x - 1)) < 0 && sgn(begin - p.x) < 0) {
                    intervals.push_back(make_pair(begin, min(p.x, end)));
                }
                if (sgn(getY(j, p.x + 1) - getY(i, p.x + 1)) < 0 && sgn(p.x - end) < 0) {
                    intervals.push_back(make_pair(max(p.x, begin), end));
                }
            }
        }
        double left = ends[i][1].x - ends[i][0].x;
        sort(intervals.begin(), intervals.end());
        if ((int)intervals.size() > 0) {
            double begin = intervals.front().first;
            double end = intervals.front().second;
            for (int k = 1; k < (int)intervals.size(); ++ k) {
                if (sgn(intervals[k].first - end) <= 0) {
                    end = max(end, intervals[k].second);
                } else {
                    left -= max(min(end, ends[i][1].x) - max(begin, ends[i][0].x), 0.0);
                    begin = intervals[k].first;
                    end = intervals[k].second;
                }
            }
            left -= max(min(end, ends[i][1].x) - max(begin, ends[i][0].x), 0.0);
        }
        length[color[i]] += left;
    }
    for (int i = 0; i < 3; ++ i) {
        printf("%c %.2f\n", COLORS[i], length[i]);
    }
    return 0;
}
