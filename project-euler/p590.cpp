#include <algorithm>
#include <cstdio>
#include <cstring>

bool is_prime(int n)
{
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

const int PW2 = 1 << 9;
const int PW5 = 1953125;
const int P5 = 1562500;
const int C2 = 212890625;
const int C5 = 787109376;
const int MOD = (int)1e9;

int dp2[2][2][10], dp5[2][2][P5], pw2[10], pw5[P5];

inline void update(int& x, int a, int mod)
{
    x += a;
    if (x >= mod) {
        x -= mod;
    }
}

int solve(int n)
{
    memset(dp2, 0, sizeof(dp2));
    memset(dp5, 0, sizeof(dp5));
    dp2[0][0][1] = 1;
    dp5[0][0][1] = 1;
    int t = 0;
    for (int p = 2; p <= n; ++ p) {
        if (!is_prime(p)) {
            continue;
        }
        fprintf(stderr, "=> %d...\n", p);
        int e = 0;
        int pw = 1;
        while ((long long)pw * p <= n) {
            e ++;
            pw *= p;
        }
        memset(dp2[t ^ 1], 0, sizeof(dp2[t ^ 1]));
        memset(dp5[t ^ 1], 0, sizeof(dp5[t ^ 1]));
        for (int parity = 0; parity < 2; ++ parity) {
            for (int i = 0; i < 10; ++ i) {
                update(dp2[t ^ 1][parity][std::min(i * (e + 1), 10)], dp2[t][parity][i], PW2);
                update(dp2[t ^ 1][parity ^ 1][std::min(i * e, 10)], dp2[t][parity][i], PW2);
            }
            for (int i = 0; i < P5; ++ i) {
                update(dp5[t ^ 1][parity][(i * (e + 1)) % P5], dp5[t][parity][i], PW5);
                update(dp5[t ^ 1][parity ^ 1][(i * e) % P5], dp5[t][parity][i], PW5);
            }
        }
        t ^= 1;
    }
    pw2[0] = pw5[0] = 1;
    for (int i = 1; i < 10; ++ i) {
        pw2[i] = (pw2[i - 1] * 2) % PW2;
    }
    for (int i = 1; i < P5; ++ i) {
        pw5[i] = (pw5[i - 1] * 2) % PW5;
    }
    int rem2 = 0;
    int rem5 = 0;
    for (int i = 0; i < 10; ++ i) {
        update(rem2, (long long)dp2[t][0][i] * pw2[i] % PW2, PW2);
        update(rem2, PW2 - (long long)dp2[t][1][i] * pw2[i] % PW2, PW2);
    }
    for (int i = 0; i < P5; ++ i) {
        update(rem5, (long long)dp5[t][0][i] * pw5[i] % PW5, PW5);
        update(rem5, PW5 - (long long)dp5[t][1][i] * pw5[i] % PW5, PW5);
    }
    return ((long long)rem2 * C2 + (long long)rem5 * C5) % MOD;
}

int main()
{
    printf("%d\n", solve(4));
    printf("%d\n", solve(50000));
}
