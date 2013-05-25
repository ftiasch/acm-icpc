// Codeforces Beta Round #16
// Problem C -- Monitor
#include <cstdio>
#include <cstring>
#include <algorithm>

int a, b, x, y;

int main() {
    scanf("%d%d%d%d", &a, &b, &x, &y);
    int g = std::__gcd(x, y);
    x /= g;
    y /= g;
    int k = std::min(a / x, b / y);
    printf("%d %d\n", x * k, y * k);
    return 0;
}
