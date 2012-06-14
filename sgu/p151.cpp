// SGU 151 -- Construct a triangle
#include <cmath>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const double EPS = 1e-8;

double c, b, m;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

double my_sqrt(double x) {
    return sqrt(max(x, 0.0));
}

double fix(double x) {
    if (sgn(x) == 0) {
        return 0;
    }
    return x;
}

int main() {
    scanf("%lf%lf%lf", &c, &b, &m);
    if (sgn(abs(b - c) / 2 - m) <= 0 && sgn(m - (b + c) / 2) <= 0) {
        double a = my_sqrt(2 * (b * b + c * c - 2 * m * m));
        double x = fix((c * c - b * b) / (2 * a));
        double h = my_sqrt(b * b - (a / 2 - x) * (a / 2 - x));
        printf("%.5f %.5f\n%.5f %.5f\n%.5f %.5f\n", x, h, fix(-a / 2), 0.0, fix(a / 2), 0.0);
    } else {
        puts("Mission impossible");
    }
    return 0;
}
