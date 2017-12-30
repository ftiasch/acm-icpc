#include <algorithm>
#include <cmath>
#include <cstdio>

const int N = 1000;

int n, r, x[N];
double y[N];

int main()
{
    scanf("%d%d", &n, &r);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", x + i);
    }
    for (int i = 0; i < n; ++ i) {
        double& yy = y[i] = r;
        for (int j = 0; j < i; ++ j) {
            int d = std::abs(x[i] - x[j]);
            if (d <= r + r) {
                yy = std::max(yy, y[j] + sqrt(4 * r * r - d * d));
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%.8f\n", y[i]);
    }
}
