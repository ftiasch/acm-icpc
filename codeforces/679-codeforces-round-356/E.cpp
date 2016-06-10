#include <algorithm>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <iostream>
#include <numeric>
#include <tuple>

typedef long long Long;

Long cal_diff(Long v)
{
    Long p = 1;
    while (p < v) {
        p *= 42;
    }
    return p - v;
}

struct Node
{
    Node() = default;
    Node(const Node& other) = default;
    Node(Long, int);

    void add(Long);

    void push_down()
    {
        if (add_val)
        {
            left->add(add_val);
            right->add(add_val);
            add_val = 0;
        }
    }

    Long get(int index)
    {
        if (index < left->tot_len) {
            return left->get(index) + add_val;
        }
        index -= left->tot_len;
        if (index < len) {
            return val;
        }
        index -= len;
        return right->get(index) + add_val;
    }

    Node* update()
    {
        tot_len = left->tot_len + len + right->tot_len;
        min_diff = std::min(diff, std::min(left->min_diff, right->min_diff));
        return this;
    }

    bool fix()
    {
        if (min_diff > 0) {
            return false;
        }
        push_down();
        bool result = left->fix() || right->fix();
        if (diff <= 0) {
            diff = cal_diff(val);
            result |= diff == 0;
        }
        update();
        return result;
    }

    std::pair<Node*, Node*> split(int);

    int len, tot_len;
    Long val, add_val, diff, min_diff;
    Node *left, *right;
};

Node* null;

bool random(int a, int b)
{
    return rand() % (a + b) < a;
}

Node* merge(Node* a, Node* b)
{
    if (a == null) {
        return b;
    }
    if (b == null) {
        return a;
    }
    if (random(a->tot_len, b->tot_len)) {
        a->push_down();
        a->right = merge(a->right, b);
        return a->update();
    } else {
        b->push_down();
        b->left = merge(a, b->left);
        return b->update();
    }
}

Node::Node(Long v, int l)
: len(l), tot_len(l), val(v), add_val(0), diff(cal_diff(v)), min_diff(diff), left(null), right(null)
{
}

void Node::add(Long d)
{
    if (this != null) {
        val += d;
        add_val += d;
        diff -= d;
        min_diff -= d;
    }
}

std::pair<Node*, Node*> Node::split(int s)
{
    if (this == null) {
        return {null, null};
    }
    push_down();
    if (s <= left->tot_len) {
        auto p = left->split(s);
        left = p.second;
        return {p.first, update()};
    }
    s -= left->tot_len;
    if (s < len) {
        Node* n = new Node(*this);
        n->left = null;
        right = null;
        n->len = len - s;
        len = s;
        return {update(), n->update()};
    }
    s -= len;
    auto p = right->split(s);
    right = p.first;
    return {update(), p.second};
}

int main()
{
    null = new Node();
    null->tot_len = 0;
    null->min_diff = std::numeric_limits<Long>::max();
    int n, q;
    scanf("%d%d", &n, &q);
    Node* root = null;
    for (int i = 0; i < n; ++ i) {
        int t;
        scanf("%d", &t);
        root = merge(root, new Node(t, 1));
    }
    while (q --) {
        int t, a, b, c;
        scanf("%d", &t);
        if (t == 1) {
            scanf("%d", &a);
            std::cout << root->get(a - 1) << "\n";
        } else {
            scanf("%d%d%d", &a, &b, &c);
            Node *l, *x, *r;
            std::tie(x, r) = root->split(b);
            std::tie(l, x) = x->split(a - 1);
            if (t == 2) {
                root = merge(l, merge(new Node(c, b - a + 1), r));
            } else {
                do {
                    x->add(c);
                } while (x->fix());
                root = merge(l, merge(x, r));
            }
        }
    }
    std::cout << std::flush;
}
