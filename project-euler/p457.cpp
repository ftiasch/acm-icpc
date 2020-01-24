#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

using namespace std;

typedef long long Long;

Long powMod(__int128 a, Long n, Long m) {
    __int128 result = 1 % m;
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

Long solve_qr(Long a, Long p) {
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

std::vector<int> get_primes(int n) {
    std::vector<bool> is_prime(n + 1, true);
    std::vector<int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p > n) {
                break;
            }
            is_prime[d * p] = false;
            if (d % p == 0) {
                break;
            }
        }
    }
    return primes;
}

Long solve_bf(int p)
{
    Long p2 = (Long)p * p;
    Long r = 1;
    while (r < p2 && (r * r - 3 * r - 1) % p2 != 0) {
        r ++;
    }
    return r < p2 ? r : 0;
}

Long solve_p2(int p)
{
    Long p2 = (Long)p * p;
    Long r0 = solve_qr(13, p);
    if (r0 == -1) {
        return 0;
    }
    assert(r0 * r0 % p == 13 % p);
    Long inv_2 = powMod(2, (p - 1LL) * p - 1, p2);
    Long result = p2;
    for (auto&& r : std::vector<Long>{r0, p - r0}) {
        if (r < p) {
            Long r2 = 13 + p2 - r * r;
            if (r2 % p != 0) {
                continue;
            }
            Long k = (Long)powMod(2 * r % p, p - 2, p) * (r2 / p) % p;
            Long x = (__int128)(k * p + r + 3) * inv_2 % p2;
            result = std::min(x, result);
        }
    }
    return result < p2 ? result : 0;
}

// const int N = 100;
const int N = 10000000;

int main()
{
    Long result = 0;
    for (auto&& p : get_primes(N)) {
        if (p > 2 && p != 13) {
            // assert(solve_bf(p) == solve_p2(p));
            result += solve_p2(p);
        }
        // fprintf(stderr, "%d\n", p);
    }
    printf("%lld\n", result);
}
