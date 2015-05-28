#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

typedef std::vector <int> Poly;

const int MOD = (int)1e9;

void add(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

void operator += (Poly &a, const Poly &b)
{
    assert(a.size() >= b.size());
    for (int i = 0; i < (int)b.size(); ++ i) {
        add(a[i], b[i]);
    }
}

void operator -= (Poly &a, const Poly &b)
{
    assert(a.size() >= b.size());
    for (int i = 0; i < (int)b.size(); ++ i) {
        a[i] -= b[i];
        if (a[i] < 0) {
            a[i] += MOD;
        }
    }
}

void cut(const Poly &p, Poly &p0, Poly &p1)
{
    int n = (int)p.size() + 1 >> 1;
    p0 = Poly(p.begin(), p.begin() + n);
    p1 = Poly(p.begin() + n, p.end());
    if (p0.size() > p1.size()) {
        p1.push_back(0);
    }
}

void update(Poly &a, const Poly &b, int offset)
{
    for (int i = 0; i < (int)b.size(); ++ i) {
        if (b[i]) {
            assert(offset + i < (int)a.size());
            add(a[offset + i], b[i]);
        }
    }
}

Poly operator * (const Poly &a, const Poly &b)
{
    if ((int)a.size() == 1) {
        return Poly(1, (long long)a[0] * b[0] % MOD);
    } else {
        Poly a0, a1, b0, b1;
        cut(a, a0, a1);
        cut(b, b0, b1);
        int n = a0.size();
        Poly res((n << 2) - 1);
        Poly &&p00 = a0 * b0;
        update(res, p00, 0);
        Poly &&p11 = a1 * b1;
        update(res, p11, n + n);
        a0 += a1;
        b0 += b1;
        Poly p01 = a0 * b0;
        p01 -= p00;
        p01 -= p11;
        update(res, p01, n);
        return res;
    }
}

Poly multiply(int m, const Poly &a, const Poly &b)
{
    Poly c(m);
    Poly &&ref = a * b;
    for (int i = 0; i < (int)ref.size(); ++ i) {
        add(c[i % m], ref[i]);
    }
    return c;
}

Poly power(int m, Poly a, long long n)
{
    Poly res(m);
    res[0] = 1;
    while (n) {
        if (n & 1) {
            res = multiply(m, res, a);
        }
        a = multiply(m, a, a);
        n >>= 1;
    }
    return res;
}

std::vector <long long> divisors(long long n)
{
    std::vector <long long> res;
    for (long long d = 1; d * d <= n; ++ d) {
        if (n % d == 0) {
            res.push_back(d);
            res.push_back(n / d);
        }
    }
    std::sort(res.begin(), res.end());
    res.erase(std::unique(res.begin(), res.end()), res.end());
    return res;
}

int solve(long long n, int m)
{
    Poly w0(m);
    for (long long d : divisors(n)) {
        w0[d % m] ++;
    }
    return power(m, w0, n)[(m - n % m) % m];
}

int main()
{
    printf("%d\n", solve(3, 4));
    printf("%d\n", solve(4, 11));
    printf("%d\n", solve(1111, 24));
    printf("%d\n", solve(1234567898765LL, 4321)); // 935247012
    return 0;
}
