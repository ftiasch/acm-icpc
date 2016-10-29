#include <atomic>
#include <cstdio>
#include <vector>
#include <thread>

using LL = long long;

inline LL mul_mod(LL a, LL b, LL mod) {
    return __int128(a) * b % mod;
}

LL pow_mod_LL(LL a, LL n, LL m) {
    LL res = 1;
    for (a %= m; n; n >>= 1) {
        if (n & 1) res = mul_mod(res, a, m);
        a = mul_mod(a, a, m);
    }
    return res;
}

// 用miller rabin素数测试判断n是否为质数
bool is_prime(LL n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (~n & 1) return false;
    const int u[] = {2,3,5,7,325,9375,28178,450775,9780504,1795265022,0};
    LL e = n - 1, a, c = 0; // 原理：http://miller-rabin.appspot.com/
    while (~e & 1) e >>= 1, ++c;
    for (int i = 0; u[i]; ++i) {
        if (n <= u[i]) return true;
        a = pow_mod_LL(u[i], e, n);
        if (a == 1) continue;
        for (int j = 1; a != n - 1; ++j) {
            if (j == c) return false;
            a = mul_mod(a, a, n);
        }
    }
    return true;
}

bool check(LL n)
{
    for (auto&& d : std::vector<int>{3, 7, 13, 27}) {
        if (n % d == 0) {
            return false;
        }
    }
    n *= n;
    for (auto&& d : std::vector<int>{1, 3, 7, 9, 13, 27}) {
        if (!is_prime(n + d)) {
            return false;
        }
    }
    int last = 1;
    for (auto&& d : std::vector<int>{1, 3, 7, 9, 13, 27}) {
        for (int i = last + 1; i < d; ++ i) {
            if (is_prime(n + i)) {
                return false;
            }
        }
        last = d;
    }
    return true;
}

int main()
{
    const int N = 150000000;
    std::vector<std::thread> threads;
    std::atomic_int result(0);
    for (int t = 0; t < 4; ++ t) {
        threads.emplace_back([t, &result]()
        {
            int local_result = 0;
            for (int i = t; i < N; i += 4) {
                if (check(i)) {
                    local_result += i;
                }
                if (i % 1000000 == t) {
                    fprintf(stderr, "=> %d\n", i);
                }
            }
            result += local_result;
        });
    }
    for (auto& t : threads) {
        t.join();
    }
    printf("%d\n", result.load());
}
