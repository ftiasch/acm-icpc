#include <iostream>
#include <cstdlib>
#include <cstring>

const int M = 1 << 16;
const int MOD = (int)1e9 + 7;

int k, m, inv_fact[M];

void add(int &x, int a) {
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

void mlt(int* result, int* a, int* b, int m)
{
    if (m <= 1 << 4) {
        for (int i = 0; i < m + m; ++ i) {
            unsigned long long ref = 0;
            for (int j = std::max(0, i - m + 1); j <= i && j < m; ++ j) {
                ref += (long long)a[j] * b[i - j];
            }
            add(result[i], ref % MOD);
        }
    } else {
        int* cache = (int*)malloc(sizeof(int) * m);
        memset(cache, 0, sizeof(*cache) * m);
        int m2 = m >> 1;
        mlt(cache, a, b, m2);
        for (int i = 0; i < m; ++ i) {
            add(result[i], cache[i]);
            add(result[i + m2], MOD - cache[i]);
        }
        memset(cache, 0, sizeof(*cache) * m);
        mlt(cache, a + m2, b + m2, m2);
        for (int i = 0; i < m; ++ i) {
            add(result[i + m], cache[i]);
            add(result[i + m2], MOD - cache[i]);
        }
        free(cache);
        for (int i = 0; i < m2; ++ i) {
            add(a[i], a[i + m2]);
            add(b[i], b[i + m2]);
        }
        mlt(result + m2, a, b, m2);
        for (int i = 0; i < m2; ++ i) {
            add(a[i], MOD - a[i + m2]);
            add(b[i], MOD - b[i + m2]);
        }
    }
}

void cov(int* result, int* a_, int* b, int len)
{
    static int a[M];
    int p0 = 1;
    for (int _ = 0; _ < len; ++ _) {
        (p0 <<= 1) %= MOD;
    }
    for (int i = 0, p = 1; i < m; ++ i) {
        a[i] = (long long)a_[i] * p % MOD;
        p = (long long)p * p0 % MOD;
    }
    memset(result, 0, sizeof(*result) * (m + m));
    int m2 = m >> 1;
    mlt(result, a, b, m2);
    mlt(result + m2, a + m2, b, m2);
    mlt(result + m2, a, b + m2, m2);
    memset(result + (k + 1), 0, sizeof(*result) * (m + m - k - 1));
}

void rec(int* result, int n)
{
    static int cache[M];
    if (n == 1) {
        memcpy(result, inv_fact, sizeof(*result) * m);
    } else {
        rec(result, n >> 1);
        cov(cache, result, result, n >> 1);
        if (n & 1) {
            cov(result, cache, inv_fact, 1);
        } else {
            memcpy(result, cache, sizeof(*result) * m);
        }
    }
}

int main()
{
    long long n;
    std::cin >> n >> k;
    int result = 0;
    if (n <= k) {
        m = 1;
        while (k >= m) {
            m <<= 1;
        }
        memset(inv_fact, 0, sizeof(*inv_fact) * m);
        inv_fact[1] = 1;
        for (int i = 2; i <= k; ++ i) {
            inv_fact[i] = (long long)(MOD - MOD / i) * inv_fact[MOD % i] % MOD;
        }
        for (int i = 2; i <= k; ++ i) {
            inv_fact[i] = (long long)inv_fact[i] * inv_fact[i - 1] % MOD;
        }
        static int ways[M];
        rec(ways, n);
        inv_fact[0] = 1;
        int fact = 1;
        for (int i = 1; i <= k; ++ i) {
            fact = (long long)fact * i % MOD;
        }
        for (int i = 0; i <= k; ++ i) {
            add(result, (long long)fact * ways[i] % MOD * inv_fact[k - i] % MOD);
        }
    }
    std::cout << result << std::endl;
    return 0;
}
