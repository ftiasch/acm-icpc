// Northeastern Europe 2007
// Problem L -- Language Recognition
#include <map>
#include <vector>
#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

const int N = 222222,
      BASE = 100003;

int n, m, depth[N], parent[N];
bool terminated[N];
unsigned long long power[33];
vector <pair <unsigned long long, int> > order[33];
map <char, int> childs[N];

void dfs (int x) {
    for (map <char, int> :: iterator iter = childs[x].begin();
            iter != childs[x].end(); ++ iter) {
        dfs(iter->second);
        depth[x] = max(depth[x], depth[iter->second] + 1);
    }
}

int find (int x) {
    if (parent[x] != x) {
        parent[x] = find(parent[x]);
    }
    return parent[x];
}

void merge (int x, int y) {
    if (find(x) == find(y)) {
        return;
    }
    parent[find(x)] = find(y);
}

int main () {
    scanf("%d", &n);
    m = 1;
    childs[0].clear();
    memset(terminated, 0, sizeof(terminated));
    for (int i = 0; i < n; ++ i) {
        char str[33];
        scanf("%s", str);
        int len = strlen(str), cur = 0;
        for (int j = 0; j < len; ++ j) {
            if (!childs[cur].count(str[j])) {
                childs[m].clear();
                childs[cur][str[j]] = m ++;
            }
            cur = childs[cur][str[j]];
        }
        terminated[cur] = true;
    }
    memset(depth, 0, sizeof(depth));
    dfs(0);
    for (int i = 0; i < 33; ++ i) {
        order[i].clear();
    }
    for (int i = 0; i < m; ++ i) {
        order[depth[i]].push_back(make_pair(0ULL, i));
    }
    for (int i = 0; i < m; ++ i) {
        parent[i] = i;
    }
    power[0] = 1ULL;
    for (int i = 1; i < 33; ++ i) {
        power[i] = power[i - 1] * BASE;
    }
    for (int i = 0; i < 33; ++ i) {
        for (vector <pair <unsigned long long, int> > :: iterator iter = order[i].begin();
                iter != order[i].end(); ++ iter) {
            unsigned long long &res = iter->first;
            int cur = iter->second;
            res = power[26] * terminated[cur];
            for (map <char, int> :: iterator iter = childs[cur].begin();
                    iter != childs[cur].end(); ++ iter) {
                res += power[iter->first - 'a'] * find(iter->second);
            }
        }
        sort(order[i].begin(), order[i].end());
        for (int j = 1; j < (int)order[i].size(); ++ j) {
            if (order[i][j].first == order[i][j - 1].first) {
                merge(order[i][j].second, order[i][j - 1].second);
            }
        }
    }
    int res = 0;
    for (int i = 0; i < m; ++ i) {
        res += parent[i] == i;
    }
    printf("%d\n", res);
    return 0;
}
