#include <cstdio>
#include <iostream>
#include <map>
#include <utility>
#include <vector>

void gen(int n, std::map<int, int>& c, const std::vector<std::pair<int, int>>& facts, int idx, long long a, long long b)
{
    if (idx < facts.size()) {
        auto p = facts[idx].first;
        auto e = facts[idx].second;
        for (int i = 0; i < e; ++ i) {
            b *= p;
        }
        for (int i = 0; i <= e; ++ i) {
            gen(n, c, facts, idx + 1, a, b);
            a *= p;
            b /= p;
        }
    } else if (a > b && (~(a - b) & 1) && (a + b >> 1) < n) {
        c[(a - b) >> 1] ++;
    }
}

long long solve(int n)
{
    std::vector<std::map<int, int>> count(n);
    long long result = 0;
    for (int s = 1; s < n + n - 1; ++ s) {
        int x = s;
        int h = 1;
        std::vector<std::pair<int, int>> facts;
        for (int p = 2; p * p <= x; ++ p) {
            int e = 0;
            while (x % p == 0) {
                x /= p;
                e ++;
                if (e & 1) {
                    h *= p;
                }
            }
            if (e > 0 && s < n) {
                facts.emplace_back(p, 2 * e);
            }
        }
        if (x > 1) {
            h *= x;
            facts.emplace_back(x, 2);
        }
        if (s < n) {
            gen(n, count[s], facts, 0, 1, 1);
        }
        if (s % 10000 == 0) {
            std::cerr << "Processing " << s << std::endl;
        }
        for (int u = h; u < s - u; u += h) {
            int v = s - u;
            if (v < n) {
                auto jt = count[v].begin();
                for (auto&& it : count[u]) {
                    while (jt != count[v].end() && jt->first < it.first) {
                        jt ++;
                    }
                    if (it.first == jt->first) {
                        result += (long long)it.second * jt->second;
                    }
                }
            }
        }
    }
    return result;
}

int main()
{
    std::cout << solve(200) << std::endl;
    std::cout << solve(1000000) << std::endl;
}
