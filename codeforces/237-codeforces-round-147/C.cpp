// Codeforces Round #147
// Problem C -- Primes on Interval
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1000000;

int a, b, k;
int sum[N + 1];

int main() {
    scanf("%d%d%d", &a, &b, &k);
    fill(sum, sum + (b + 1), 1);
    sum[0] = sum[1] = 0;
    for (int i = 2; i <= b; ++ i) {
        if (sum[i]) {
            if ((long long)i * i <= b) {
                for (int j = i * i; j <= b; j += i) {
                    sum[j] = 0;
                }
            }
        }
    }
    for (int i = 1; i <= b; ++ i) {
        sum[i] += sum[i - 1];
    }
    if (sum[b] - sum[a - 1] < k) {
        puts("-1");
    } else {
        int low = 1;
        int high = b - a + 1;
        while (low < high) {
            int middle = (low + high) >> 1;
            bool valid = true;
            for (int i = a; i + middle - 1 <= b && valid; ++ i) {
                valid &= sum[i + middle - 1] - sum[i - 1] >= k;
            }
            if (valid) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        printf("%d\n", high);
    }
    return 0;
}
