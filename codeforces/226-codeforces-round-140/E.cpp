// Codeforces Round #140
// Problem E -- Noble Knight's Path
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;
const int D = 19;

int n, parent[N], depth[N], go[N][D], attack[N];
vector <int> children[N];

int dfsCount, rank[N], size[N];

void dfs(int u) {
    depth[u] = parent[u] == -1? 0: depth[parent[u]] + 1;
    go[u][0] = parent[u];
    for (int i = 0; go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    rank[u] = dfsCount ++;
    size[u] = 1;
    for (vector <int> :: iterator iter = children[u].begin();
            iter != children[u].end(); ++ iter) {
        dfs(*iter);
        size[u] += size[*iter];
    }
}

int jump(int u, int d) {
    for (int i = D - 1; i >= 0; -- i) {
        if (1 << i <= d) {
            u = go[u][i];
            d -= 1 << i;
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
    return parent[u];
}

struct Node {
    int count;
    Node *left, *right;

    Node(int count, Node *left, Node *right): count(count), left(left), right(right) {}

    Node* cover(int, int, int, int, int);

    int query(int l, int r, int k) {
        if (k < l || r <= k) {
            return 0;
        }
        int result = count;
        int m = (l + r) >> 1;
        if (l + 1 < r) {
            result += left->query(l, m, k);
            result += right->query(m, r, k);
        }
        return result;
    }
};

Node *null;

Node* Node::cover(int l, int r, int a, int b, int c = 1) {
    if (b <= l || r <= a) {
        return this;
    }
    if (a <= l && r <= b) {
        return new Node(count + 1, left, right);
    }
    int m = (l + r) >> 1;
    return new Node(count, 
                    left->cover(l, m, a, b, c),
                    right->cover(m, r, a, b, c));
}

Node* trees[N + 1];

int queryPath(Node *tree, int a, int b) {
    int c = lca(a, b);
    int result = tree->query(0, n, rank[a]);
    result += tree->query(0, n, rank[b]);
    result -= tree->query(0, n, rank[c]);
    if (parent[c] != -1) {
        result -= tree->query(0, n, rank[parent[c]]);
    }
    return result;
}

int select(int a, int b, int k) {
    int c = lca(a, b);
    if (k <= depth[a] - depth[c] + 1) {
        return jump(a, k - 1);
    }
    k -= depth[a] - depth[c];
    return jump(b, depth[b] - depth[c] - k + 1);
}

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", &parent[i]);
        parent[i] --;
        if (parent[i] != -1) {
            children[parent[i]].push_back(i);
        }
    }
    int root = 0;
    while (parent[root] != -1) {
        root ++;
    }
    dfsCount = 0;
    memset(go, -1, sizeof(go));
    dfs(root);
    Node *all = null;
    for (int i = 0; i < n; ++ i) {
        all = all->cover(0, n, rank[i], rank[i] + size[i]);
    }
    trees[0] = null;
    int m;
    scanf("%d", &m);
    memset(attack, -1, sizeof(attack));
    for (int i = 0; i < m; ++ i) {
        int type;
        scanf("%d", &type);
        if (type == 1) {
            int u;
            scanf("%d", &u);
            u --;
            attack[u] = i;
            trees[i + 1] = trees[i]->cover(0, n, rank[u], rank[u] + size[u]);
        } else {
            trees[i + 1] = trees[i];
            int a, b, k, y;
            scanf("%d%d%d%d", &a, &b, &k, &y);
            a --;
            b --;
            int limit = queryPath(all, a, b);
            int low = 2;
            int high = limit;
            while (low < high) {
                int middle = (low + high) >> 1;
                int u = select(a, b, middle);
                int result = queryPath(all, a, u);
                result -= queryPath(trees[i], a, u);
                result += queryPath(trees[y], a, u);
                if (y > attack[a]) {
                    result --;
                }
                if (result >= k) {
                    high = middle;
                } else {
                    low = middle + 1;
                }
            }
            if (high == limit) {
                puts("-1");
            } else {
                printf("%d\n", select(a, b, high) + 1);
            }
        }
    }
    return 0;
}
