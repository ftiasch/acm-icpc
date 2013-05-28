// Codeforces Beta Round #30
// Problem D -- Kings Problem?
#include <cmath>
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 100000 + 1;
int n, k, x[N], y[N];

long long sqr(long long x) {
    return x * x;
}

double dist(int i, int j) { 
    return sqrt(sqr(x[i] - x[j]) + sqr(y[i] - y[j]));
}

int main() {
    scanf("%d%d", &n, &k);
    memset(y, 0, sizeof(y));
    for (int i = 0; i < n; ++ i) {
        scanf("%d", x + i);
    }
    int xk = x[-- k];
    std::sort(x, x + n);
    scanf("%d%d", x + n, y + n);
    if (k == n) {
        printf("%.10f\n", dist(0, n - 1) + std::min(dist(n, 0), dist(n, n - 1)));
    } else {
        k = std::lower_bound(x, x + n, xk) - x;
        double answer = 1e100;
        answer = std::min(answer, dist(k, 0) + dist(0, n - 1) + dist(n - 1, n));
        answer = std::min(answer, dist(k, n - 1) + dist(n - 1, 0) + dist(0, n));
        for (int i = k; i + 1 < n; ++ i) {
            answer = std::min(answer, dist(0, i) + std::min(dist(0, k) + dist(i, n), dist(k, i) + dist(0, n)) + std::min(dist(n, i + 1), dist(n, n - 1)) + dist(i + 1, n - 1));
        }
        for (int i = 1; i <= k; ++ i) {
            answer = std::min(answer, dist(i, n - 1) + std::min(dist(i, k) + dist(n - 1, n), dist(k, n - 1) + dist(i, n)) + std::min(dist(n, 0), dist(n, i - 1)) + dist(0, i - 1));
        }
        printf("%.10f\n", answer);
    }
    return 0;
}
