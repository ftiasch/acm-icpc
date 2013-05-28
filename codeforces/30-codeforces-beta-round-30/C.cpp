// Codeforces Beta Round #30
// Problem C -- Shooting Gallery
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 1000;

int n, x[N], y[N], t[N], order[N];
double p[N], maximum[N];

bool by_t(int i, int j) {
    return t[i] < t[j];
}

long long sqr(long long x) {
    return x * x;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d%lf", x + i, y + i, t + i, p + i);
        order[i] = i;
    }
    std::sort(order, order + n, by_t);
    for (int i = 0; i < n; ++ i) {
        maximum[order[i]] = 0.0;
        for (int j = 0; j < i; ++ j) {
            if (sqr(x[order[i]] - x[order[j]]) + sqr(y[order[i]] - y[order[j]]) <= sqr(t[order[i]] - t[order[j]])) {
                maximum[order[i]] = std::max(maximum[order[i]], maximum[order[j]]);
            }
        }
        maximum[order[i]] += p[order[i]];
    }
    printf("%.10f\n", *std::max_element(maximum, maximum + n));
    return 0;
}
