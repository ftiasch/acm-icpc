// Codeforces Round #152
// Problem B -- Chilly Willy
#include <cstdio>
#include <cstring>

const int MOD = 2 * 3 * 5 * 7;

const int N = 100000;

int n, power[N];
bool valid[N + 1][MOD];

int main() {
    scanf("%d", &n);
    power[0] = 1;
    for (int i = 0; i + 1 < n; ++ i) {
        power[i + 1] = (power[i] * 10) % MOD;
    }
    memset(valid, 0, sizeof(valid));
    valid[0][0] = true;
    for (int i = 0; i < n; ++ i) {
        for (int r = 0; r < MOD; ++ r) {
            if (valid[i][r]) {
                for (int d = 0; d < 10; ++ d) {
                    if (d == 0 && i == n - 1) {
                        continue;
                    }
                    valid[i + 1][(r + power[i] * d) % MOD] = true;
                }
            }
        }
    }
    if (valid[n][0]) {
        int r = 0;
        for (int i = n; i >= 1; -- i) {
            int d = i == n? 1 : 0;
            while (!valid[i - 1][(r + MOD - power[i - 1] * d % MOD) % MOD]) {
                d ++;
            }
            (r += MOD - power[i - 1] * d % MOD) %= MOD;
            printf("%d", d);
        }
        puts("");
    } else {
        puts("-1");
    }
    return 0;
}
