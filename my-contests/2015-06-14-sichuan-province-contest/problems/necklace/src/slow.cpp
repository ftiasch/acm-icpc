#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 100000;
const int MAX_VALUE = 10000;

int a[N], up[N], down[N];

int main()
{
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        int result = 0;
        for (int _ = 0; _ < n; ++ _) {
            std::rotate(a, a + 1, a + n);
            for (int i = 0; i < n; ++ i) {
                up[i] = 0;
                for (int j = 0; j < i; ++ j) {
                    if (a[j] <= a[i] && a[j] < MAX_VALUE) {
                        up[i] = std::max(up[i], up[j]);
                    }
                }
                up[i] += a[i];
            }
            for (int i = n - 1; i >= 0; -- i) {
                down[i] = 0;
                for (int j = n - 1; j > i; -- j) {
                    if (a[j] <= a[i] && a[j] < MAX_VALUE) {
                        down[i] = std::max(down[i], down[j]);
                    }
                }
                down[i] += a[i];
            }
            for (int i = 0; i < n; ++ i) {
                if (a[i] == MAX_VALUE) {
                    result = std::max(result, up[i] + down[i] - MAX_VALUE);
                }
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
