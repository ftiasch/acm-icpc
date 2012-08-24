// Timus 1132 -- Square Root
#include <cassert>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

typedef long long Long;

Long powMod(Long a, Long n, Long m) {
    Long result = 1 % m;
    while (n > 0) {
        if ((n & 1) == 1) {
            result = result * a % m;
        }
        a = a * a % m;
        n >>= 1;
    }
    return result;
}

Long legendre(Long a, Long p) {
    return powMod(a, (p - 1) >> 1, p);
}

Long mod(Long a, Long m) {
    a %= m;
    if (a < 0) {
        a += m;
    }
    return a;
}

struct Combination {
    static Long p, omega;

    Long a, b;

    Combination(Long a, Long b): a(a % p), b(b % p) {}
};

Combination operator *(const Combination &p, const Combination &q) {
    int m = Combination::p;
    return Combination(p.a * q.a + p.b * q.b % m * Combination::omega,
                       p.a * q.b + q.a * p.b);
}

Combination powMod(Combination a, Long n) {
    Combination result(1, 0);
    while (n > 0) {
        if ((n & 1) == 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

Long Combination::p, Combination::omega;

Long solve(Long a, Long p) {
    if (p == 2) {
        return 1;
    }
    if (legendre(a, p) + 1 == p) {
        return -1;
    }
    if ((((p + 1) >> 1) & 1) == 0) {
        return powMod(a, (p + 1) >> 2, p);
    }
    Long a_0 = -1;
    while (true) {
        a_0 = rand() % p;
        if (legendre(mod(a_0 * a_0 - a, p), p) + 1 == p) {
            break;
        }
    }
    Combination::p = p;
    Combination::omega = mod(a_0 * a_0 - a, p);
//printf("%lld\n", Combination::omega);
    Combination ret = powMod(Combination(a_0, 1), (p + 1) >> 1);
    assert(ret.b == 0);
    return ret.a;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int a, p;
        scanf("%d%d", &a, &p);
        a %= p;
        int x_0 = solve(a, p);
        if (x_0 == -1) {
            puts("No root");
        } else {
            if (p - x_0 < x_0) {
                x_0 = p - x_0;
            }
            printf("%d", x_0);
            if (p - x_0 != x_0) {
                printf(" %d\n", p - x_0);
            } else {
                puts("");
            }
        }
    }
    return 0;
}
