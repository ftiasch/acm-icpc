#include <iostream>
#include <bitset>
#include <map>

using LL = long long;

auto inline at(int i, int j)
{
    return (i & 1) | (j << 1);
}

auto solve(int n, int m)
{
    std::map<int, LL> ways;
    ways[0] = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            std::map<int, LL> new_ways;
            for (auto&& iterator : ways) {
                auto mask = iterator.first;
                auto&& value = iterator.second;
                if (mask >> at(i, j) & 1) {
                    if (mask >> at(i + 1, j) & 1) {
                        new_ways[mask & ~(3 << (j << 1))] += value;
                    }
                } else {
                    new_ways[mask | 1 << at(i, j)] += value;
                    auto U = mask >> at(i + 1, j) & 1;
                    auto L = j && (mask >> at(i, j - 1) & 1);
                    auto UL = i && j && (mask >> at(i + 1, j - 1) & 1);
                    auto UR = i && j + 1 < m && (mask >> at(i + 1, j + 1) & 1);
                    if (U && UR) {
                        new_ways[mask & ~(5 << at(i + 1, j))] += value;
                    }
                    if (UL && U) {
                        new_ways[mask & ~(5 << at(i + 1, j - 1))] += value;
                    }
                    if (UL && L) {
                        new_ways[mask & ~(3 << (j - 1 << 1))] += value;
                    }
                    if (U && L) {
                        new_ways[mask & ~(1 << at(i + 1, j)) & ~(1 << at(i, j - 1))] += value;
                    }
                    if (L && j >= 2 && (mask >> at(i, j - 2) & 1)) {
                        new_ways[mask & ~(5 << at(i, j - 2))] += value;
                    }
                }
            }
            ways.swap(new_ways);
            // printf("(%d,%d)\n", i, j);
            // for (auto&& iterator : ways) {
            //     printf("%s:%lld\n", std::bitset<4>(iterator.first).to_string().c_str(), iterator.second);
            // }
        }
    }
    return ways[0];
}

int main()
{
    std::cout << solve(12, 9) << std::endl;
}
