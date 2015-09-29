#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>

const int N = 100000 + 2;

int n, a[N], g[N], f[N], b[N];

void go(int *g, int *f, int s, int l) {
    std::fill(b, b + (n + 2), INT_MAX);
    for (int i = s + l; i <= n + 1; ++ i) {
        int j = i - l;
        for (int k = g[j]; k >= 0 && a[j] < b[k]; -- k) {
            b[k] = a[j];
        }
        b[g[j]] = std::min(b[g[j]], a[j]);
        j = std::lower_bound(b, b + (n + 2), a[i]) - b;
        f[i] = std::max(f[i], j);
    }
// for (int i = s + l; i <= n + 1; ++ i) {
//     printf("%d%c", f[i], " \n"[i == n + 1]);
// }
}

int main() {
    int T;
    scanf("%d", &T);
    for (int t = 1; t <= T; ++ t) {
        int l;
        scanf("%d%d", &n, &l);
        a[0]     = INT_MIN;
        a[n + 1] = INT_MAX;
        for (int i = 1; i <= n; ++ i) {
            scanf("%d", a + i);
        }
        std::fill(g, g + (n + 2), INT_MIN);
        std::fill(f, f + (n + 2), INT_MIN);
        g[0] = 0;
        go(g, g, 0,     1);
        go(g, f, 0,     l + 1);
        go(f, f, l + 1, 1);
        printf("Case #%d: %d\n", t, f[n + 1] - 1);
    }
    return 0;
}
