// EMEA Semifinal 2008
// Problem C -- Rainbow Trees
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

const int N = 555;
const int MOD = 1000000009;

int n, k, parent[N], children_count[N];
vector <int> adjacent[N];

void dfs(int p, int u) {
    parent[u] = p;
    children_count[u] = 0;
    for (vector <int> :: iterator iter = adjacent[u].begin(); \
            iter != adjacent[u].end(); ++ iter) {
        int v = *iter;
        if (v != p) {
            dfs(u, v);
            children_count[u] ++;
        }
    }
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    for (int test = 1; test <= test_count; ++ test) {
        scanf("%d%d", &n, &k);
        for (int i = 0; i < n; ++ i) {
            adjacent[i].clear();
        }
        for (int i = 0; i < n - 1; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            a --;
            b --;
            adjacent[a].push_back(b);
            adjacent[b].push_back(a);
        }
        dfs(-1, 0);
        long long result = 1;
        for (int i = 0; i < n; ++ i) {
            int color = k;
            if (parent[i] != -1) {
                color -= children_count[parent[i]];
                if (parent[parent[i]] != -1) {
                    color --;
                }
            }
            for (int j = 0; j < children_count[i]; ++ j) {
                result *= max(color - j, 0);
                result %= MOD;
            }
        }
        printf("Case #%d: %d\n", test, (int)result);
    }
    return 0;
}
