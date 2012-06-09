// SGU 128 -- Snake
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

struct Point {
    int id, x, y;
};

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        return a.y < b.y;
    }
    return a.x < b.x;
}

const int N = 22222;
const int DELTA = 10000;

int n, length, degree[N], parent[N], treap_count, key[N], weight[N], children[N][2];
Point points[N];
Point *previous[N];

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

void connect(const Point &a, const Point &b) {
    int i = a.id;
    int j = b.id;
    length += abs(a.x - b.x) + abs(a.y - b.y);
    degree[i] ++;
    degree[j] ++;
    if (find(i) != find(j)) {
        parent[find(i)] = find(j);
    }
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    x = y;
}

void insert(int &x, int k) {
    if (x == 0) {
        x = ++ treap_count;
        key[x] = k;
        weight[x] = rand();
        children[x][0] = children[x][1] = 0;
    } else {
        int t = key[x] < k;
        insert(children[x][t], k);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
}

void erase(int &x, int k) {
    if (key[x] != k) {
        erase(children[x][key[x] < k], k); 
    } else if (children[x][0] + children[x][1] == 0) {
        x = 0;
    } else {
        rotate(x, weight[children[x][0]] > weight[children[x][1]]);
        erase(x, k);
    }
}

bool query(int &x, int l, int r) {
    if (x == 0 || l > r) {
        return false;
    }
    if (l <= key[x] && key[x] <= r) {
        return true;
    }
    return query(children[x][key[x] < l], l, r);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        points[i].id = i;
        scanf("%d%d", &points[i].x, &points[i].y);
        points[i].x += DELTA;
        points[i].y += DELTA;
    }
    sort(points, points + n);
    memset(degree, 0, sizeof(degree));
    for (int i = 0; i < n; ++ i) {
        parent[i] = i;
    }
    memset(previous, 0, sizeof(previous));
    length = 0;
    bool check = true;
    int root = 0;
    treap_count = 0;
    weight[0] = INT_MAX;
    for (int lower = 0; lower < n; ++ lower) {
        int upper = lower + 1;
        while (upper < n && points[lower].x == points[upper].x) {
            upper ++;
        }
        for (int i = lower; i < upper; ++ i) {
            Point* &reg = previous[points[i].y];
            if (reg == NULL) {
                insert(root, points[i].y);
                reg = &points[i];
            } else {
                connect(*reg, points[i]);
                erase(root, points[i].y);
                reg = NULL;
            }
            if (((i - lower) & 1) == 1) {
                connect(points[i - 1], points[i]);
                check &= !query(root, points[i - 1].y + 1, points[i].y - 1);
            }
        }
        lower = upper - 1;
    }
    for (int i = 0; i < n; ++ i) {
        check &= degree[i] == 2;
        check &= find(0) == find(i);
    }
    printf("%d\n", check? length: 0);
    return 0;
}
