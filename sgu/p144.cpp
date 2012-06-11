// SGU 144 -- Meeting
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

const double EPS = 1e-8;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    double x, y;

    Point(double x = 0, double y = 0): x(x), y(y) {}
};

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

double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

vector <Point> rebuild(vector <Point> area, const Point &a, const Point &b) {
    area.push_back(area.front());
    int n = (int)area.size();
    vector <Point> result;
    for (int i = 1; i < n; ++ i) {
        double s_1 = det(b - a, area[i] - a);
        double s_2 = det(b - a, area[i - 1] - a);
        if (sgn(s_1) * sgn(s_2) < 0) {
            result.push_back((area[i] * s_2 - area[i - 1] * s_1) / (s_2 - s_1));
        }
        if (sgn(s_1) >= 0) {
            result.push_back(area[i]);
        }
    }
    return result;
}

double get_area(vector <Point> area) {
    double result = 0;
    int n = (int)area.size();
    for (int i = 0; i < n; ++ i) {
        result += det(area[i], area[(i + 1) % n]);
    }
    return result / 2;
}

vector <Point> area;

int main() {
    double lower, upper, delta;
    scanf("%lf%lf%lf", &lower, &upper, &delta);
    delta /= 60;
    area.push_back(Point(lower, lower));
    area.push_back(Point(upper, lower));
    area.push_back(Point(upper, upper));
    area.push_back(Point(lower, upper));
    double total_area = get_area(area);
    area = rebuild(area, Point(delta, 0), Point(delta + 1, 1));
    area = rebuild(area, Point(1, delta + 1), Point(0, delta));
    printf("%.7f\n", get_area(area) / total_area);
    return 0;
}
