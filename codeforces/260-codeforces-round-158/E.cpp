// Codeforces Round #158
// Problem E -- Dividing Kingdom
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

using std::vector;

const int INF = 1000000000;

struct Point {
    int x, y;

    Point() : x(0), y(0) {}

    Point(int x, int y) : x(x), y(y) {}
};

bool by_x(const Point &a, const Point &b) {
    return a.x < b.x;
}

bool by_y(const Point &a, const Point &b) {
    return a.y < b.y;
}

const int N = 100000;

int n, a[9];
Point point[N], point_by_x[N], point_by_y[N];

int get_middle(int low, int high) {
    int middle = low + high;
    if (middle % 2) {
        middle --;
    }
    return middle / 2;
}

struct Node {
    int count;
    Node *left, *right;

    Node(int count, Node *left, Node *right) : count(count), left(left), right(right) {}

    Node* insert(int, int, int);
    int query(int, int, int);
};

Node *null;

Node* Node::insert(int l, int r, int k) {
    if (k < l || r < k) {
        return this;
    }
    if (l == r) {
        return new Node(count + 1, null, null);
    } 
    int m = get_middle(l, r);
    return new Node(count + 1, left->insert(l, m, k), right->insert(m + 1, r, k));
}

int Node::query(int l, int r, int k) {
    if (this == null || k <= l) {
        return 0;
    }
    if (r < k) {
        return count;
    }
    int m = get_middle(l, r);
    return left->query(l, m, k) + right->query(m + 1, r, k);
}

vector <int> xvalue;
vector <Node*> tree;

int range_query(int x, int y) { // < x, < y
    int i = std::lower_bound(xvalue.begin(), xvalue.end(), x) - xvalue.begin();
    return tree[i]->query(-INF, INF, y);
}

double mix(int x) {
    return x - 0.5;
}

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &point[i].x, &point[i].y);
        xvalue.push_back(point[i].x);
    }
    std::sort(xvalue.begin(), xvalue.end());
    xvalue.erase(std::unique(xvalue.begin(), xvalue.end()), xvalue.end());
    std::copy(point, point + n, point_by_x);
    std::sort(point_by_x, point_by_x + n, by_x);
    std::copy(point, point + n, point_by_y);
    std::sort(point_by_y, point_by_y + n, by_y);
    tree.push_back(null);
    for (int i = 0, j = 0; i < (int)xvalue.size(); ++ i) {
        Node* root = tree.back();
        while (j < n && point_by_x[j].x == xvalue[i]) {
            root = root->insert(-INF, INF, point_by_x[j ++].y);
        }
        tree.push_back(root);
    }
    int p[9], b[9];
    for (int i = 0; i < 9; ++ i) {
        scanf("%d", a + i);
        p[i] = i;
    }
    do {
        for (int i = 0; i < 9; ++ i) {
            b[i] = a[p[i]];
        }
        bool valid = true;
        int x_0 = point_by_x[b[0] + b[1] + b[2] - 1].x + 1;
        int x_1 = point_by_x[n - (b[6] + b[7] + b[8])].x;
        int y_0 = point_by_y[b[0] + b[3] + b[6] - 1].y + 1;
        int y_1 = point_by_y[n - (b[2] + b[5] + b[8])].y;
        valid &= range_query(x_0, INF + 1) == b[0] + b[1] + b[2];
        valid &= range_query(INF + 1, y_0) == b[0] + b[3] + b[6];
        valid &= range_query(x_1, INF + 1) == b[0] + b[1] + b[2] + b[3] + b[4] + b[5];
        valid &= range_query(INF + 1, y_1) == b[0] + b[3] + b[6] + b[1] + b[4] + b[7];
        valid &= range_query(x_0, y_0) == b[0];
        valid &= range_query(x_0, y_1) == b[0] + b[1];
        valid &= range_query(x_1, y_0) == b[0] + b[3];
        valid &= range_query(x_1, y_1) == b[0] + b[1] + b[3] + b[4];
        if (valid) {
            printf("%.8f %.8f\n%.8f %.8f\n", mix(x_0), mix(x_1), mix(y_0), mix(y_1));
            return 0;
        }
    } while (std::next_permutation(p, p + 9));
    puts("-1");
    return 0;
}
