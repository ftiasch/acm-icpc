#include <cstdio>
#include <iostream>
#include <utility>
#include <map>
#include <queue>
#include <vector>

const int N = 100000;
const int MOD = (int)1e9 + 7;

std::vector <std::pair <int, int>> graph[N];

struct Node {
    Node() {}

    Node(int k, Node *left, Node *right) : left(left), right(right)
    {
        first = left->first + (left->first < 1 << k ? 0 : right->first);
    }

    int find(int k, int i)
    {
        if (!i) {
            return first;
        }
        int s = 1 << k;
        if (i < s) {
            int ret = left->find(k - 1, i);
            if (ret < s) {
                return ret;
            }
            i = s;
        }
        return s + right->find(k - 1, i - s);
    }

    Node* set(int, int, int, int);

    int to_i(int, int);

    int  first;
    Node *left, *right;
};

std::map <std::pair <Node*, Node*>, Node*> map;

Node* merge(int k, Node *left, Node *right)
{
    Node* &ref = map[{left, right}];
    if (ref == NULL) {
        ref = new Node(k, left, right);
    }
    return ref;
}

const int M = 18;

Node* templates[2][M];

int compare(Node *a, Node *b)
{
    if (a == b) {
        return 0;
    }
    int ret = compare(a->right, b->right);
    if (!ret) {
        ret = compare(a->left, b->left);
    }
    if (ret == 0) {
        ret = (a == templates[1][0]) - (b == templates[1][0]);
    }
    return ret;
}

typedef std::pair <Node*, int> Pair;

struct Comparator
{
    bool operator()(const Pair &p, const Pair &q)
    {
        return compare(p.first, q.first) > 0;
    }
};


Node* Node::set(int k, int a, int b, int c)
{
    int s = 1 << k + 1;
    if (b <= 0 || s <= a) {
        return this;
    }
    if (a <= 0 && s <= b) {
        return templates[c][k + 1];
    }
    s = 1 << k;
    return merge(k, left->set(k - 1, a, b, c), right->set(k - 1, a - s, b - s, c));
}

Node* add(Node *root, int i)
{
    int j = root->find(M - 2, i);
    if (i < j) {
        root = root->set(M - 2, i, j, 0);
    }
    return root->set(M - 2, j, j + 1, 1);
}

int Node::to_i(int k, int hash)
{
    if (k == 0) {
        return ((hash << 1) + (this == templates[1][0])) % MOD;
    }
    return left->to_i(k - 1, right->to_i(k - 1, hash));
}

int predecessor[N];
Node* distance[N];

int main()
{
    for (int i = 0; i < 2; ++ i) {
        Node* &ref = templates[i][0] = new Node();
        ref->first = i;
        ref->left = ref->right = NULL;
        for (int j = 1; j < M; ++ j) {
            templates[i][j] = merge(j - 1, templates[i][j - 1], templates[i][j - 1]);
        }
    }
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        graph[a].push_back({b, c});
        graph[b].push_back({a, c});
    }
    int s, t;
    scanf("%d%d", &s, &t);
    s --;
    t --;
    for (int i = 0; i < n; ++ i) {
        distance[i] = templates[1][M - 1];
    }
    distance[s] = templates[0][M - 1];
    std::priority_queue <Pair, std::deque <Pair>, Comparator> heap;
    heap.push({distance[s], s});
    while (!heap.empty()) {
        Pair ret = heap.top();
        heap.pop();
        int u = ret.second;
        if (ret.first == distance[u]) {
            for (auto iterator : graph[u]) {
                int v = iterator.first;
                Node* new_distance = add(distance[u], iterator.second);
                if (compare(new_distance, distance[v]) < 0) {
                    distance[v] = new_distance;
                    predecessor[v] = u;
                    heap.push({distance[v], v});
                }
            }
        }
    }
    if (distance[t] == templates[1][M - 1]) {
        puts("-1");
    } else {
        printf("%d\n", distance[t]->to_i(M - 1, 0));
        std::vector <int> path;
        for (int v = t; v != s; v = predecessor[v]) {
            path.push_back(v);
        }
        path.push_back(s);
        printf("%d\n", (int)path.size());
        for (int i = (int)path.size() - 1; i >= 0; -- i) {
            printf("%d%c", path[i] + 1, " \n"[i == 0]);
        }
    }
    return 0;
}
