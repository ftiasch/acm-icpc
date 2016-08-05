#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 50000;
const int MAX_NODE = 40 * N;

int depth[N], order[N], parent[N], position[N], size[N];
std::vector<int> edge[N];

void prepare(int u)
{
    depth[u] = ~parent[u] ? depth[parent[u]] + 1 : 0;
    order[position[u]] = u;
    size[u] = 1;
    foreach (iterator, edge[u]) {
        int& v = *iterator;
        if (v != parent[u]) {
            parent[v] = u;
            position[v] = position[u] + size[u];
            prepare(v);
            size[u] += size[v];
        }
    }
}

struct Summary
{
    Summary(int length = 0, int maximum = INT_MIN, int minimum = INT_MAX, int sum = 0) : length(length), maximum(maximum), minimum(minimum), sum(sum) {}

    Summary add(int delta) const {
        return Summary(length, maximum + delta, minimum + delta, sum + length * delta);
    }

    int length, maximum, minimum, sum;
};

Summary merge(const Summary& a, const Summary& b)
{
    return Summary(a.length + b.length, std::max(a.maximum, b.maximum), std::min(a.minimum, b.minimum), a.sum + b.sum);
}

struct Node
{
    int delta;
    Summary summary;
    Node* left;
    Node* right;

    Node* cover(int, int, int, int, int);
    Summary query(int, int, int, int);
};

int node_count;
Node nodes[MAX_NODE];
Node* null;
Node* tree[N];

Node* new_node(int delta, const Summary& summary, Node* left, Node* right)
{
    Node* node = nodes + (node_count ++);
    node->delta = delta;
    node->summary = summary;
    node->left = left;
    node->right = right;
    return node;
}

Node* build(int l, int r)
{
    if (l == r) {
        int d = depth[order[l]];
        return new_node(0, Summary(1, d, d, d), null, null);
    }
    int m = l + r >> 1;
    Node* left_tree = build(l, m);
    Node* right_tree = build(m + 1, r);
    return new_node(0, merge(left_tree->summary, right_tree->summary), left_tree, right_tree);
}

Node* Node::cover(int l, int r, int a, int b, int c)
{
    if (b < l || r < a) {
        return this;
    }
    if (a <= l && r <= b) {
        return new_node(delta + c, summary.add(c), left, right);
    }
    int m = l + r >> 1;
    Node* new_left = left->cover(l, m, a, b, c);
    Node* new_right = right->cover(m + 1, r, a, b, c);
    return new_node(delta, merge(new_left->summary, new_right->summary).add(delta), new_left, new_right);
}

Summary Node::query(int l, int r, int a, int b)
{
    if (b < l || r < a) {
        return Summary();
    }
    if (a <= l && r <= b) {
        return summary;
    }
    int m = l + r >> 1;
    return merge(left->query(l, m, a, b), right->query(m + 1, r, a, b)).add(delta);
}

int main()
{
    null = new Node();
    null->delta = 0;
    null->left = null->right = null;
    int n, q;
    while (scanf("%d%d", &n, &q) == 2) {
        for (int i = 0; i < n; ++ i) {
            edge[i].clear();
        }
        for (int i = 0; i < n - 1; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            a --;
            b --;
            edge[a].push_back(b);
            edge[b].push_back(a);
        }
        parent[0] = -1;
        position[0] = 0;
        prepare(0);
        node_count = 0;
        tree[0] = build(0, n - 1);
        for (int i = 1; i < n; ++ i) {
            int u = order[i];
            tree[u] = tree[parent[u]]->cover(0, n - 1, 0, n - 1, 1)->cover(0, n - 1, position[u], position[u] + size[u] - 1, -2);
        }
        int last_answer = 0;
        for (int _ = 0; _ < q; ++ _) {
            int k, p, t;
            scanf("%d%d%d", &k, &p, &t);
            p = (p + last_answer) % n;
            std::vector<std::pair<int, int> > events;
            events.push_back(std::make_pair(0, 0));
            events.push_back(std::make_pair(n, 0));
            for (int __ = 0; __ < k; ++ __) {
                int a;
                scanf("%d", &a);
                a --;
                events.push_back(std::make_pair(position[a], 1));
                events.push_back(std::make_pair(position[a] + size[a], -1));
            }
            std::sort(events.begin(), events.end());
            Summary summary;
            int delta = 0;
            for (int i = 0; i + 1 < k + 1 << 1; ++ i) {
                delta += events[i].second;
                if (!delta && events[i].first < events[i + 1].first) {
                    summary = merge(summary, tree[p]->query(0, n - 1, events[i].first, events[i + 1].first - 1));
                }
            }
            if (!summary.length) {
                last_answer = -1;
            } else if (t == 1) {
                last_answer = summary.sum;
            } else if (t == 2) {
                last_answer = summary.minimum;
            } else if (t == 3) {
                last_answer = summary.maximum;
            }
            printf("%d\n", last_answer);
            if (last_answer == -1) {
                last_answer = 0;
            }
        }
    }
}
