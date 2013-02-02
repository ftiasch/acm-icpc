#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <algorithm>

using std::pair;

const int N = 100000 + 1;

int treap_size, key[N], weight[N], count[N], size[N], children[N][2];

void update(int &x) {
    size[x] = size[children[x][0]] + count[x] + size[children[x][1]];
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, int k) {
    if (x) {
        if (key[x] == k) {
            count[x] ++;
        } else {
            int t = key[x] < k;
            insert(children[x][t], k);
            if (weight[children[x][t]] < weight[x]) {
                rotate(x, t);
            }
        }
    } else {
        x = treap_size ++;
        key[x] = k;
        weight[x] = rand();
        count[x] = 1;
        children[x][0] = children[x][1] = 0;
    }
    update(x);
}

void erase(int &x, int k) {
    if (key[x] == k) {
        if (count[x] > 1) {
            count[x] --;
        } else {
            if (!children[x][0] && !children[x][1]) {
                x = 0;
                return;
            }
            rotate(x, weight[children[x][0]] > weight[children[x][1]]);
            erase(x, k);
        }
    } else {
        erase(children[x][key[x] < k], k);
    }
    update(x);
}

int select(int &x, int s) {
    if (size[children[x][0]] >= s) {
        return select(children[x][0], s);
    }
    if (s <= size[children[x][0]] + count[x]) {
        return key[x];
    }
    return select(children[x][1], s - (size[children[x][0]] + count[x]));
}

#define F first
#define S second

int n, m, a[N], answer[N];
pair <pair <pair <int, int>, int>, int> queries[N];

int main() {
    treap_size = 1;
    size[0] = 0;
    weight[0] = INT_MAX;
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", &queries[i].F.F.F, &queries[i].F.F.S, &queries[i].F.S);
        queries[i].S = i;
    }
    std::sort(queries, queries + m);
    int begin = 1;
    int end = 0;
    int root = 0;
    for (int i = 0; i < m; ++ i) {
        while (end < queries[i].F.F.S) {
            insert(root, a[++ end]);
        }
        while (begin < queries[i].F.F.F) {
            erase(root, a[begin ++]);
        }
        answer[queries[i].S] = select(root, queries[i].F.S);
    }
    for (int i = 0; i < m; ++ i) {
        printf("%d\n", answer[i]);
    }
    return 0;
}
