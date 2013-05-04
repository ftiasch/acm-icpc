// Codeforces Round #181
// Problem C -- Beautiful Numbers
#include <cstdio>
#include <cstring>

const int N = 1000000;
const int MOD = 1000000000 + 7;

int a, b, n, factorial[N + 1];

int inverse(int a) {
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

inline int binom(int n, int k) {
    return (long long)factorial[n] * inverse(factorial[k]) % MOD * inverse(factorial[n - k]) % MOD;
}

int main() {
    scanf("%d%d%d", &a, &b, &n);
    factorial[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        factorial[i] = (long long)factorial[i - 1] * i % MOD;
    }
    int answer = 0;
    for (int i = 0; i <= n; ++ i) {
        int sum = a * i + (n - i) * b;
        bool valid = true;
        while (sum) {
            valid &= sum % 10 == a || sum % 10 == b;
            sum /= 10;
        }
        if (valid) {
            (answer += binom(n, i)) %= MOD;
        }
    }
    printf("%d\n", answer);
    return 0;
}
