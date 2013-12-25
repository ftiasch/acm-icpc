#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 100000;

int value[N];

int weight[N + 1], children[N + 1][2];
int subtree_size[N + 1], subtree_min[N + 1], subtree_max[N + 1], subtree_gcd[N + 1];

int gcd(int a, int b) {
    return b ? gcd(b, a % b) : a;
}

void update(int i) {
    subtree_size[i] = 1 + subtree_size[children[i][0]] + subtree_size[children[i][1]];
    subtree_min[i] = std::min(value[i], subtree_min[children[i][0]]);
    subtree_max[i] = std::max(value[i], subtree_max[children[i][1]]);
    subtree_gcd[i] = gcd(value[i], gcd(subtree_gcd[children[i][0]], subtree_gcd[children[i][1]]));
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

bool compare(int i, int j) {
    if (value[i] != value[j]) {
        return value[i] < value[j];
    }
    return i < j;
}

void insert(int &x, int i) {
    if (x == N) {
        x = i;
        weight[x] = rand();
        children[x][0] = children[x][1] = N;
    } else {
        int t = compare(x, i);
        insert(children[x][t], i);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

void erase(int &x, int i) {
    if (x == i) {
        if (children[x][0] == N && children[x][1] == N) {
            x = N;
            return;
        }
        rotate(x, weight[children[x][0]] > weight[children[x][1]]);
        erase(x, i);
    } else if (x == N) {
        assert(false);
    } else {
        int t = compare(x, i);
        erase(children[x][t], i);
    }
    update(x);
}

int set[N], root[N];

void merge(int &v, int u) {
    if (v != N) {
        merge(children[v][0], u);
        merge(children[v][1], u);
        set[v] = set[u];
        insert(root[set[v]], v);
        v = N;
    }
}

int query(int x, int l, int r) {
    if (r < subtree_min[x] || subtree_max[x] < l) {
        return 0;
    }
    if (l <= subtree_min[x] && subtree_max[x] <= r) {
        return subtree_gcd[x];
    }
    int result = gcd(query(children[x][0], l, r), query(children[x][1], l, r));
    if (l <= value[x] && value[x] <= r) {
        result = gcd(result, value[x]);
    }
    return result;
}

int select(int x, int rank) {
    if (subtree_size[children[x][0]] >= rank) {
        return select(children[x][0], rank);
    }
    if (subtree_size[children[x][0]] + 1 == rank) {
        return value[x];
    }
    return select(children[x][1], rank - 1 - subtree_size[children[x][0]]);
}

int lower_bound(int x, int l) {
    if (x == N) {
        return INT_MAX;
    }
    if (value[x] < l) {
        return lower_bound(children[x][1], l);
    }
    return std::min(value[x], lower_bound(children[x][0], l));
}

int main() {
    weight[N] = INT_MAX;
    subtree_size[N] = 0;
    subtree_min[N] = INT_MAX;
    subtree_max[N] = INT_MIN;
    subtree_gcd[N] = 0;
    int test_count;
    scanf("%d", &test_count);
    for (int t = 1; t <= test_count; ++ t) {
        int n, q;
        scanf("%d%d", &n, &q);
        for (int i = 0; i < n; ++ i) {
            scanf("%d", value + i);
        }
        printf("Case #%d:\n", t);
        for (int i = 0; i < n; ++ i) {
            set[i] = i;
            root[i] = N;
            insert(root[i], i);
        }
        while (q --) {
            int type, u;
            scanf("%d%d", &type, &u);
            u --;
            if (type == 1) {
                int v;
                scanf("%d", &v);
                v --;
                if (set[u] != set[v]) {
                    if (subtree_size[root[set[u]]] < subtree_size[root[set[v]]]) {
                        std::swap(u, v);
                    }
                    merge(root[set[v]], u);
                }
            } else if (type == 2) {
                int v;
                scanf("%d", &v);
                v --;
                if (set[u] != set[v]) {
                    erase(root[set[u]], u);
                    set[u] = set[v];
                    insert(root[set[u]], u);
                }
            } else if (type == 3) {
                int x;
                scanf("%d", &x);
                erase(root[set[u]], u);
                value[u] = x;
                insert(root[set[u]], u);
            } else if (type == 4) {
                int r = root[set[u]];
                if (subtree_size[r] <= 2) {
                    printf("%d\n", subtree_size[r]);
                } else {
                    int a = select(r, 1);
                    int b = select(r, 2);
                    int result = 2;
                    while (true) {
                        int c = lower_bound(r, a + b);
                        if (c == INT_MAX) {
                            break;
                        }
                        a = b;
                        b = c;
                        result ++;
                    }
                    printf("%d\n", result);
                }
            } else if (type == 5) {
                int l, r;
                scanf("%d%d", &l, &r);
                int result = query(root[set[u]], l, r);
                printf("%d\n", result ? result : -1);
            } else {
                assert(false);
            }
        }
    }
    return 0;
}
