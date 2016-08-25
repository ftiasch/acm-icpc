#include <algorithm>
#include <cstdio>
#include <functional>
#include <utility>
#include <vector>

const int N = 400000;

int n, parent[N], size[N], down[N], up[N];
std::vector<int> tree[N];

bool valid(int s)
{
    return s * 2 <= n;
}

void prepare(int p, int u)
{
    parent[u] = p;
    size[u] = 1;
    down[u] = 0;
    for (auto&& v : tree[u]) {
        if (v != p) {
            prepare(u, v);
            size[u] += size[v];
            down[u] = std::max(down[u], down[v]);
        }
    }
    if (valid(size[u])) {
        down[u] = size[u];
    }
}

void dfs(int p, int u)
{
    std::pair<int, int> best[3];
    best[0].second = best[1].second = -1;
    for (auto&& v : tree[u]) {
        if (v != p && valid(down[v])) {
            best[2] = {down[v], v};
            std::sort(best, best + 3, std::greater<std::pair<int, int>>());
        }
    }
    for (auto&& v : tree[u]) {
        if (v != p) {
            up[v] = std::max(up[u], best[best[0].second == v].first);
            if (valid(n - size[v])) {
                up[v] = n - size[v];
            }
            dfs(u, v);
        }
    }
}

int main()
{
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    prepare(-1, 0);
    up[0] = 0;
    dfs(-1, 0);
    for (int u = 0; u < n; ++ u) {
        std::pair<int, int> best(n - size[u], up[u]);
        for (auto&& v : tree[u]) {
            if (v != parent[u]) {
                best = std::max(best, {size[v], down[v]});
            }
        }
        printf("%d%c", valid(best.first - best.second), " \n"[u == n - 1]);
    }
}
