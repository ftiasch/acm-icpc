#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <iostream>
#include <set>
#include <utility>
#include <vector>

typedef long long Long;
typedef std::pair<Long, int> Pair;

const int N = 100000;

int f[N], degree[N];
Long t[N], income[N];
std::set<Pair> global, followers[N];

Long contribution(int u)
{
    return t[u] / degree[u];
}

Long self_contribution(int u)
{
    return t[u] - (degree[u] - 1) * contribution(u);
}

Long true_income(int u)
{
    return income[u] + contribution(f[u]);
}

std::vector<int> candidates(std::set<Pair>& s, std::function<bool(Long, int)> valid)
{
    std::vector<int> result;
    while (!s.empty()) {
        auto&& t = *s.begin();
        if (valid(t.first, t.second)) {
            result.push_back(t.second);
            break;
        }
        s.erase(t);
    }
    while (!s.empty()) {
        auto&& t = *s.rbegin();
        if (valid(t.first, t.second)) {
            result.push_back(t.second);
            break;
        }
        s.erase(t);
    }
    return result;
}

void add(int u)
{
    followers[f[u]].insert({income[u], u});
}

void update(int u)
{
    for (auto&& p : candidates(followers[u], [&](Long income_, int v) {
        return f[v] == u && income[v] == income_;
    })) {
        global.insert({true_income(p), p});
    }
}

void update_degree(int u, int delta)
{
    income[f[u]] -= contribution(u);
    income[u] -= self_contribution(u);
    degree[u] += delta;
    income[f[u]] += contribution(u);
    income[u] += self_contribution(u);
    add(f[u]);
    add(u);
    update(u);
    update(f[u]);
    update(f[f[u]]);
}

int main()
{
    std::ios::sync_with_stdio(false);
    int n, q;
    std::cin >> n >> q;
    for (int i = 0; i < n; ++ i) {
        std::cin >> t[i];
        degree[i] = 2;
    }
    for (int i = 0; i < n; ++ i) {
        std::cin >> f[i];
        degree[-- f[i]] ++;
    }
    for (int i = 0; i < n; ++ i) {
        income[f[i]] += contribution(i);
        income[i] += self_contribution(i);
    }
    for (int i = 0; i < n; ++ i) {
        add(i);
    }
    for (int i = 0; i < n; ++ i) {
        update(i);
    }
    while (q --) {
        int t;
        std::cin >> t;
        if (t == 1) {
            int x, y;
            std::cin >> x >> y;
            x --;
            y --;
            auto z = f[x];
            if (z != y) {
                income[z] -= contribution(x);
                income[y] += contribution(x);
                f[x] = y;
                add(x);
                update_degree(z, -1);
                update_degree(y, 1);
            }
        } else if (t == 2) {
            int x;
            std::cin >> x;
            std::cout << true_income(x - 1) << std::endl;
        } else if (t == 3) {
            auto result = candidates(global, [&](Long income, int u) {
                return income == true_income(u);
            });
            std::cout << true_income(result[0]) << " " << true_income(result[1]) << std::endl;
        }
    }
}
