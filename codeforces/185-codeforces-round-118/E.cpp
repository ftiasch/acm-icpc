// Codeforces Round #118
// Problem E -- Soap Time! - 2
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1000000000;

struct Point {
    int id, x, y;

    Point(int id = 0, int x = 0, int y = 0): id(id), x(x), y(y) {}
};

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        if (a.y == b.y) {
            return a.id > b.id;
        }
        return a.y < b.y;
    }
    return a.x < b.x;
}

const int N = 200000;

int n, m, near[N];
Point points[N], points2[N];

namespace Treap{
    int node_count, key[N], weight[N], value[N], maximum[N], children[N][2];
    
    void update(int &x) {
        maximum[x] = max(value[x], max(maximum[children[x][0]], maximum[children[x][1]]));
    }

    void rotate(int &x, int t) {
        int y = children[x][t];
        children[x][t] = children[y][1 ^ t];
        children[y][1 ^ t] = x;
        update(x);
        update(y);
        x = y;
    }

    void insert(int &x, int k, int v) {
        if (x == 0) {
            x = ++ node_count;
            key[x] = k;
            value[x] = v;
            weight[x] = rand();
            children[x][0] = children[x][1] = 0;
        } else if (key[x] == k) {
            value[x] = max(value[x], v);
        } else {
            int t = key[x] < k;
            insert(children[x][t], k, v);
            if (weight[children[x][t]] < weight[x]) {
                rotate(x, t);
            }
        }
        update(x);
    }

    int query(int &x, int k) {
        if (x == 0) {
            return -INF;
        }
        if (k < key[x]) {
            return query(children[x][0], k);
        }
        return max(max(maximum[children[x][0]], value[x]), query(children[x][1], k));
    }
}

namespace SegmentTree {
    vector <int> x_values, y_values;

    struct Node {
        int total;
        Node *left, *right;

        Node(int total, Node* left, Node* right): total(total), left(left), right(right) {}

        Node* insert(int l, int r, int k) {
            if (k < y_values[l] || y_values[r] < k) {
                return this;
            }
            if (l == r) {
                return new Node(total + 1, NULL, NULL);
            }
            int m = (l + r) >> 1;
            return new Node(total + 1, left->insert(l, m, k), right->insert(m + 1, r, k));
        }

        int query(int l, int r, int a, int b) {
            if (b < y_values[l] || y_values[r] < a) {
                return 0;
            }
            if (a <= y_values[l] && y_values[r] <= b) {
                return total;
            }
            int m = (l + r) >> 1;
            return left->query(l, m, a, b) + right->query(m + 1, r, a, b);
        }
    };

    Node* build(int l, int r) {
        int m = (l + r) >> 1;
        bool is_leaf = l == r;
        return new Node(0, is_leaf? NULL: build(l, m), is_leaf? NULL: build(m + 1, r));
    }

    Node* roots[N];

    void initilize() {
        sort(points, points + (n + m));
        for (int i = 0; i < (n + m); ++ i) {
            if (points[i].id >= n) {
                x_values.push_back(points[i].x);
                y_values.push_back(points[i].y);
            }
        }
        sort(x_values.begin(), x_values.end());
        x_values.erase(unique(x_values.begin(), x_values.end()), x_values.end());
        sort(y_values.begin(), y_values.end());
        y_values.erase(unique(y_values.begin(), y_values.end()), y_values.end());
        if (m > 0) {
            roots[0] = build(0, (int)y_values.size() - 1);
            for (int i = 0, j = 0; i < (n + m); ++ i) {
                if (points[i].id >= n) {
                    if (j == 0 || x_values[j - 1] != points[i].x) {
                        roots[j + 1] = roots[j];
                        j ++;
                    }
                    roots[j] = roots[j]->insert(0, (int)y_values.size() - 1, points[i].y);
                }
            }
        }
    }

    Node* get_tree(int x) {
        return roots[upper_bound(x_values.begin(), x_values.end(), x) - x_values.begin()];
    }

    bool query(int x_1, int x_2, int y_1, int y_2) {
        if (m == 0) {
            return false;
        }
        return get_tree(x_2)->query(0, (int)y_values.size() - 1, y_1, y_2) - get_tree(x_1 - 1)->query(0, (int)y_values.size() - 1, y_1, y_2) > 0;
    }
}

vector <pair <int, Point> > orders;

bool check(int limit) {
    if (orders.back().first <= limit) {
        return true;
    }
    int x_min = INT_MIN;
    int x_max = INT_MAX;
    int y_min = INT_MIN;
    int y_max = INT_MAX;
    for (int i = n - 1; i >= 0; -- i) {
        Point p = orders[i].second;
        x_min = max(x_min, p.x - limit);
        x_max = min(x_max, p.x + limit);
        y_min = max(y_min, p.y - limit);
        y_max = min(y_max, p.y + limit);
        if (x_min > x_max || y_min > y_max) {
            return false;
        }
        if (i == 0) {
            return true;
        }
        if (orders[i - 1].first <= limit) {
            int left = limit - orders[i - 1].first;
            if (SegmentTree::query(x_min - left, x_max + left, y_min - left, y_max + left)) {
                return true;
            }
        }
    }
    return false;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        points[i].id = i;
        scanf("%d%d", &points[i].x, &points[i].y);
    }
    for (int i = 0; i < m; ++ i) {
        points[n + i].id = n + i;
        scanf("%d%d", &points[n + i].x, &points[n + i].y);
    }
    Treap::weight[0] = INT_MAX;
    Treap::maximum[0] = -INF;
    for (int i = 0; i < n; ++ i) {
        near[i] = INF;
    }
    for (int direction = 0; direction < 4; ++ direction) {
        for (int i = 0; i < (n + m); ++ i) {
            points[i] = Point(points[i].id, -points[i].y, points[i].x);
        }
        int root = Treap::node_count = 0;
        sort(points, points + (n + m));
        for (int i = 0; i < (n + m); ++ i) {
            if (points[i].id < n) {
                near[points[i].id] = min(near[points[i].id], points[i].x + points[i].y - Treap::query(root, points[i].y));
            } else {
                Treap::insert(root, points[i].y, points[i].x + points[i].y);
            }
        }
    }
    for (int i = 0; i < (n + m); ++ i) {
        points[i] = Point(points[i].id, points[i].x - points[i].y, points[i].x + points[i].y);
        if (points[i].id < n) {
            orders.push_back(make_pair(near[points[i].id], points[i]));
        }
    }
    sort(orders.begin(), orders.end());
    SegmentTree::initilize();
    int lower = 0;
    int upper = 200000000;
    while (lower < upper) {
        int mider = (lower + upper) >> 1;
        if (check(mider)) {
            upper = mider;
        } else {
            lower = mider + 1;
        }
    }
    printf("%d\n", upper);
    return 0;
}
