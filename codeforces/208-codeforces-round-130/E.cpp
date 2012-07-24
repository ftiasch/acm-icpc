// Codeforces Round #130
// Problem E -- Blood Cousins
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;
const int D = 20;

int n, m, go[N][D], depth[N], size[N], labelCount, label[N];
vector <int> sons[N], nodes[N], sonLabels[N];

void dfs(int u) {
    size[u] = 1;
    label[u] = labelCount ++;
    for (vector <int> :: iterator iter = sons[u].begin(); iter != sons[u].end(); ++ iter) {
        depth[*iter] = depth[u] + 1;
        dfs(*iter);
        sonLabels[u].push_back(label[*iter]);
        size[u] += size[*iter];
    }
}

int jump(int u, int d) {
    for (int i = D - 1; i >= 0; -- i) {
        if (u == -1) {
            return -1;
        }
        if (d >= (1 << i)) {
            d -= 1 << i;
            u = go[u][i];
        }
    }
    return u;
}

int countNode(int p, int v) {
    return lower_bound(nodes[p].begin(), nodes[p].end(), label[v] + size[v]) - lower_bound(nodes[p].begin(), nodes[p].end(), label[v]);
}

int main() {
    scanf("%d", &n);
    memset(go, -1, sizeof(go));
    for (int i = 0; i < n; ++ i) {
        scanf("%d", &go[i][0]);
        go[i][0] --;
        if (go[i][0] != -1) {
            sons[go[i][0]].push_back(i);
        }
    }
    for (int j = 0; j < D; ++ j) {
        for (int i = 0; i < n; ++ i) {
            if (go[i][j] != -1) {
                go[i][j + 1] = go[go[i][j]][j];
            }
        }
    }
    labelCount = 0;
    for (int i = 0; i < n; ++ i) {
        if (go[i][0] == -1) {
            depth[i] = 0;
            dfs(i);
        }
    }
    for (int i = 0; i < n; ++ i) {
        nodes[depth[i]].push_back(label[i]);
    }
    for (int i = 0; i < n; ++ i) {
        sort(nodes[i].begin(), nodes[i].end());
    }
    int m;
    scanf("%d", &m);
    while (m > 0) {
        m --;
        int v, p;
        scanf("%d%d", &v, &p);
        int u = jump(-- v, p);
        if (u == -1) {
            putchar('0');
        } else {
            p += depth[u];
            int result = countNode(p, u);
            printf("%d", result - 1);
        }
        putchar(m == 0? '\n': ' ');
    }
    return 0;
}
