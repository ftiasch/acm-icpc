// POI X Stage II Motorways(aut)
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 20000;
const int T = N + N + 1;
const int INF = 1000000000;

struct Interval {
    int key, value, id;

    Interval(int key = 0, int value = 0, int id = 0): key(key), value(value), id(id) {}

    int compareTo(const Interval &o) const {
        if (key == o.key) {
            return id - o.id;
        }
        return key - o.key;
    }

    Interval max(const Interval &o) const {
        return (value > o.value || (value == o.value && id > o.id))? *this: o;
    }
};

static const Interval INTERVAL_MIN(0, -INF, -1);

int n, m, a[N], b[N], treapCount, weight[T], children[T][2], low[T], high[T], color[N], queue[N];
Interval key[T], maximum[T];

void update(int &x) {
    low[x] = children[x][0]? low[children[x][0]]: key[x].key;
    high[x] = children[x][1]? high[children[x][1]]: key[x].key;
    maximum[x] = key[x]
        .max(maximum[children[x][0]])
        .max(maximum[children[x][1]]);
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, const Interval &k) {
    if (x == 0) {
        x = treapCount ++;
        key[x] = k;
        weight[x] = rand();
        children[x][0] = children[x][1] = 0;
    } else {
        int t = key[x].compareTo(k) < 0;
        insert(children[x][t], k);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

void erase(int &x, const Interval &k) {
    if (key[x].compareTo(k) != 0) {
        erase(children[x][key[x].compareTo(k) < 0], k);
    } else if ((children[x][0] | children[x][1]) == 0) {
        x = 0;
        return;
    } else {
        int t = weight[children[x][0]] > weight[children[x][1]];
        rotate(x, t);
        erase(x, k);
    }
    update(x);
}

Interval query(int &x, int l, int r) {
    if (r < low[x] || high[x] < l) {
        return INTERVAL_MIN;
    }
    if (l <= low[x] && high[x] <= r) {
        return maximum[x];
    }
    Interval result = INTERVAL_MIN;
    if (l <= key[x].key && key[x].key <= r) {
        result = result.max(key[x]);
    }
    result = result.max(query(children[x][0], l, r));
    result = result.max(query(children[x][1], l, r));
    return result;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
    }
    weight[0] = INT_MAX;
    maximum[0] = INTERVAL_MIN;
    memset(color, -1, sizeof(color));
    treapCount = 1;
    int maxTree = 0;
    int minTree = 0;
    for (int i = 0; i < m; ++ i) {
        insert(maxTree, Interval(a[i], b[i], i));
        insert(minTree, Interval(b[i], -a[i], i));
    }
    for (int i = 0; i < m; ++ i) {
        if (color[i] == -1) {
            color[i] = 0;
            int tail = 0;
            queue[tail ++] = i;
            int head = 0;
            while (head != tail) {
                int i = queue[head ++];
                bool found;
                do {
                    found = false;
                    int j = query(maxTree, a[i] + 1, b[i] - 1).id;
                    if (j != -1 && b[j] > b[i]) {
                        found = true;
                        erase(maxTree, Interval(a[j], b[j], j));
                        if (color[j] == -1) {
                            color[j] = color[i] ^ 1;
                            queue[tail ++] = j;
                        }
                    }
                } while (found);
                do {
                    found = false;
                    int j = query(minTree, a[i] + 1, b[i] - 1).id;
                    if (j != -1 && a[j] < a[i]) {
                        found = true;
                        erase(minTree, Interval(b[j], -a[j], j));
                        if (color[j] == -1) {
                            color[j] = color[i] ^ 1;
                            queue[tail ++] = j;
                        }
                    }
                } while (found);
            }
        }
    }
    bool valid = true;
    for (int t = 0; t < 2; ++ t) {
        treapCount = 1;
        int root = 0;
        for (int i = 0; i < m; ++ i) {
            if (color[i] == t) {
                insert(root, Interval(a[i], b[i], i));
            }
        }
        for (int i = 0; i < m; ++ i) {
            if (color[i] == t) {
                int j = query(root, a[i] + 1, b[i] - 1).id;
                valid &= j == -1 || b[j] <= b[i];
            }
        }
    }
    if (valid) {
        for (int i = 0; i < m; ++ i) {
            puts(color[i]? "S": "N");
        }
    } else {
        puts("NIE");
    }
    return 0;
}
