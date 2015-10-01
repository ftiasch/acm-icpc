#include <cstdio>
#include <iostream>

int main()
{
    int n, m, l;
    while (scanf("%d%d%d", &n, &m, &l) == 3) {
        long long result = 0;
        for (int i = 1; i <= n; ++ i) {
            int max = std::min(l / 2 - i, m);
            if (max >= 1) {
                result += (long long)(2 * m - max + 1) * max / 2 * (n + 1 - i);
            }
        }
        std::cout << result << std::endl;
    }
}
