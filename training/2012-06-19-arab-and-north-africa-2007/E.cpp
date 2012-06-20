// Problem E -- Fermat's Christmas Theorem
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1000001;

bool is_prime[N];
int sum[2][N];

int main() {
    memset(is_prime, true, sizeof(is_prime));
    for (int i = 2; i < N; ++ i) {
        if ((long long)i * i < N) {
            for (int j = i * i; j < N; j += i) {
                is_prime[j] = false;
            }
        }
    }
    is_prime[0] = is_prime[1] = false;
    memset(sum, 0, sizeof(sum));
    for (int i = N - 2; i >= 0; -- i) {
        sum[0][i] = sum[0][i + 1];
        if (is_prime[i]) {
            sum[0][i] ++;
        }
        sum[1][i] = sum[1][i + 1];
        if (is_prime[i] && (i == 2 || i % 4 == 1)) {
            sum[1][i] ++;
        }
    }
    while (true) {
        int lower, upper;
        scanf("%d%d", &lower, &upper);
        if (lower == -1 && upper == -1) {
            break;
        }
        if (upper <= 1) {
            printf("%d %d %d %d\n", lower, upper, 0, 0);
        } else {
            printf("%d %d %d %d\n", lower, upper, sum[0][max(lower, 0)] - sum[0][upper + 1], sum[1][max(lower, 0)] - sum[1][upper + 1]);
        }
    }
    return 0;
}
