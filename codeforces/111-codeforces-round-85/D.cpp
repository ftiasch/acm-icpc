// Codeforces Beta Round #85
// Problem D -- Petya and Coloring 
#include <cstdio>
#include <cstring>
using namespace std;

const int MOD = 1000000000 + 7;

const int K = 1000000;

int n, m, k;

int factorial[K + 1];

int inverse(int a) {
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

int binom(int n, int k) {
    if (n < k) {
        return 0;
    }
    return (long long)factorial[n] * inverse(factorial[n - k]) % MOD * inverse(factorial[k]) % MOD;
}

int pow(int a, int n) {
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % MOD;
        }
        a = (long long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

const int N = 1000;

int memory[N + 1][N + 1];

int exact(int n, int c) {
    if (n == 0) {
        return c == 0;
    }
    int &ret = memory[n][c];
    if (ret == -1) {
        ret = (long long)c * (exact(n - 1, c) + exact(n - 1, c - 1)) % MOD;
    }
    return ret;
}

int main() {
    memset(memory, -1, sizeof(memory));
    scanf("%d%d%d", &n, &m, &k);
    factorial[0] = 1;
    for (int i = 1; i <= k; ++ i) {
        factorial[i] = (long long)factorial[i - 1] * i % MOD;
    }
    if (m == 1) {
        printf("%d\n", pow(k, n));
    } else {
        int result = 0;
        for (int i = 1; i <= n; ++ i) {
            for (int j = 0; j <= i; ++ j) {
                int ways = (long long)binom(k, j) * binom(k - j, i - j) % MOD * binom(k - i, i - j) % MOD;
                result = (result + (long long)ways * exact(n, i) % MOD * exact(n, i) % MOD * pow(j, n * (m - 2)) % MOD) % MOD;
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
