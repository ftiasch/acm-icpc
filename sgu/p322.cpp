// SGU 322 -- The Great Union
#include <cstdio>
#include <cstring>
#include <set>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 2000;

int n;
std::set <int> edges[N];

int parent[N], depth[N];

int count[N][N];

void prepare(int p, int u) {
    parent[u] = p;
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    foreach (iter, edges[u]) {
        if (*iter != p) {
            prepare(u, *iter);
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0, a, b; i < n - 1; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        edges[a].insert(b);
        edges[b].insert(a);
        count[a][b] ++;
        count[b][a] ++;
    }
    for (int i = 0, a, b; i < n - 1; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        count[a][b] --;
        count[b][a] --;
    }
    int total = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            total += count[i][j] > 0;
        }
    }
    printf("%d\n", total);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            if (count[i][j] < 0) {
                prepare(-1, 0);
                bool found = false;
                for (int x = i, y = j; !found; ) {
                    if (depth[x] < depth[y]) {
                        std::swap(x, y);
                    }
                    if (count[x][parent[x]] > 0) {
                        found = true;
                        edges[x].erase(parent[x]);
                        edges[parent[x]].erase(x);
                        count[x][parent[x]] --;
                        count[parent[x]][x] --;
                        printf("1 %d %d %d %d\n", 1 + i, 1 + j, 1 + x, 1 + parent[x]);
                    } else {
                        x = parent[x];
                    }
                }
                edges[i].insert(j);
                edges[j].insert(i);
                count[i][j] ++;
                count[j][i] ++;
            }
        }
    }
    return 0;
}
