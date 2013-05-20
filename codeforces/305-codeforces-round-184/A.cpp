// Codeforces Round #184
// Problem A -- Strange Addition
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 100;

int n, a[N], has[N], maximum[N + 1][1 << 3];

void update(int &x, int a) {
    x = std::max(x, a);
}

int main() {
    scanf("%d", &n);
    memset(has, 0, sizeof(has));
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        for (int k = 0, now = a[i]; k < 3; ++ k) {
            has[i] |= (now % 10 != 0) << k;
            now /= 10;
        }
    }
    memset(maximum, 0, sizeof(maximum));
    for (int i = n; i >= 1; -- i) {
        for (int mask = 0; mask < 1 << 3; ++ mask) {
            update(maximum[i - 1][mask], maximum[i][mask]);
            if (!(mask & has[i - 1])) {
                update(maximum[i - 1][mask | has[i - 1]], maximum[i][mask] + 1);
            }
        }
    }
    int mask = std::max_element(maximum[0], maximum[0] + (1 << 3)) - maximum[0];
    printf("%d\n", maximum[0][mask]);
    bool first = true;
    for (int i = 0; i < n; ++ i) {
        if (maximum[i + 1][mask] != maximum[i][mask]) {
            if (first) {
                first = false;
            } else {
                putchar(' ');
            }
            printf("%d", a[i]);
            mask &= ~has[i];
        }
    }
    return 0;
}
