// Codeforces Beta Round #88
// Problem D -- Not Quick Transformation
#include <cstdio>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

Long mod;

struct Pair {
    Long length, sum;

    Pair(Long length, Long sum) : length(length), sum(sum) {}

    Pair oddMap() {
        return Pair(length, ((sum * 2 - length) % mod + mod) % mod );
    }

    Pair evenMap() {
        return Pair(length, sum * 2 % mod);
    }
};

Pair operator + (const Pair &a, const Pair &b) {
    return Pair(a.length + b.length, (a.sum + b.sum) % mod);
}

Pair solve(Long n, Long l, Long r, Long u, Long v) {
    if (r < 1 || n < l || v < 1 || n < u || v < u) {
        return Pair(0, 0);
    }
    if (l <= 1 && n <= r) {
        Long a = max(u, 1LL);
        Long b = min(v, n);
        Long length = b - a + 1;
        return Pair(length, ((length % (mod * 2)) * ((a + b) % (mod * 2)) / 2) % mod);
    }
    Long odd = n + 1 >> 1;
    return solve(odd, l, r, u + 2 >> 1, v + 1 >> 1).oddMap() + solve(n - odd, l - odd, r - odd, u + 1 >> 1, v >> 1).evenMap();
}

int main() {
    Long n, m;
    cin >> n >> m >> mod;
    for (int i = 0; i < m; ++ i) {
        Long l, r, u, v;
        cin >> l >> r >> u >> v;
        cout << solve(n, l, r, u, v).sum << endl;
    }
    return 0;
}
