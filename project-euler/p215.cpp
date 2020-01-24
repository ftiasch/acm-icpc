#include <cstdio>
#include <vector>

long long solve(int n, int m)
{
    std::vector<long long> ways(1 << m + m + 1);
    ways[0] = 1;
    for (auto i = 1; i <= n; ++ i) {
        for (auto j = 0; j < m; ++ j) {
            std::vector<long long> new_ways(1 << m + m + 1);
            for (auto mask = 0; mask < 1 << m + m + 1; ++ mask) {
                if (!ways[mask]) {
                    continue;
                }
                auto pre = j && (mask >> m + m & 1);
                auto cur = mask >> j + j & 3;
                if ((!pre || i == n) && (cur == 1 || cur == 2)) {
                    new_ways[mask & ~(3 << j + j) | 1 << m + m] += ways[mask];
                }
                if (cur < 3) {
                    new_ways[(mask & ~(1 << m + m)) + (1 << j + j)] += ways[mask];
                }
            }
            ways.swap(new_ways);
            // printf("%d %d\n", i, j);
            // for (auto mask = 0; mask < 1 << m + m + 1; ++ mask) {
            //     if (!ways[mask]) {
            //         continue;
            //     }
            //     for (int k = 0; k < m; ++ k) {
            //         printf("%d", mask >> k + k & 3);
            //     }
            //     printf(" %d", mask >> m + m & 1);
            //     printf(" %lld\n", ways[mask]);
            // }
        }
    }
    return ways[1 << m + m];
}

int main()
{
    printf("%lld\n", solve(9, 3));
    printf("%lld\n", solve(32, 10));
}
