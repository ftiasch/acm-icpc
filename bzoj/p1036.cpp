#include <algorithm>
#include <cassert>
#include <climits>
#include <cstdio>
#include <cstring>
#include <utility>

const int N = 30000 + 1;

int first_edge[N], to[N << 1], next_edge[N << 1], weight[N];

void add_edge(int u, int v) {
    static int edge_count = 0;
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int path_count, depth[N], size[N], belong[N], head[N], length[N];

void divide(int p, int u) {
    size[u] = 1;
    depth[u] = depth[p] + 1;
    std::pair <int, int> result(0, -1);
    for (int it = first_edge[u]; ~it; it = next_edge[it]) {
        int v = to[it];
        if (v != p) {
            divide(u, v);
            result = std::max(result, std::make_pair(size[v], belong[v]));
            size[u] += size[v];
        }
    }
    belong[u] = result.first ? result.second : path_count ++;
    head[belong[u]] = p;
    length[belong[u]] ++;
}

int get_lca(int a, int b) {
    while (belong[a] != belong[b]) {
        int &p = depth[head[belong[a]]] > depth[head[belong[b]]] ? a : b;
        p = head[belong[p]];
    }
    return depth[a] < depth[b] ? a : b;
}

struct Info {
    int max, sum;

    Info() : max(INT_MIN), sum(0) {}
    Info(int max, int sum) : max(max), sum(sum) {}
};

Info merge(const Info &a, const Info &b) {
    return Info(std::max(a.max, b.max), a.sum + b.sum);
}

struct Node {
    Info info;
    Node *left, *right;

    void update() {
        info = merge(left->info, right->info);
    }

    void modify(int l, int r, int k, int v) {
        if (l <= k && k <= r) {
            if (l < r) {
                int m = l + r >> 1;
                left->modify(l, m, k, v);
                right->modify(m + 1, r, k, v);
                update();
            } else {
                info.max = info.sum = v;
            }
        }
    }

    Info query(int l, int r, int a, int b) {
        if (b < l || r < a) {
            return Info(INT_MIN, 0);
        }
        if (a <= l && r <= b) {
            return info;
        }
        int m = l + r >> 1;
        return merge(left->query(l, m, a, b), right->query(m + 1, r, a, b));
    }
};

Node* trees[N];

Node* build(int l, int r) {
    Node* node = new Node();
    if (l < r) {
        int m = l + r >> 1;
        node->left = build(l, m);
        node->right = build(m + 1, r);
    }
    return node;
}


void modify(int u, int w) {
    int p = belong[u];
    trees[p]->modify(1, length[p], depth[u] - depth[head[p]], w);
}

Info query(int u, int d) {
    Info result(INT_MIN, 0);
    while (d > 0) {
        int p = belong[u];
        int i = depth[u] - depth[head[p]];
        result = merge(result, trees[p]->query(1, length[p], std::max(i - d + 1, 1), i));
        u = head[p];
        d -= i;
    }
    return result;
}

int main() {
    int n;
    scanf("%d", &n);
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        add_edge(a, b);
        add_edge(b, a);
    }
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", weight + i);
    }
    path_count = 0;
    depth[0] = -1;
    memset(length, 0, sizeof(length));
    divide(0, 1);
    for (int i = 0; i < path_count; ++ i) {
        trees[i] = build(1, length[i]);
    }
    for (int i = 1; i <= n; ++ i) {
        modify(i, weight[i]);
    }
    int q;
    scanf("%d", &q);
    while (q --) {
        char buffer[7];
        scanf("%s", buffer);
        if (*buffer == 'C') {
            int u, w;
            scanf("%d%d", &u, &w);
            modify(u, w);
        } else {
            int a, b;
            scanf("%d%d", &a, &b);
            int c = get_lca(a, b);
            Info result = merge(query(a, depth[a] - depth[c] + 1), query(b, depth[b] - depth[c]));
            printf("%d\n", buffer[1] == 'M' ? result.max : result.sum);
        }
    }
    return 0;
}
