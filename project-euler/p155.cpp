#include <algorithm>
#include <cstdio>
#include <set>
#include <vector>

int gcd(int a, int b)
{
    return b ? gcd(b, a % b) : a;
}

struct Fraction
{
    Fraction(int p_, int q_) : p(p_), q(q_)
    {
        auto g = gcd(p, q);
        p /= g;
        q /= g;
    }

    int p, q;
};

Fraction serial(const Fraction& a, const Fraction& b)
{
    return Fraction(a.p * b.q + b.p * a.q, a.q * b.q);
}

Fraction parallel(const Fraction& a, const Fraction& b)
{
    if (!a.p) {
        return b;
    }
    return Fraction(a.p * b.p, a.p * b.q + b.p * a.q);
}

bool operator < (const Fraction& a, const Fraction& b)
{
    return a.p < b.p || a.p == b.p && a.q < b.q;
}

const int MAX_N = 19;

std::set<Fraction> existed[2];
std::vector<std::pair<int, Fraction>> fractions[2];

int T, N;

void dfs(int n, int s, Fraction f)
{
    if (n) {
        for (int i = s; i < (int)fractions[T].size() && fractions[T][i].first <= n; ++ i) {
            dfs(n - fractions[T][i].first, i, (T ? serial : parallel)(f, fractions[T][i].second));
        }
    } else {
        if (existed[T ^ 1].insert(f).second) {
            fractions[T ^ 1].emplace_back(N, f);
        }
    }
}

int main()
{
    Fraction unit {1, 1};
    for (int t = 0; t < 2; ++ t) {
        existed[t].emplace(unit);
        fractions[t].emplace_back(1, unit);
    }
    for (auto n = 2; n < MAX_N; ++ n) {
        for (T = 0; T < 2; ++ T) {
            ::N = n;
            dfs(n, 0, Fraction(0, 1));
        }
    }
    auto all = existed[0];
    for (auto&& f : existed[1]) {
        all.emplace(f);
    }
    printf("%d\n", (int)all.size());
}
