#include <cstdio>

long power(long n, long m)
{
    long a = 2, result = 1;
    while (n) {
        if (n & 1) {
            result = result * a % m;
        }
        a = a * a % m;
        n >>= 1;
    }
    return result;
}

struct Mod
{
    Mod phi() const
    {
        Mod o(*this);
        if (exp[2]) {
            o.val = val / 7 * 6;
            o.exp[2] --, o.exp[1] ++, o.exp[0] ++;
        } else if (exp[1]) {
            o.val = val / 3 * 2;
            o.exp[1] --, o.exp[0] ++;
        } else if (exp[0]) {
            o.val = val / 2;
            o.exp[0] --;
        }
        return o;
    }

    long  val;
    short exp[3];
};

long tower(long n, const Mod& m)
{
    if (m.val == 1) {
        return 0;
    }
    if (n < 2) {
        static int fv[] {16, 65536};
        return fv[n] % m.val;
    }
    if (n == 2) {
        return power(65536, m.val);
    }
    auto phi = m.phi().val;
    return power(phi + tower(n - 1, m.phi()), m.val);
}

long A4(long n, const Mod& m)
{
    return (tower(n, m) + (m.val - 1) * 3) % m.val;
}

int main()
{
    Mod mod;
    mod.val = 1475789056;
    mod.exp[0] = mod.exp[2] = 8;
    mod.exp[1] = 0;

    const long BIG = 100;
    long result = 1;
    result += 3;
    result += 7;
    result += 61;
    result += A4(4, mod);
    result += A4(BIG, mod);
    result += A4(BIG, mod);
    printf("%ld\n", result % mod.val);
}
