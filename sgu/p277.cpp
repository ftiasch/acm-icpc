// SGU 277 -- Heroes
#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <algorithm>

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}

    Point negate() const {
        return Point(-x, -y);
    }
};

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

std::ostream &operator <<(std::ostream &out, const Point &p) {
    return out << "(" << p.x << ", " << p.y << ")";
}

bool operator <(const Point &a, const Point &b) {
    if (a.x != b.x) {
        return a.x < b.x;
    }
    return a.y < b.y;
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

Point operator - (const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

bool is_convex(const Point &a, const Point &b, const Point &c) {
    return det(b - a, c - b) > 0;
}

const int N = (100003 << 1) + 1;

int treap_count, children[N][2], weight[N], size[N];
Point key[N];

void update(int &x) {
    size[x] = size[children[x][0]] + 1 + size[children[x][1]];
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, const Point &k) {
    if (!x) {
        x = treap_count ++;
        key[x] = k;
        weight[x] = rand();
    } else {
        int t = key[x] < k;
        insert(children[x][t], k);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

void erase(int &x, const Point &k) {
    if (k == key[x]) {
        if (!children[x][0] && !children[x][1]) {
            x = 0;
            return;
        }
        rotate(x, weight[children[x][0]] > weight[children[x][1]]);
        erase(x, k);
    } else {
        erase(children[x][key[x] < k], k);
    }
    update(x);
}

int count(int &x, const Point &k) {
    if (!x) {
        return 0;
    }
    if (k < key[x]) {
        return count(children[x][0], k);
    }
    return size[children[x][0]] + 1 + count(children[x][1], k);
}

Point find(int &x, int k) {
    if (k <= size[children[x][0]]) {
        return find(children[x][0], k);
    }
    if (k == size[children[x][0]] + 1) {
        return key[x];
    }
    return find(children[x][1], k - (size[children[x][0]] + 1));
}

struct Hull {
    int root;
    long long area;

    Hull(): root(0), area(0LL) {}
    
    void add(const Point &p) {
        int a = count(root, p);
        int b = size[root] - a;
        if (a && b) {
            Point u = find(root, a);
            Point v = find(root, a + 1);
            if (!is_convex(u, p, v)) {
                return;
            }
            area -= det(u, v);
        }
        while (a >= 2) {
            Point u = find(root, a - 1);
            Point v = find(root, a);
            if (is_convex(u, v, p)) {
                break;
            }
            area -= det(u, v);
            erase(root, v);
            a --;
        }
        while (b >= 2) {
            Point u = find(root, a + 1);
            Point v = find(root, a + 2);
            if (is_convex(p, u, v)) {
                break;
            }
            area -= det(u, v);
            erase(root, u);
            b --;
        }
        if (a) {
            area += det(find(root, a), p);
        }
        if (b) {
            area += det(p, find(root, a + 1));
        }
        insert(root, p);
    }
};

int main() {
    size[0] = 0;
    weight[0] = INT_MAX;
    memset(children, 0, sizeof(children));
    treap_count = 1;
    Hull bottom, up;
    for (int i = 0; i < 3; ++ i) {
        Point p;
        std::cin >> p;
        bottom.add(p);
        up.add(p.negate());
    }
    int n;
    std::cin >> n;
    while (n --) {
        Point p;
        std::cin >> p;
        bottom.add(p);
        up.add(p.negate());
        std::cout << bottom.area + up.area << std::endl;
    }
    return 0;
}
