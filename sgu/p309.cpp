// SGU 309 -- Real Fun
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 20000;

int n, x[N], y[N], count[N];

const long long INF = 1000000000000000000LL;

bool check(long long length, int times) {
    if (*std::min_element(count, count + n)) {
        return true;
    }
    if (times) {
        long long a[2], b[2];
        a[0] = b[0] = INF;
        a[1] = b[1] = -INF;
        for (int i = 0; i < n; ++ i) {
            if (!count[i]) {
                a[0] = std::min(a[0], (long long)x[i]);
                a[1] = std::max(a[1], x[i] - length);
                b[0] = std::min(b[0], (long long)y[i]);
                b[1] = std::max(b[1], y[i] - length);
            }
        }
        for (int i = 0; i < 4; ++ i) {
            long long x_0 = a[i & 1];
            long long y_0 = b[i >> 1];
#define IN x_0 <= x[j] && y_0 <= y[j] && x[j] <= x_0 + length && y[j] <= y_0 + length
            for (int j = 0; j < n; ++ j) {
                if (IN) {
                    count[j] ++;
                }
            }
            if (check(length, times - 1)) {
                return true;
            }
            for (int j = 0; j < n; ++ j) {
                if (IN) {
                    count[j] --;
                }
            }
        }
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    long long low = 0;
    long long high = 2000000000;
    while (low < high) {
        long long middle = low + high >> 1;
        memset(count, 0, sizeof(count));
        if (check(middle, 3)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    printf("%d\n", (int)high);
    return 0;
}
