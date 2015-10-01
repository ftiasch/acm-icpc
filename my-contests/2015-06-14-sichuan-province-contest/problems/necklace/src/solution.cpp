#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 100000;
const int MAX_VALUE = 10000;

int a[N], prefix[N], count[MAX_VALUE + 1];

void update(int k, int v)
{
    for (; ~k; k -= ~k & k + 1) {
        count[k] = std::max(count[k], v);
    }
}

int query(int k)
{
    int result = 0;
    for (; k <= MAX_VALUE; k += ~k & k + 1) {
        result = std::max(result, count[k]);
    }
    return result;
}

int main()
{
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        int result = MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            if (a[i] == MAX_VALUE) {
                prefix[0] = 0;
                memset(count, 0, sizeof(*count) * (MAX_VALUE + 1));
                update(MAX_VALUE, MAX_VALUE);
                for (int j = 1; j < n; ++ j) {
                    prefix[j] = prefix[j - 1];
                    int aj = a[(i + j) % n];
                    if (aj < MAX_VALUE) {
                        int fj = query(aj) + aj;
                        prefix[j] = std::max(prefix[j], fj);
                        update(aj, fj);
                    }
                }
                result = std::max(result, prefix[n - 1]);
                memset(count, 0, sizeof(*count) * (MAX_VALUE + 1));
                int suffix = 0;
                for (int j = n; j >= 1; -- j) {
                    int aj = a[(i + j) % n];
                    if (aj < MAX_VALUE) {
                        int fj = query(aj) + aj;
                        suffix = std::max(suffix, fj);
                        update(aj, fj);
                    }
                    result = std::max(result, prefix[j - 1] + suffix);
                }
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
