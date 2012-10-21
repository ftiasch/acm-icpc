// Codeforces Round #144
// Problem B -- Table
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int MOD = 1000000000 + 7;

const int N = 100;

int n, k;
long long m;

int pow(int a, long long n) {
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

int binom[N + 1][N + 1], column[N][N + 1], ways[N + 1][N * N + 1];

int main() {
    cin >> n >> m >> k;
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= n; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    for (int j = 0; j < n; ++ j) {
        long long p = (m - j + n - 1) / n;
        for (int k = 0; k <= n; ++ k) {
            column[j][k] = pow(binom[n][k], p);
        }
    }
    memset(ways, 0, sizeof(ways));
    ways[0][0] = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j <= k; ++ j) {
            for (int x = 0; x <= n && j + x <= k; ++ x) {
                ways[i + 1][j + x] = (ways[i + 1][j + x] + (long long)ways[i][j] * column[i][x]) % MOD;
            }
        }
    }
    printf("%d\n", ways[n][k]);
    return 0;
}
