// ZOJ 3686 -- A Simple Tree Problem
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

int n, m;
std::vector <int> children[N];

int start[N], size[N], node_count;

void prepare(int u) {
    start[u] = node_count ++;
    size[u] = 1;
    foreach (iter, children[u]) {
        int v = *iter;
        prepare(v);
        size[u] += size[v];
    }
}

int get_id(int l, int r) {
    return l + r | l != r;
}

int delta[N << 1], count[N << 1];

void cover(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return;
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        delta[id] ^= 1;
        count[id] = r - l + 1 - count[id];
    } else {
        int m = l + r >> 1;
        cover(l, m, a, b);
        cover(m + 1, r, a, b);
        count[id] = count[get_id(l, m)] + count[get_id(m + 1, r)];
        if (delta[id]) {
            count[id] = r - l + 1 - count[id];
        }
    }
}

int query(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return 0;
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        return count[id];
    }
    int m = l + r >> 1;
    int ret = query(l, m, a, b) + query(m + 1, r, a, b);
    if (delta[id]) {
        ret = std::min(r, b) - std::max(l, a) + 1 - ret;
    }
    return ret;
}

int main() {
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            children[i].clear();
        }
        for (int i = 1; i < n; ++ i) {
            int parent;
            scanf("%d", &parent);
            children[-- parent].push_back(i);
        }
        node_count = 0;
        prepare(0);
        memset(delta, 0, sizeof(delta));
        memset(count, 0, sizeof(count));
        while (m --) {
            char type[2];
            int u;
            scanf("%s%d", type, &u);
            u --;
            if (*type == 'o') {
                cover(0, n - 1, start[u], start[u] + size[u] - 1);
            } else {
                printf("%d\n", query(0, n - 1, start[u], start[u] + size[u] - 1));
            }
        }
        puts("");
    }
    return 0;
}
