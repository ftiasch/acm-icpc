#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>

const int N = (int)2e5;
const int M = (int)5e5;

std::vector <int> divisors[M + 1];

int n, q, a[N], mu[M + 1], count[M + 1];
bool state[N];

int main()
{
    for (int d = 1; d <= M; ++ d) {
        for (int n = d; n <= M; n += d) {
            divisors[n].push_back(d);
        }
    }
    memset(mu, 0, sizeof(mu));
    mu[1] = 1;
    for (int d = 1; d <= M; ++ d) {
        for (int n = d + d; n <= M; n += d) {
            mu[n] -= mu[d];
        }
    }
    scanf("%d%d", &n, &q);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    memset(count, 0, sizeof(count));
    memset(state, 0, sizeof(*state) * n);
    long long result = 0;
    while (q --) {
        int x;
        scanf("%d", &x);
        x --;
        state[x] ^= 1;
        int sign = state[x] ? 1 : -1;
        for (int d : divisors[a[x]]) {
            int delta = count[d];
            if (sign == -1) {
                delta = -count[d] + 1;
            }
            result += mu[d] * delta;
            count[d] += sign;
        }
        printf("%I64d\n", result);
    }
    return 0;
}
