// Codeforces Round #153
// Problem A -- Points on Line
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, d, a[N];

int main() {
    scanf("%d%d", &n, &d);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    long long ways = 0;
    for (int i = 0; i < n; ++ i) {
        int size = std::upper_bound(a, a + n, a[i] + d) - a - i - 1;
        if (size >= 2) {
            ways += (long long)size * (size - 1) / 2;
        }
    }
    std::cout << ways << std::endl;
    return 0;
}
