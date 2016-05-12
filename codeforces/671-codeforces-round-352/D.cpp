#include <cstdio>
#include <cstring>
#include <iostream>
#include <set>
#include <utility>
#include <vector>

const int N = 300000;

int n, depth[N];
long long result;
std::vector<int> tree[N];
std::set<std::pair<long long, int>> query[N];

typedef std::pair<long long, std::set<std::pair<long long, int>>*> Summary;

Summary merge(const Summary& u, const Summary& v)
{
    if (u.second->size() < v.second->size()) {
        return merge(v, u);
    }
    Summary w = u;
    for (auto&& it : *v.second) {
        w.second->insert({it.first + v.first - u.first, it.second});
    }
    return w;
}

Summary dfs(int p, int u)
{
    depth[u] = ~p ? depth[p] + 1 : 0;
    Summary s {0, query + u};
    for (int v : tree[u]) {
        if (v != p) {
            auto s2 = dfs(u, v);
            while (!s2.second->empty() && depth[u] < depth[s2.second->begin()->second]) {
                s2.second->erase(s2.second->begin());
            }
            if (s2.second->empty()) {
                result = -1;
            }
            if (~result) {
                int tmp = s2.first + s2.second->begin()->first;
                result += tmp;
                s2.first -= tmp;
            }
            s = merge(s, s2);
        }
    }
    return s;
}

int main()
{
    int m;
    scanf("%d%d", &n, &m);
    for (int _ = 0; _ < n - 1; ++ _) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    for (int i = 0; i < m; ++ i) {
        int u, v, c;
        scanf("%d%d%d", &u, &v, &c);
        u --;
        v --;
        query[u].emplace(c, v);
    }
    dfs(-1, 0);
    std::cout << result << std::endl;
}
