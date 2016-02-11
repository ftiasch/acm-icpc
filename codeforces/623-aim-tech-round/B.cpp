#include <algorithm>
#include <cstdio>
#include <functional>
#include <iostream>
#include <set>
#include <vector>

const long long INF = (long long)1e15 + 1;

std::vector<int> prime_factors(int n)
{
    std::vector<int> result;
    for (int d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            result.push_back(d);
            while (n % d == 0) {
                n /= d;
            }
        }
    }
    if (n > 1) {
        result.push_back(n);
    }
    return result;
}

long long add(long long a, long long b)
{
    return std::min(a + b, INF);
}

long long solve(int n, int x, int y, const std::vector<int>& a)
{
    long long result = INF;
    for (int delta = -1; delta <= 1; ++ delta) {
        for (int d : prime_factors(a[n - 1] + delta)) {
            std::function<long long(int)> cost = [&](int a) -> long long {
                int r = a % d;
                if (!r) {
                    return 0;
                }
                if (r == 1 || r + 1 == d) {
                    return y;
                }
                return INF;
            };
            std::vector<long long> suffix_cost(n);
            suffix_cost[n - 1] = delta ? y : 0;
            for (int i = n - 2; i >= 0; -- i) {
                suffix_cost[i] = add(cost(a[i]), suffix_cost[i + 1]);
            }
            result = std::min(result, suffix_cost[0]);
            long long prefix_cost = 0;
            long long min_prefix = x;
            for (int i = 0; i < n - 1; ++ i) {
                prefix_cost = add(prefix_cost, cost(a[i]));
                if (prefix_cost < INF) {
                    min_prefix = std::min(min_prefix, prefix_cost - (long long)i * x);
                }
                if (suffix_cost[i + 1] < INF) {
                    result = std::min(result, (long long)i * x + min_prefix + suffix_cost[i + 1]);
                }
            }
        }
    }
    return result;
}

int main()
{
    int n, x, y;
    scanf("%d%d%d", &n, &x, &y);
    std::vector<int> a(n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", &a[i]);
    }
    long long result = solve(n, x, y, a);
    std::reverse(a.begin(), a.end());
    result = std::min(result, solve(n, x, y, a));
    std::cout << result << std::endl;
    return 0;
}
