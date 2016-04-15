#include "pe.h"

#include <cassert>
#include <functional>
#include <iostream>
#include <unordered_map>
#include <string>

const int MOD = (int)1e9 + 7;

void update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

typedef unsigned Long;

int solve(int n, int m, int c)
{
    // if (n < m) {
    //     return solve(m, n, c);
    // }
    std::vector<Long> power(m);
    power[0] = 1;
    for (int i = 1; i < m; ++ i) {
        power[i] = power[i - 1] * (m + 2);
    }
    auto get = [&](Long mask, int k) {
        return mask / power.at(k) % (m + 2);
    };
    auto replace = [&](Long mask, int k, Long v) {
        assert(v < m + 2);
        mask -= get(mask, k) * power.at(k);
        return mask + v * power.at(k);
    };
    auto minimize = [&](Long mask) {
        int count = 0;
        std::vector<int> id(m + 2, -1);
        id[0] = 0;
        for (int i = 0; i < m; ++ i) {
            int& ref = id[get(mask, i)];
            if (!~ref) {
                ref = ++ count;
            }
            mask = replace(mask, i, ref);
        }
        return mask;
    };
    auto count = [&](Long mask) {
        Long result = 0;
        for (int i = 0; i < m; ++ i) {
            result = std::max(result, get(mask, i));
        }
        return result;
    };
    // auto display = [&](Long mask) {
    //     std::string s;
    //     for (int i = 0; i < m; ++ i) {
    //         s += std::to_string(get(mask, i));
    //     }
    //     return s;
    // };
    std::unordered_map<Long, int> ways;
    ways.emplace(0, 1);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            std::unordered_map<Long, int> new_ways;
            for (auto&& it : ways) {
                auto&& s = it.first;
                auto&& w = it.second;
                int cnt = count(s);
                for (int k = cnt + 1; k >= 1; -- k) {
                    bool valid = get(s, j) != k;
                    if (j) {
                        valid &= get(s, j - 1) != k;
                    }
                    if (valid) {
                        int nw = w;
                        if (k > cnt) {
                            nw = (long long)nw * (c - cnt) % MOD;
                        }
                        if (nw) {
                            update(new_ways[minimize(replace(s, j, k))], nw);
                        }
                    }
                }
            }
            ways.swap(new_ways);
        }
    }
    int result = 0;
    for (auto&& it : ways) {
        update(result, it.second);
    }
    return result;
}

int sum(int n, int m, long long c)
{
    int d = n * m + 2;
    std::vector<int> values;
    for (int i = 0; i < d; ++ i) {
        values.push_back(solve(n, m, i));
        if (i) {
            update(values[i], values[i - 1]);
        }
    }
    return pe::polynomial_interpolation(values, c, MOD);
}

int sum2(int n, int m, long long c)
{
    int result = 0;
    for (int i = 0; i <= c; ++ i) {
        update(result, solve(n, m, i));
    }
    return result;
}

int main()
{
    // printf("%d\n", solve(2, 2, 3));
    // printf("%d\n", solve(2, 2, 20));
    // printf("%d\n", solve(9, 10, 6));
    // printf("%d\n", solve(9, 10, 100));
    printf("%d\n", sum(10, 9, 1112131415LL));
    return 0;
}
