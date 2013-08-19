#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 150000;

int m, n, h, a[N], b[N], delta[N + 1 << 1], minimum[N + 1 << 1];

int getID(int l, int r) {
    return l + r | l != r;
}

void insert(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    int id = getID(l, r);
    if (a <= l && r <= b) {
        delta[id] += c;
        minimum[id] += c;
    } else {
        int m = l + r >> 1;
        insert(l, m, a, b, c);
        insert(m + 1, r, a, b, c);
        minimum[id] = std::min(minimum[getID(l, m)], minimum[getID(m + 1, r)]) + delta[id];
    }
}

int main() {
    scanf("%d%d%d", &n, &m, &h);
    for (int i = 0; i < m; ++ i) {
        scanf("%d", b + i);
    }
    std::sort(b, b + m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        a[i] = std::lower_bound(b, b + m, h - a[i]) - b;
    }
    memset(delta, 0, sizeof(delta));
    memset(minimum, 0, sizeof(minimum));
    for (int i = 0; i < m; ++ i) {
        insert(0, m, 0, i, 1);
    }
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        insert(0, m, 0, a[i], -1);
        if (i >= m) {
            insert(0, m, 0, a[i - m], 1);
        }
        if (i >= m - 1) {
            answer += minimum[getID(0, m)] >= 0;
        }
    }
    printf("%d\n", answer);
    return 0;
}
