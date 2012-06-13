#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1000002;

bool is_prime[N];
int minimal_divisor[N], sum[N];

int main() {
    memset(is_prime, true, sizeof(is_prime));
    for (int i = 5; i < N; i += 4) {
        if (is_prime[i] && (long long)i * i < N) {
            for (int j = i; i * j < N; j += 4) {
                is_prime[i * j] = false;
                minimal_divisor[i * j] = i;
            }
        }
    }
    for (int i = 0; i < N; ++ i) {
        if (i == 1 || i % 4 != 1) {
            is_prime[i] = false;
        }
    }
    sum[0] = 0;
    for (int i = 1; i < N; ++ i) {
        sum[i] = sum[i - 1];
        if (i % 4 == 1 && i > 1 && !is_prime[i]) {
            sum[i] += is_prime[i / minimal_divisor[i]];
        }
    }
    int n;
    while (scanf("%d", &n) == 1 && n > 0) {
        printf("%d %d\n", n, sum[n]);
    }
    return 0;
}
