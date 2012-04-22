#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 55555;
const int MAX = 16;

struct Node {
    Node* childs[2];

    Node() {
        childs[0] = childs[1] = NULL;
    }
};

int node_count;
Node nodes[N * MAX * MAX];
Node* roots[N << 2];

#define LEFT(t) ((t) + (t))
#define RIGHT(t) ((t) + (t) + 1)

void build(int t, int l, int r) {
    roots[t] = nodes + (node_count ++);
    if (l < r) {
        int m = (l + r) >> 1;
        build(LEFT(t), l, m);
        build(RIGHT(t), m + 1, r);
    }
}

void insert(int t, int l, int r, int i, int a_i) {
    if (l <= i && i <= r) {
        Node *p = roots[t];
        for (int bit = MAX; bit >= 0; -- bit) {
            int token = a_i >> bit & 1;
            if (p->childs[token] == NULL) {
                p->childs[token] = nodes + (node_count ++);
            }
            p = p->childs[token];
        }
        if (l < r) {
            int m = (l + r) >> 1;
            insert(LEFT(t), l, m, i, a_i);
            insert(RIGHT(t), m + 1, r, i, a_i);
        }
    }
}

int query(int t, int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return INT_MIN;
    }
    if (a <= l && r <= b) {
        int result = 0;
        Node *p = roots[t];
        for (int bit = MAX; bit >= 0; -- bit) {
            int token = c >> bit & 1;
            if (p->childs[token ^ 1] != NULL) {
                result |= 1 << bit;
                p = p->childs[token ^ 1];
            } else {
                p = p->childs[token];
            }
        }
        return result;
    } else {
        int m = (l + r) >> 1;
        return max(query(LEFT(t), l, m, a, b, c),
                query(RIGHT(t), m + 1, r, a, b, c));
    }
}

int main() {
    node_count = 0;
    int n, m;
    scanf("%d%d", &n, &m);
    build(1, 1, n);
    for (int i = 1; i <= n; ++ i) {
        int a_i;
        scanf("%d", &a_i);
        insert(1, 1, n, i, a_i);
//puts("okay");
    }
    while (m --) {
        int l_i, r_i, b_i;
        scanf("%d%d%d", &l_i, &r_i, &b_i);
        printf("%d\n", query(1, 1, n, l_i, r_i, b_i));
    }
    return 0;
}
