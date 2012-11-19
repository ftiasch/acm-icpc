// Codeforces Round #131
// Problem E -- Two Permutations
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 200000;

int n, m, length, text[N + N + 2];

struct Node {
    int count;
    Node *left, *right;

    Node(int count, Node *left, Node *right) : count(count), left(left), right(right) {
    }

    Node* insert(int, int, int);

    int query(int l, int r, int k) {
        if (k < l) {
            return 0;
        }
        if (r <= k) {
            return count;
        }
        int m = l + r >> 1;
        return left->query(l, m, k) + right->query(m + 1, r, k);
    }
};

Node *null;

Node* Node::insert(int l, int r, int k) {
    if (k < l || r < k) {
        return this;
    }
    if (l == r) {
        return new Node(count + 1, null, null);
    }
    int m = l + r >> 1;
    return new Node(count + 1, left->insert(l, m, k), right->insert(m + 1, r, k));
}

int rank[N + 1], fail[N + N + 2];
bool match[N + N + 2];
Node* tree[N + N + 2];

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    scanf("%d%d", &n, &m);
    length = 0;
    memset(text, 0, sizeof(text));
    for (int i = 1; i <= n; ++ i) {
        int a;
        scanf("%d", &a);
        text[a] = i;
    }
    length += n + 1;
    for (int i = 1; i <= m; ++ i) {
        int b;
        scanf("%d", &b);
        text[length + b] = i;
    }
    length += m;
    tree[0] = null;
    for (int i = 1; i <= length; ++ i) {
        tree[i] = tree[i - 1]->insert(1, N, text[i]);
    }
    for (int i = 1; i <= n; ++ i) {
        rank[i] = tree[i - 1]->query(1, N, text[i]);
    }
    for (int i = 2; i <= length; ++ i) {
        if (text[i]) {
            int p = i - 1;
            while (p) {
                p = fail[p];
                if (text[p + 1] && tree[i - 1]->query(1, N, text[i]) - tree[i - 1 - p]->query(1, N, text[i]) == rank[p + 1]) {
                    fail[i] = p + 1;
                    break;
                }
            }
        }
    }
    memset(match, 0, sizeof(match));
    match[n] = true;
    int result = 0;
    for (int i = n + 1; i <= length; ++ i) {
        result += (match[i] = match[fail[i]]);
    }
    printf("%d\n", result);
    return 0;
}
