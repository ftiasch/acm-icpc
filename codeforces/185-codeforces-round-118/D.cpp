// Codeforces Round #118
// Problem D -- Visit of the Great
#include <cassert>
#include <cstdio>
#include <cstring>
#include <utility>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long Long;

Long pow_mod(Long a, Long n, Long m) {
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

bool is_prime(Long p) {
    return true;
    if (p < 2) {
        return false;
    }
    for (int i = 2; i * i <= p; ++ i) {
        if (p % i == 0) {
            return false;
        }
    }
    return true;
}

Long inverse(Long a, Long p) {
    assert(__gcd(a, p) == 1 && is_prime(p));
    return pow_mod(a, p - 2, p);
}

pair <Long, Long> fast_sigma(Long a, Long n, Long p) {
    if (n == 0) {
        return make_pair(1, 0);
    }
    pair <Long, Long> tmp = fast_sigma(a, n >> 1, p);
    tmp = make_pair(tmp.first * tmp.first % p, tmp.second * (tmp.first + 1) % p);
    if ((n & 1) == 1) {
        return make_pair(tmp.first * a % p, (tmp.second + tmp.first) % p);
    }
    return tmp;
}

// a^0 + a^1 + ... + a^{n - 1)
Long sigma_small(Long a, Long n, Long p) {
    return fast_sigma(a, n, p).second;
}

// a^0 + a^1 + ... + a^{2^n - 1}
Long sigma(Long a, Long n, Long p) {
    if (n == 0) {
        return 0;
    }
    if (a % p == 0) {
        return 1;
    }
    // gcd(a, p) == 1
    // p - 1
    Long remainder = pow_mod(2LL, n, p - 1);
    Long part = (pow_mod(2LL, n, p) + p - remainder) * (p - 1) % p;
    Long result = sigma_small(a, remainder, p);
    Long whole = sigma_small(a, p - 1, p);
    result = (result + part * whole) % p;
    return result;
}

Long solve(Long k, Long l, Long r, Long p) {
    Long gcd = 1 + (k & 1);
    if (p == 2) {
        return gcd % p;
    }
    Long n = r - l + 1;
    Long real_base = 0;
    if (k % p == 0) {
        real_base = 0;
    } else {
        real_base = pow_mod(k, pow_mod(2LL, l, p - 1), p);
    }
    return sigma(real_base, n, p) * pow_mod(inverse(gcd, p), n - 1, p) % p;
}

int main() {
    int test_count;
    cin >> test_count;
    while (test_count > 0) {
        test_count --;
        int k, p;
        long long l, r;
        cin >> k >> l >> r >> p;
        cout << solve(k, l, r, p) << endl;
    }
    return 0;
}
