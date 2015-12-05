#include <algorithm>
#include <cstdio>
#include <numeric>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 100000;
const int M = 300000;

int a[M], b[M], c[M], odds, last, parent[N], parity[N];
std::vector<std::pair<int&, int>> backups;

bool by_c(int i, int j) {
    return c[i] < c[j];
}

void modify(int& ref, int v) {
    backups.push_back({ref, ref});
    ref = v;
}

int find(int u) {
    if (parent[u] != u) {
        modify(parent[u], find(parent[u]));
    }
    return parent[u];
}

bool merge(int u, int v, int c = 0) {
    u = find(u);
    v = find(v);
    if (!odds || u == v) {
        return false;
    }
    if (parity[u] && parity[v]) {
        modify(odds, odds - 2);
    }
    if (c > last) {
        modify(last, c);
    }
    modify(parent[u], v);
    if (parity[u]) {
        modify(parity[v], parity[v] ^ 1);
    }
    return true;
}

void rollback(int t) {
    while ((int)backups.size() > t) {
        backups.back().first = backups.back().second;
        backups.pop_back();
    }
}

void solve(int l, int r, const std::vector<int> &others) {
    int t0 = backups.size();
    for (int i = l + 1; i < r; ++ i) {
        merge(a[i], b[i]);
    }
    int limit = 0;
    for (int i = 0; i < (int)others.size(); ++ i) {
        if (merge(a[others[i]], b[others[i]])) {
            limit = i + 1;
        }
    }
    rollback(t0);
    for (int i = 0; i < limit; ++ i) {
        merge(a[others[i]], b[others[i]], c[others[i]]);
    }
    if (l + 1 == r) {
        printf("%d\n", odds ? -1 : last);
    } else {
        int m = l + r >> 1;
        int t1 = backups.size();
        std::vector<int> may;
        for (int i = limit; i < (int)others.size(); ++ i) {
            if (merge(a[others[i]], b[others[i]])) {
                may.push_back(others[i]);
            }
        }
        rollback(t1);
        solve(l, m, may);
        for (int i = l + 1; i <= m; ++ i) {
            may.push_back(i);
        }
        std::sort(ALL(may), by_c);
        solve(m, r, may);
    }
    rollback(t0);
}

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", a + i, b + i, c + i);
        a[i] --;
        b[i] --;
    }
    last = 0;
    odds = n;
    std::iota(parent, parent + n, 0);
    std::fill(parity, parity + n, 1);
    solve(0, m, {0});
    return 0;
}
