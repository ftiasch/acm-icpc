#include <cassert>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int K = 10000;

int k;
long long n, m, a[K];

void extgcd(long long a, long long b, long long c, long long &x, long long &y) {
    if (b == 0) {
        x = c / a, y = 0;
    } else {
        extgcd(b, a % b, c, y, x);
        y -= a / b * x;
    }
}

bool check() {
    long long row = 1;
    for (int i = 0; i < k; ++ i) {
        if (row / std::__gcd(row, a[i]) > n / a[i]) {
            return false;
        }
        row *= a[i] / std::__gcd(row, a[i]);
    }
    long long remainder = 0;
    long long modulo = 1;
    for (int i = 0; i < k; ++ i) {
        if ((remainder + i) % std::__gcd(modulo, a[i]) != 0) {
            return false;
        }
        long long x, y;
        extgcd(modulo, a[i], -(remainder + i), x, y);
        remainder += x % a[i] * modulo;
        modulo *= a[i] / std::__gcd(modulo, a[i]);
        remainder %= modulo;
        assert((remainder + i) % a[i] == 0);
    }
    long long column = remainder;
    if (column <= 0) {
        column += modulo;
    }
    if (column + k > m + 1) {
        return false;
    }
    for (int i = 0; i < k; ++ i) {
        if (std::__gcd(row, column + i) != a[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    std::cin >> n >> m >> k;
    for (int i = 0; i < k; ++ i) {
        std::cin >> a[i];
    }
    puts(check() ? "YES" : "NO");
    return 0;
}
