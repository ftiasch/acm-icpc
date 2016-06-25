#include <cstdio>
#include <utility>
#include <vector>

const int N = 300000;

int n, parent[N], size[N], centroid[N];
std::vector<int> children[N];

void dfs(int u)
{
    size[u] = 1;
    std::pair<int, int> best {0, 0};
    for (auto&& v : children[u]) {
        dfs(v);
        size[u] += size[v];
        best = std::max(best, {size[v], v});
    }
    auto& ref = centroid[u] = best.first ? centroid[best.second] : u;
    while (ref != u && size[ref] <= size[u] - size[ref]) {
        ref = parent[ref];
    }
}

int main()
{
    int q;
    scanf("%d%d", &n, &q);
    for (int i = 1; i < n; ++ i) {
        scanf("%d", parent + i);
        children[-- parent[i]].push_back(i);
    }
    dfs(0);
    while (q --) {
        int u;
        scanf("%d", &u);
        printf("%d\n", centroid[u - 1] + 1);
    }
}
