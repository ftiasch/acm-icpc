// Project Euler 379 Least common multiple count
#include <algorithm>
#include <cassert>
#include <cstdio>
#include <iostream>

typedef long long Long;

const int N = 1000000;

int miu[N + 1];

Long solve_triple(Long n)
{
    Long result = 0;
    for (Long x = 1; x * x * x <= n; ++ x) {
        // if x == y
        result += 1 + 3 * (n / x / x - x);
        Long n2 = n / x;
        for (Long y = x + 1; y * y <= n2; ++ y) {
            result += 3 * (1 + 2 * (n2 / y - y));
        }
    }
    //for (Long x = 1; x <= n; ++ x) {
    //    for (Long y = 1; x * y <= n; ++ y) {
    //        for (Long z = 1; x * y * z <= n; ++ z) {
    //            result --;
    //        }
    //    }
    //}
    //assert(result == 0);
    return result;
}

Long solve(Long n)
{
    Long result = n;
    for (Long d = 1; d * d <= n; ++ d) {
        if (miu[d] != 0) {
            result += miu[d] * solve_triple(n / d / d);
        }
    }
    return result / 2;
}

int main()
{
    memset(miu, 0, sizeof(miu));
    miu[1] = 1;
    for (int i = 1; i <= N; ++ i) {
        for (int j = i + i; j <= N; j += i) {
            miu[j] -= miu[i];
        }
    }
    std::cout << solve(1000000) << std::endl;
    std::cout << solve(1000000000000) << std::endl;
}
