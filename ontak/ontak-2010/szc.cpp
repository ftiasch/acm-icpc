// ONTAK 2010 Day 1 -- Peaks(szc)
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 200000;

int n, m, q, height[N];

struct Edge {
    int a, b, c;
};

bool operator <(const Edge &x, const Edge &y) {
    return x.c < y.c;
}

const int M = 500000;

Edge edges[M];

int parent[N];

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

int nodeCount, children[N][2], difficulty[N], order[N];

int start[N], size[N], leaveCount;

const int D = 20;

int go[N][D];

void dfs(int p, int u) {
    go[u][0] = p;
    for (int i = 0; go[u][i] != -1; ++ i) {
        go[u][i + 1] = go[go[u][i]][i];
    }
    if (u < n) {
        order[leaveCount] = u;
        start[u] = leaveCount ++;
        size[u] = 1;
    } else {
        start[u] = leaveCount;
        size[u] = 0;
        for (int k = 0; k < 2; ++ k) {
            dfs(u, children[u][k]);
            size[u] += size[children[u][k]];
        }
    }
}

struct Node {
    int count;
    Node *left, *right;

    Node(int count, Node *left, Node *right): count(count), left(left), right(right) {}

    Node* insert(int, int, int);

};

Node *null;

Node* Node::insert(int l, int r, int k) {
    if (k < l || r <= k) {
        return this;
    }
    if (l + 1 == r) {
        return new Node(count + 1, null, null);
    }
    int m = (l + r) >> 1;
    return new Node(count + 1, 
                    left->insert(l, m, k),
                    right->insert(m, r, k));
}

int find(Node *a, Node *b, int l, int r, int k) {
    if (a->count - b->count < k) {
        return -1;
    }
    if (l + 1 == r) {
        return l;
    }
    int m = (l + r) >> 1;
    int c = a->right->count - b->right->count;
    if (c >= k) {
        return find(a->right, b->right, m, r, k);
    }
    return find(a->left, b->left, l, m, k - c);
}

Node *trees[N];

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    scanf("%d%d%d", &n, &m, &q);
    vector <int> values;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", height + i);
        values.push_back(height[i]);
    }
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", &edges[i].a, &edges[i].b, &edges[i].c);
        edges[i].a --;
        edges[i].b --;
    }
    sort(edges, edges + m);
    for (int i = 0; i < n; ++ i) {
        parent[i] = i;
    }
    memset(children, -1, sizeof(children));
    nodeCount = n;
    for (int i = 0; i < m; ++ i) {
        int a = find(edges[i].a);
        int b = find(edges[i].b);
        int c = edges[i].c;
        if (a != b) {
            difficulty[nodeCount] = c;
            children[nodeCount][0] = a;
            children[nodeCount][1] = b;
            parent[nodeCount] = nodeCount;
            parent[a] = parent[b] = nodeCount ++;
        }
    }
    leaveCount = 0;
    memset(go, -1, sizeof(go));
    for (int i = 0; i < nodeCount; ++ i) {
        if (find(i) == i) {
            dfs(-1, i);
        }
    }
    trees[n] = null;
    for (int i = n - 1; i >= 0; -- i) {
        trees[i] = trees[i + 1]->insert(0, (int)values.size(),
                lower_bound(values.begin(), values.end(), height[order[i]]) - values.begin());
    }
    while (q > 0) {
        q --;
        int u, x, k;
        scanf("%d%d%d", &u, &x, &k);
        u --;
        for (int i = D - 1; i >= 0; -- i) {
            if (go[u][i] != -1 && difficulty[go[u][i]] <= x) {
                u = go[u][i];
            }
        }
        int ret = find(trees[start[u]], trees[start[u] + size[u]], 0, (int)values.size(), k);
        if (ret != -1) {
            ret = values[ret];
        }
        printf("%d\n", ret);
    }
    return 0;
}
