#include <cstdio>

int main()
{
    int n, k;
    double l, v1, v2;
    scanf("%d%lf%lf%lf%d", &n, &l, &v1, &v2, &k);
    (n += k - 1) /= k;
    auto v = 1. / (n / (v2 - v1) + (n - 1.) / (v2 + v1));
    printf("%.9f\n", l / (v + v1));
}
