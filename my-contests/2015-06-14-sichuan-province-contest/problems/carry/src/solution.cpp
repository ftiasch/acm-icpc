#include <algorithm>
#include <cstdio>
#include <iostream>

const int N = 100000;

int a[N], b[N];

int main()
{
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        long long result = 0;
        for (int mod = 10; mod <= 1000000000; mod *= 10) {
            for (int i = 0; i < n; ++ i) {
                b[i] = a[i] % mod;
            }
            std::sort(b, b + n);
            for (int i = 0, j = n; i < n; ++ i) {
                while (j && b[i] + b[j - 1] >= mod) {
                    j --;
                }
                result += n - std::max(i + 1, j);
            }
        }
        std::cout << result << std::endl;
    }
    return 0;
}
