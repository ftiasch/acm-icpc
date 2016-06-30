#include <algorithm>
#include <cstdlib>
#include <cctype>
#include <cstdio>
#include <cstring>
#include <numeric>

const int N = 1000;
const int M = N * (N - 1) / 2;

int n, m, q, a[M], b[M], c[M], aa[M], bb[M], cc[M], order[M], parent[N << 1];

int find(int u)
{
    int r = u;
    while (parent[r] != r) {
        r = parent[r];
    }
    while (u != r) {
        int p = parent[u];
        parent[u] = r;
        u = p;
    }
    return r;
}

bool add(int a, int b)
{
    if (find(a << 1) == find(b << 1)) {
        return false;
    }
    parent[find(a << 1)] = find(b << 1 | 1);
    parent[find(b << 1)] = find(a << 1 | 1);
    return true;
}

bool by_c(int i, int j)
{
    return c[i] > c[j];
}

int main()
{
    scanf("%d%d%d", &n, &m, &q);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", a + i, b + i, c + i);
        a[i] --;
        b[i] --;
        order[i] = i;
    }
    std::sort(order, order + m, by_c);
    for (int i = 0; i < m; ++ i) {
        aa[i] = a[order[i]];
        bb[i] = b[order[i]];
        cc[i] = c[order[i]];
    }
    for (int i = 0; i < q; ++ i) {
        int l, r;
        scanf("%d%d", &l, &r);
        l --;
        int result = -1;
        std::iota(parent, parent + (n << 1), 0);
        for (int j = 0; j < m && !~result; ++ j) {
            auto&& index = order[j];
            if (l <= index && index < r && !add(aa[j], bb[j])) {
                result = cc[j];
            }
        }
        printf("%d\n", result);
    }
}
