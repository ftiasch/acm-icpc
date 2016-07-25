#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>

const int N = 200000;

int m, size[N];
std::vector<int> tree[N];

long long dfs(int p, int u)
{
    long long result = 0;
    for (auto&& v : tree[u]) {
        if (v != p) {
            result += dfs(u, v);
            result += std::min(size[v], (m << 1) - size[v]);
            size[u] += size[v];
        }
    }
    return result;
}

int main()
{
    int n;
    scanf("%d%d", &n, &m);
    memset(size, 0, sizeof(*size) * n);
    for (int i = 0; i < m << 1; ++ i) {
        int u;
        scanf("%d", &u);
        size[u - 1] ++;
    }
    for (int _ = 0; _ < n - 1; ++ _) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    std::cout << dfs(-1, 0) << std::endl;
}
