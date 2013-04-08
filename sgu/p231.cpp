// SGU 231 -- Prime Sum
#include <cstdio>
#include <cstring>

const int N = 1000000;

int n;
bool is_prime[N + 1];

int main() {
    scanf("%d", &n);
    memset(is_prime, true, sizeof is_prime);
    for (int i = 2; i <= n; ++ i) {
        if (is_prime[i]) {
            for (long long j = (long long)i * i; j <= n; j += i) {
                is_prime[j] = false;
            }
        }
    }
    int answer = 0;
    for (int i = 3; i + 2 <= n; ++ i) {
        answer += is_prime[i] && is_prime[i + 2];
    }
    printf("%d\n", answer);
    for (int i = 3; i + 2 <= n; ++ i) {
        if (is_prime[i] && is_prime[i + 2]) {
            printf("%d %d\n", 2, i);
        }
    }
    return 0;
}
