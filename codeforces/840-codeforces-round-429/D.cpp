#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>

struct Node;

Node* null;

struct Node
{
    Node* insert(int l, int r, int k)
    {
        if (k < l || r < k) {
            return this;
        }
        if (l == r) {
            return new Node { count + 1, null, null };
        }
        int m = l + r >> 1;
        return new Node { count + 1, left->insert(l, m, k), right->insert(m + 1, r, k) };
    }

    int count;
    Node *left, *right;
};

int query(Node* p, Node* q, int l, int r, int limit)
{
    if (q->count - p->count <= limit) {
        return INT_MAX;
    }
    if (l == r) {
        return l;
    }
    int m = l + r >> 1;
    return std::min(query(p->left, q->left, l, m, limit), query(p->right, q->right, m + 1, r, limit));
}

const int N = 300000;

Node* tree[N + 1];

int main()
{
#ifdef LOCAL_JUDGE
    freopen("D.in", "r", stdin);
#endif
    null = new Node {0, nullptr, nullptr};
    null->left = null->right = null;
    int n, q;
    while (scanf("%d%d", &n, &q) == 2) {
        tree[0] = null;
        for (int i = 1, a; i <= n; ++ i) {
            scanf("%d", &a);
            tree[i] = tree[i - 1]->insert(1, n, a);
        }
        while (q --) {
            int l, r, k;
            scanf("%d%d%d", &l, &r, &k);
            int limit = (r - l + 1) / k;
            int result = query(tree[l - 1], tree[r], 1, n, limit);
            printf("%d\n", result == INT_MAX ? -1 : result);
        }
    }
}
