// POJ 2378 -- Tree Cutting
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 10000;

int n, size[N], max_child[N];
std::vector <int> edges[N];
std::vector <int> answer;

void dfs(int p, int u) {
    size[u] = 1;
    max_child[u] = 0;
    foreach (iter, edges[u]) {
        int v = *iter;
        if (v != p) {
            dfs(u, v);
            size[u] += size[v];
            max_child[u] = std::max(max_child[u], size[v]);
        }
    }
    max_child[u] = std::max(max_child[u], n - size[u]);
    if (max_child[u] <= n / 2) {
        answer.push_back(u);
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0, a, b; i < n - 1; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    dfs(-1, 0);
    std::sort(answer.begin(), answer.end());
    if (answer.empty()) {
        puts("NONE");
    } else {
        foreach (iter, answer) {
            printf("%d\n", *iter + 1);
        }
    }
    return 0;
}
