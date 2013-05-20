// Codeforces Round #184
// Problem D -- Olya and Graph
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = (int)1e6;
const int MOD = (int)1e9 + 7;

int n, m, k, power[N + 1];
int sum[N + 1];

int main() {
    scanf("%d%d%d", &n, &m, &k);
    memset(sum, 0, sizeof(sum));
    for (int i = 0; i < m; ++ i) {
        int u, v;
        scanf("%d%d", &u, &v);
        if (v - u == k + 1) {
            sum[u - 1] = 1;
        } else if (v - u != 1) {
            puts("0");
            return 0;
        }
    }
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] += sum[i + 1];
    }
    power[0] = 1;
    for (int i = 1; i <= N; ++ i) {
        power[i] = power[i - 1] * 2 % MOD;
    }
    int answer = 0;
    if (!sum[0]) {
        answer ++;
    }
    for (int i = 0; i < n - k - 1; ++ i) {
        if (sum[0] - sum[i]) {
            continue;
        }
        if (sum[i + k + 1]) {
            continue;
        }
        int tobe = std::min(n - k - 2, i + k) - i - (sum[i + 1] - sum[i + k + 1]);
        (answer += power[tobe]) %= MOD;
    }
    printf("%d\n", answer);
    return 0;
}
