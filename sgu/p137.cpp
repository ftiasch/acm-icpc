// SGU 137 -- Funny Strings
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1111;

int n, m, x[N];

int gcd(int a, int b) {
    return b == 0? a: gcd(b, a % b);
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i < n; ++ i) {
        if (gcd(n, i) == 1) {
            int delta_sum = 0;
            int delta = 0;
            for (int j = 0, k = 0; j < n; ++ j) {
                delta_sum += delta;
                if (k == 0) {
                    delta ++;
                }
                if (k == n - 1) {
                    delta --;
                }
                k = (k + i) % n;
            }
            if ((m - delta_sum) % n == 0) {
                int s = (m - delta_sum) / n;
                for (int j = 0, k = 0; j < n; ++ j) {
                    x[k] = s;
                    if (k == 0) {
                        s ++;
                    }
                    if (k == n - 1) {
                        s --;
                    }
                    k = (k + i) % n;
                }
                for (int j = 0; j < n; ++ j) {
                    printf("%d%c", x[j], j == n - 1? '\n': ' ');
                }
                return 0;
            }
        }
    }
    return 0;
}
