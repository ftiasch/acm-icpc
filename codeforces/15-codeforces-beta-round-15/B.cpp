// Codeforces Beta Round #15
// Problem B -- Laser
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

using std::min;
using std::max;

long long range(int x_1, int x_2, int x_min, int x_max) {
    return max((min(x_1, x_2) + x_max) - (max(x_1, x_2) + x_min) + 1, 0);
}

int main() {
    int t;
    scanf("%d", &t);
    while (t --) {
        int n, m, x_1, y_1, x_2, y_2;
        scanf("%d%d%d%d%d%d", &n, &m, &x_1, &y_1, &x_2, &y_2);
        // 1 <= x_i + delta_x <= n
        int x_min = 1 - min(x_1, x_2);
        int x_max = n - max(x_1, x_2);
        int y_min = 1 - min(y_1, y_2);
        int y_max = m - max(y_1, y_2);
        std::cout << (long long)n * m - 2LL * (x_max - x_min + 1) * (y_max - y_min + 1) + range(x_1, x_2, x_min, x_max) * range(y_1, y_2, y_min, y_max) << std::endl;
    }
    return 0;
}
