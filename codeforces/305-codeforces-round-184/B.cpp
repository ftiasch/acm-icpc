// Codeforces Round #184
// Problem B -- Continued Fractions
#include <cstdio>
#include <iostream>
#include <algorithm>

typedef long long LL;

const int N = 90;

int n;
LL p, q, a[N];

bool check(LL p, LL q, int i) {
    if (i < n) {
        if (q == 0) {
            return false;
        }
        if (p / q < a[i]) {
            return false;
        }
        return check(q, p - a[i] * q, i + 1);
    } 
    return q == 0;
}

int main() {
    std::cin >> p >> q >> n;
    for (int i = 0; i < n; ++ i) {
        std::cin >> a[i];
    }
    puts(check(p, q, 0) ? "YES" : "NO");
    return 0;
}
