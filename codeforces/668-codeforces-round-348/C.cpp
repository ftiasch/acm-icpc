#include <algorithm>
#include <cmath>
#include <cstdio>
#include <tuple>
#include <utility>
#include <vector>

std::pair<double, double> solve(double b, double c)
{
    double d = sqrt(std::max(b * b - 4 * c, 0.));
    return {.5 * (b - d), .5 * (b + d)};
}

int main()
{
    int n;
    scanf("%d", &n);
    std::vector<double> M(n + 1), m(n + 2);
    for (int i = 1; i <= n; ++ i) {
        scanf("%lf", &M[i]);
    }
    for (int i = 1; i <= n; ++ i) {
        scanf("%lf", &m[i]);
    }
    for (int i = n; i >= 1; -- i) {
        m[i] += m[i + 1];
    }
    std::vector<double> a(n + 1), b(n + 1);
    for (int i = 1; i <= n; ++ i) {
        M[i] += M[i - 1];
        double x, y;
        std::tie(x, y) = solve(M[i] - m[i + 1] + 1, M[i]);
        a[i] = x;
        b[i] = y;
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%.9f%c", a[i] - a[i - 1], " \n"[i == n]);
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%.9f%c", b[i] - b[i - 1], " \n"[i == n]);
    }
}
