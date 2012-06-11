// SGU 136 -- Erasing Edges
#include <cmath>
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100000;
const double EPS = 1e-8;

int n;
bool no_solution;
double a[N], b[N], x[N], y[N];

void solve(double a[], double x[]) {
    int sign = 1;
    double delta = 0;
    for (int i = 0; i < n; ++ i) {
        sign *= -1;
        delta = 2 * a[i] - delta;
    }
    if (sign == 1 && fabs(delta) > EPS) {
        no_solution = true;
    } else {
        x[0] = sign == 1? 0: delta / 2;
        for (int i = 1; i < n; ++ i) {
            x[i] = 2 * a[i - 1] - x[i - 1];
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%lf%lf", a + i, b + i);
    }
    no_solution = false;
    solve(a, x);
    solve(b, y);
    if (no_solution) {
        puts("NO");
    } else {
        puts("YES");
        for (int i = 0; i < n; ++ i) {
            printf("%.3f %.3f\n", x[i], y[i]);
        }
    }
    return 0;
}
