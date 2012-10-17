// Codeforces Beta Round #93
// Problem A -- Wasted Time
#include <cmath>
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100;

int n, k, x[N], y[N];

int sqr(int x) {
    return x * x;
}

int main() {
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    double result = 0.0;
    for (int i = 0; i + 1 < n; ++ i) {
        result += sqrt(sqr(x[i + 1] - x[i]) + sqr(y[i + 1] - y[i]));
    }
    printf("%.10f\n", result / 50 * k);
    return 0;
}
