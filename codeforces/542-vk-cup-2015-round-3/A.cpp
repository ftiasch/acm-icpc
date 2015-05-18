#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

const int N = 200000;

int n, m, l[N], r[N], a[N], b[N], c[N];
std::pair <long long, std::pair <int, int>> result;

void update(int i, int j)
{
    long long profit = std::max(std::min(r[i], b[j]) - std::max(l[i], a[j]), 0) * (long long)c[j];
    result = std::max({profit, {i, j}}, result);
}

void solve()
{
    std::vector <std::pair <int, int>> events;
    for (int i = 0; i < n; ++ i) {
        events.push_back({l[i], i});
    }
    for (int i = 0; i < m; ++ i) {
        events.push_back({a[i], n + i});
    }
    std::sort(events.begin(), events.end());
    std::pair <int, int> opt(INT_MIN, -1);
    for (const auto &e : events) {
        int id = e.second;
        if (id >= n) {
            id -= n;
            if (~opt.second) {
                update(opt.second, id);
            }
        } else {
            opt = std::max(opt, {r[id], id});
        }
    }
}

void solve_inner()
{
    std::vector <int> values;
    for (int i = 0; i < n; ++ i) {
        values.push_back(l[i]);
    }
    for (int i = 0; i < m; ++ i) {
        values.push_back(a[i]);
    }
    std::sort(values.begin(), values.end());
    values.erase(std::unique(values.begin(), values.end()), values.end());
    std::vector <std::pair <int, int>> events;
    for (int i = 0; i < n; ++ i) {
        events.push_back({r[i], i});
    }
    for (int i = 0; i < m; ++ i) {
        events.push_back({b[i], n + i});
    }
    std::sort(events.begin(), events.end());
    int m = values.size();
    std::vector <std::pair <int, int>> tree(m, {INT_MIN, -1});
#define INDEX(v) (std::lower_bound(values.begin(), values.end(), v) - values.begin())
    for (const auto &e : events) {
        int id = e.second;
        if (id >= n) {
            id -= n;
            for (int k = INDEX(a[id]); k < m; k += ~k & k + 1) {
                if (~tree[k].second) {
                    update(tree[k].second, id);
                }
            }
        } else {
            for (int k = INDEX(l[id]); ~k; k -= ~k & k + 1) {
                tree[k] = std::max(tree[k], {r[id] - l[id], id});
            }
        }
    }
#undef INDEX
}

int main()
{
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", l + i, r + i);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", a + i, b + i, c + i);
    }
    solve();
    solve_inner();
    for (int i = 0; i < n; ++ i) {
        l[i] *= -1;
        r[i] *= -1;
        std::swap(l[i], r[i]);
    }
    for (int i = 0; i < m; ++ i) {
        a[i] *= -1;
        b[i] *= -1;
        std::swap(a[i], b[i]);
    }
    solve();
    if (result.first) {
        std::cout << result.first << std::endl << result.second.first + 1 << " " << result.second.second + 1 << std::endl;
    } else {
        puts("0");
    }
    return 0;
}
