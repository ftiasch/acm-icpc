// SGU 134 -- Centroid
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 20000;

int n, size[N], weight[N];
vector <int> adjacents[N];

void dfs(int p, int u) {
    size[u] = 1;
    weight[u] = 0;
    for (vector <int> :: iterator iter = adjacents[u].begin(); iter != adjacents[u].end(); ++ iter) {
        int v = *iter;
        if (v != p) {
            dfs(u, v);
            size[u] += size[v];
            weight[u] = max(weight[u], size[v]);
        }
    }
    weight[u] = max(weight[u], n - size[u]);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        adjacents[a].push_back(b);
        adjacents[b].push_back(a);
    }
    dfs(0, 1);
    int min_weight = *min_element(weight + 1, weight + 1 + n);
    int centroid_count = 0;
    for (int i = 1; i <= n; ++ i) {
        if (weight[i] == min_weight) {
            centroid_count ++;
        }
    }
    printf("%d %d\n", min_weight, centroid_count);
    bool first = true;
    for (int i = 1; i <= n; ++ i) {
        if (weight[i] == min_weight) {
            if (first) {
                first = false;
            } else {
                printf(" ");
            }
            printf("%d", i);
        }
    }
    puts("");
    return 0;
}
