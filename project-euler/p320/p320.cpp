#include <cassert>
#include <iostream>
#include <vector>

typedef unsigned long long Long;

const Long MOD = (Long)1e18;

Long count(Long n, int p)
{
    Long result = 0;
    while (n) {
        result += (n /= p);
    }
    return result;
}

Long update(Long now, int p, Long n)
{
    Long e = count(n, p) * 1234567890;
    if (count(now, p) >= e) {
        return now;
    }
    Long low = now;
    Long high = 1ULL << 63;
    while (low < high) {
        Long middle = low + high >> 1;
        if (count(middle, p) >= e) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    assert(count(high, p) >= e);
    return high;
}

Long solve(int n)
{
    Long result = 0;
    Long m = 0;
    for (int i = 1; i <= n; ++ i) {
        {
            int x = i;
            for (int p = 2; p * p <= x; ++ p) {
                if (x % p == 0) {
                    int e = 0;
                    while (x % p == 0) {
                        e ++, x /= p;
                    }
                    m = update(m, p, i);
                }
            }
            if (x > 1) {
                m = update(m, x, i);
            }
        }
        if (i >= 10) {
            (result += m) %= MOD;
        }
    }
    return result;
}

int main()
{
    std::cout << solve(1000) << std::endl;
    std::cout << solve(1000000) << std::endl;
}
