// Codeforces Round #120
// Problem E -- Counter Attack
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 500002;

int n, m, next[N];
vector <int> adjacent[N];
vector <vector <int> > components;

int find_next(int i) {
    if (next[i] != i) {
        next[i] = find_next(next[i]);
    }
    return next[i];
}

void dfs(int u) {
    components.back().push_back(u);
    next[u] = u + 1;
    vector <int> :: iterator iter = adjacent[u].begin();
    for (int v = find_next(1); v <= n; v = find_next(v + 1)) {
        while (iter != adjacent[u].end() && *iter < v) {
            iter ++;
        }
        if (iter == adjacent[u].end() || *iter != v) {
            dfs(v);
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    for (int i = 1; i <= n; ++ i) {
        next[i] = i;
        sort(adjacent[i].begin(), adjacent[i].end());
    }
    next[n + 1] = n + 1;
    for (int i = 1; i <= n; ++ i) {
        if (next[i] == i) {
            components.push_back(vector <int>());
            dfs(i);
        }
    }
    printf("%d\n", (int)components.size());
    for (int i = 0; i < (int)components.size(); ++ i) {
        printf("%d", (int)components[i].size());
        for (int j = 0; j < (int)components[i].size(); ++ j) {
            printf(" %d", components[i][j]);
        }
        puts("");
    }
    return 0;
}
