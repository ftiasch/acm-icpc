// Codeforces Round #143
// Problem E -- Cactus
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;
const int M = 100000;

int n, m;
int edgeCount, firstEdge[N], to[M << 1], nextEdge[M << 1];

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int depth[N], lowest[N], cpnCount, belong[N];
bool used[M];
vector <int> stack;

int size[N];

void dfs(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    lowest[u] = depth[u];
    stack.push_back(u);
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        if (!used[iter >> 1]) {
            used[iter >> 1] = true;
            int v = to[iter];
            if (depth[v] == -1) {
                dfs(u, v);
                lowest[u] = min(lowest[u], lowest[v]);
            } else {
                lowest[u] = min(lowest[u], depth[v]);
            }
        }
    }
    if (lowest[u] == depth[u]) {
        while (true) {
            int v = stack.back();
            stack.pop_back();
            size[cpnCount] ++;
            belong[v] = cpnCount;
            if (u == v) {
                break;
            }
        }
        cpnCount ++;
    }
}

const int D = 20;

vector <int> adjacent[N];
int counter[N], go[N][D];

#define foreach(i, v) for (typeof(v.begin()) i = v.begin(); i != v.end(); ++ i) 

void dfs2(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    go[u][0] = p;
    for (int i = 0; go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    counter[u] = (p == -1 ? 0 : counter[p]) + (size[u] > 1);
    foreach (iter, adjacent[u]) {
        int v = *iter;
        if (v != p) {
            dfs2(u, v);
        }
    }
}

int jump(int u, int d) {
    for (int i = D - 1; i >= 0; -- i) {
        if (1 << i <= d) {
            d -= 1 << i;
            u = go[u][i];
        }
    }
    return u;
}

int lca(int u, int v) {
    if (depth[u] > depth[v]) {
        swap(u, v);
    }
    v = jump(v, depth[v] - depth[u]);
    if (u == v) {
        return u;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (go[u][i] != go[v][i]) {
            u = go[u][i];
            v = go[v][i];
        }
    }
    return go[u][0];
}

const int MOD = 1000000000 + 7;

int powMod(int a, int n, int m) {
    int result = 1 % m;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % m;
        }
        a = (long long)a * a % m;
        n >>= 1;
    }
    return result;
}

int main() {
    scanf("%d%d", &n, &m);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        addEdge(a, b);
        addEdge(b, a);
    }
    memset(used, 0, sizeof(used));
    memset(depth, -1, sizeof(depth));
    cpnCount = 0;
    memset(size, 0, sizeof(size));
    dfs(-1, 0);
    for (int i = 0; i < edgeCount; i += 2) {
        int a = belong[to[i]];
        int b = belong[to[i ^ 1]];
        if (a != b) {
            adjacent[a].push_back(b);
            adjacent[b].push_back(a);
        }
    }
    memset(go, -1, sizeof(go));
    dfs2(-1, 0);
    int q;
    scanf("%d", &q);
    while (q --) {
        int x, y;
        scanf("%d%d", &x, &y);
        int a = belong[-- x];
        int b = belong[-- y];
        int c = lca(a, b);
        int result = powMod(2, counter[a] + counter[b] - 2 * counter[c] + (size[c] > 1), MOD);
        printf("%d\n", result);
    }
    return 0;
}
