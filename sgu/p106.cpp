// SGU 106 -- The equation
#include <cstdio>
#include <cassert>
#include <utility>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

const Long INF = 1000000000LL;

Long gcd(Long a, Long b) {
    return __gcd(a, b);
}

struct Fraction {
    Long p, q;

    Fraction(Long a = 0, Long b = 1): p(a), q(b) {
        Long d = gcd(p, q);
        p /= d;
        q /= d;
        if (q < 0) {
            p *= -1;
            q *= -1;
        }
    }

    Fraction negate() const {
        return Fraction(-p, q);
    }

    Fraction reciprocal() const {
        return Fraction(q, p);
    }

    int sgn() {
        return p < 0? -1: p > 0;
    }

    Long round_down() {
        if (p >= 0) {
            return p / q;
        }
        return (p - (q - 1)) / q;
    }

    Long round_up() {
        if (p >= 0) {
            return (p + (q - 1)) / q;
        }
        return p / q;
    }

    const char* show() {
        char *buffer = new char[22];
        sprintf(buffer, "%lld/%lld", p, q);
        return buffer;
    }
};

bool operator <(const Fraction &a, const Fraction &b) {
    return a.p * b.q < b.p * a.q;
}

Fraction operator +(const Fraction &a, const Fraction &b) {
    return Fraction(a.p * b.q + b.p * a.q, a.q * b.q);
}

Fraction operator -(const Fraction &a, const Fraction &b) {
    return a + b.negate();
}

Fraction operator *(const Fraction &a, const Fraction &b) {
    return Fraction(a.p * b.p, a.q * b.q);
}

Fraction operator /(const Fraction &a, const Fraction &b) {
    return a * b.reciprocal();
}

void gcd(Long a, Long b, Long &x, Long &y) {
    if (b == 0) {
        x = 1, y = 0;
    } else {
        gcd(b, a % b, y, x);
        y -= a / b * x;
    }
}

pair <Fraction, bool> solve_inequality(Fraction a, Fraction b) { // ax <= b
    assert(a.sgn() != 0);
    if (a.sgn() > 0) {
        return make_pair(b / a, true);
    }
    return make_pair(b / a, false);
}

void update(Fraction &t_min, Fraction &t_max, pair <Fraction, bool> ret) {
    if (ret.second) {
        t_max = min(t_max, ret.first);
    } else {
        t_min = max(t_min, ret.first);
    }
}

Long solve(Long a, Long b, Long c, Long x_min, Long x_max, Long y_min, Long y_max) {
    if (x_min > x_max || y_min > y_max) {
        return 0;
    }
    if (a == 0 && b == 0) {
        if (c != 0) {
            return 0;
        }
        return (x_max - x_min + 1) * (y_max - y_min + 1);
    }
    Long d = gcd(a, b);
    if (c % d != 0) {
        return 0;
    }
    Long x_0, y_0;
    gcd(a, b, x_0, y_0);
    Long k = c / d;
    x_0 *= k;
    y_0 *= k;
    // a x_0 + b y_0 = d
    // x = x_0 + (b / d) t
    // y = y_0 - (a / d) t
    Fraction t_max(INF);
    Fraction t_min(-INF);
    if (Fraction(b, d).sgn() == 0) {
        if (x_0 < x_min || x_max < x_0) {
            return 0;
        }
    } else {
        update(t_min, t_max, solve_inequality(Fraction(b, d), Fraction(x_max - x_0)));
        update(t_min, t_max, solve_inequality(Fraction(-b, d), Fraction(x_0 - x_min)));
    }
    if (Fraction(a, d).sgn() == 0) {
        if (y_0 < y_max || y_max < y_0) {
            return 0;
        }
    } else {
        update(t_min, t_max, solve_inequality(Fraction(-a, d), Fraction(y_max - y_0)));
        update(t_min, t_max, solve_inequality(Fraction(a, d), Fraction(y_0 - y_min)));
    }
    return t_max.round_down() - t_min.round_up() + 1;
}

int main() {
    Long a, b, c, x_min, x_max, y_min, y_max;
    cin >> a >> b >> c >> x_min >> x_max >> y_min >> y_max;
    cout << solve(a, b, -c, x_min, x_max, y_min, y_max) << endl;
    return 0;
}
