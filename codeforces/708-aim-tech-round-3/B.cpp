#include <iostream>
#include <vector>

using Long = long long;

std::vector<Long> sqrt(Long n)
{
    if (n == 0) {
        return {0, 1};
    }
    Long r = 2;
    while ((r + 1) * r <= n * 2) {
        r ++;
    }
    if (r * (r - 1) / 2 == n) {
        return {r};
    }
    return {};
}

bool solve()
{
    long long a00, a01, a10, a11;
    std::cin >> a00 >> a01 >> a10 >> a11;
    for (auto&& n : sqrt(a00 + a01 + a10 + a11)) {
        for (auto&& a : sqrt(a00)) {
            for (auto&& b : sqrt(a11)) {
                if (n == 0 || n != a + b || a * b < a01 || b * a < a10) {
                    continue;
                }
                for (int i = 0; i < n; ++ i) {
                    if (a > 0 && b <= a01 && (a - 1) * b >= a01 - b && b * (a - 1) >= a10) {
                        a01 -= b;
                        a --;
                        putchar('0');
                    } else {
                        a10 -= a;
                        b --;
                        putchar('1');
                    }
                }
                puts("");
                return true;
            }
        }
    }
    return false;
}

int main()
{
    freopen("B.in", "r", stdin);
    if (!solve()) {
        puts("Impossible");
    }
}
