#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

int K;
const int N = 150000;

struct Summary
{
    Summary()
    {
        memset(count, 0, sizeof(count));
        memset(value, 0, sizeof(value));
    }

    int count[5], value[5];
};

Summary merge(Summary a, const Summary& b)
{
    for (int j = 0; j < K; ++ j) {
        if (b.count[j]) {
            int i = 0, i_min = 0;
            while (i < K && a.value[i] != b.value[j]) {
                if (a.count[i] < a.count[i_min]) {
                    i_min = i;
                }
                i ++;
            }
            if (i < K) {
                a.count[i] += b.count[j];
            } else if (a.count[i_min] >= b.count[j]) {
                for (int i = 0; i < K; ++ i) {
                    a.count[i] -= b.count[j];
                }
            } else {
                int t = a.count[i_min];
                for (int i = 0; i < K; ++ i) {
                    a.count[i] -= t;
                }
                a.count[i_min] = b.count[j] - t;
                a.value[i_min] = b.value[j];
            }
        }
    }
    return a;
}

int get_id(int l, int r)
{
    return l + r | l != r;
}

int a[N], cover[N << 1];
Summary summary[N << 1];

void build(int l, int r)
{
    int u = get_id(l, r);
    if (l < r) {
        int m = l + r >> 1;
        build(l, m);
        build(m + 1, r);
        summary[u] = merge(summary[get_id(l, m)], summary[get_id(m + 1, r)]);
    } else {
        summary[u].count[0] = 1;
        summary[u].value[0] = a[l];
    }
}

void set(int l, int r, int c)
{
    int u = get_id(l, r);
    cover[u] = c;
    memset(&summary[u], 0, sizeof(summary[u]));
    summary[u].count[0] = r - l + 1;
    summary[u].value[0] = c;
}

void insert(int l, int r, int a, int b, int c)
{
    if (b < l || r < a) {
        return;
    }
    int u = get_id(l, r);
    if (a <= l && r <= b) {
        set(l, r, c);
    } else {
        int m = l + r >> 1;
        if (cover[u]) {
            set(l, m, cover[u]);
            set(m + 1, r, cover[u]);
            cover[u] = 0;
        }
        insert(l, m, a, b, c);
        insert(m + 1, r, a, b, c);
        summary[u] = merge(summary[get_id(l, m)], summary[get_id(m + 1, r)]);
    }
}

Summary query(int l, int r, int a, int b)
{
    if (b < l || r < a) {
        return Summary();
    }
    int u = get_id(l, r);
    if (a <= l && r <= b) {
        return summary[u];
    } else {
        int m = l + r >> 1;
        if (cover[u]) {
            Summary result;
            result.count[0] = std::min(r, b) - std::max(l, a) + 1;
            result.value[0] = cover[u];
            return result;
        }
        if (b <= m) {
            return query(l, m, a, b);
        }
        if (m < a) {
            return query(m + 1, r, a, b);
        }
        return merge(query(l, m, a, b), query(m + 1, r, a, b));
    }
}

int main()
{
    int n, m, p;
    scanf("%d%d%d", &n, &m, &p);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    K = 100 / p;
    memset(cover, 0, sizeof(cover));
    build(0, n - 1);
    while (m --) {
        int t, l, r;
        scanf("%d%d%d", &t, &l, &r);
        l --;
        r --;
        if (t == 1) {
            int id;
            scanf("%d", &id);
            insert(0, n - 1, l, r, id);
        } else {
            Summary result = query(0, n - 1, l, r);
            printf("%d", K);
            for (int i = 0; i < K; ++ i) {
                printf(" %d", result.value[i]);
            }
            puts("");
        }
    }
}
