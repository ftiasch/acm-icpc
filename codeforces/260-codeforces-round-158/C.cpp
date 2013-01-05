// Codeforces Round #158
// Problem C -- Balls and Boxes
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, x;
long long a[N];

int main() {
    std::cin >> n >> x;
    x --;
    for (int i = 0; i < n; ++ i) {
        std::cin >> a[i];
    }
    int minimum = *std::min_element(a, a + n);
    int y = x;
    while (a[y] != minimum) {
        y = (y + n - 1) % n;
    }
    long long turns = a[y];
    long long total = turns * n;
    for (int i = 0; i < n; ++ i) {
        a[i] -= turns;
    }
    for (int i = y; i != x; ) {
        i = (i + 1) % n;
        total ++;
        a[i] --;
    }
    a[y] = total;
    for (int i = 0; i < n; ++ i) {
        std::cout << a[i] << (i == n - 1 ? '\n' : ' ');
    }
    return 0;
}
