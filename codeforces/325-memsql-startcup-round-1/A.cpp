#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 5;

int n, x[N << 1], y[N << 1];

int main() {
    scanf("%d", &n);
    long long area = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d%d", x + (i << 1), y + (i << 1), x + (i << 1 | 1), y + (i << 1 | 1));
        area += (x[i << 1 | 1] - x[i << 1]) * (y[i << 1 | 1] - y[i << 1]);
    }
    int min_x = *std::min_element(x, x + (n << 1));
    int max_x = *std::max_element(x, x + (n << 1));
    int min_y = *std::min_element(y, y + (n << 1));
    int max_y = *std::max_element(y, y + (n << 1));
    if (max_x - min_x == max_y - min_y && area == (long long)(max_x - min_x) * (max_y - min_y)) {
        puts("YES");
    } else {
        puts("NO");
    }
    return 0;
}
