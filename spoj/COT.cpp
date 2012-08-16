// 10628. Count on a tree
// Problem code: COT
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;
const int D = 21;

struct Node {
    static int MAX;

    int count;
    Node *left, *right;

    Node(int count, Node* left, Node* right): 
        count(count), left(left), right(right) {}

    Node* insert(int k);
    Node* insert(int l, int r, int k);
};

Node* null = new Node(0, NULL, NULL);

Node* Node::insert(int k) {
    return this->insert(0, MAX, k);
}

Node* Node::insert(int l, int r, int k) {
    if (l <= k && k < r) {
        if (l + 1 == r) {
            return new Node(this->count + 1, null, null);
        }
        int m = (l + r) >> 1;
        return new Node(this->count + 1, 
                        this->left->insert(l, m, k),
                        this->right->insert(m, r, k));
    }
    return this;
}

int n, m, weight[N], edgeCount, firstEdge[N], to[N << 1], nextEdge[N << 1];
int depth[N], go[N][D];
Node* root[N];
int Node::MAX;

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

void dfs(int p, int u, Node* r) {
    depth[u] = p == -1? 0: depth[p] + 1;
    go[u][0] = p;
    for (int i = 0; i < D && go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    root[u] = r->insert(weight[u]);
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        if (to[iter] != p) {
            dfs(u, to[iter], root[u]);
        }
    }
}

int jump(int u, int d) {
    for (int i = 0; i < D; ++ i) {
        if (d >> i & 1) {
            u = go[u][i];
        }
    }
    return u;
}

int lca(int u, int v) {
    if (depth[u] > depth[v]) {
        return lca(v, u);
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

int query(Node *x, Node *y, Node *z, int l, int r, int k, int extra) {
    if (l + 1 == r) {
        return l;
    }
    int m = (l + r) >> 1;
    int count = x->left->count + y->left->count - 2 * z->left->count;
    if (l <= extra && extra < m) {
        count ++;
    }
    if (count >= k) {
        return query(x->left, y->left, z->left, l, m, k, extra);
    }
    return query(x->right, y->right, z->right, m, r, k - count, extra);
}

int main() {
    null->left = null->right = null;
    scanf("%d%d", &n, &m);
    vector <int> values;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", weight + i);
        values.push_back(weight[i]);
    }
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());
    for (int i = 0; i < n; ++ i) {
        weight[i] = lower_bound(values.begin(), values.end(), weight[i]) 
            - values.begin();
    }
    Node::MAX = values.size();
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        addEdge(a, b);
        addEdge(b, a);
    }
    memset(go, -1, sizeof(go));
    dfs(-1, 0, null);
    while (m > 0) {
        m --;
        int a, b, k;
        scanf("%d%d%d", &a, &b, &k);
        a --, b --;
        int c = lca(a, b);
        printf("%d\n", values[query(root[a], root[b], root[c], 0, Node::MAX, k, weight[c])]);
    }
    return 0;
}
