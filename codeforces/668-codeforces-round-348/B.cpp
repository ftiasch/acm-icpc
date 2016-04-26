#include <cstdio>
#include <vector>

int main()
{
    int n, q;
    scanf("%d%d", &n, &q);
    auto update = [&](int& p, int x) {
        (p += x + n) %= n;
    };
    auto swap = [&](int& p) {
        update(p, p & 1 ? -1 : 1);
    };
    int i = 0, j = 1;
    while (q --) {
        int t;
        scanf("%d", &t);
        if (t == 1) {
            int x;
            scanf("%d", &x);
            update(i, x);
            update(j, x);
        } else {
            swap(i);
            swap(j);
        }
    }
    std::vector<int> result(n);
    for (int k = 0; k < n; k += 2) {
        result[(i + k) % n] = k;
        result[(j + k) % n] = k + 1;
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", result[i] + 1, " \n"[i == n - 1]);
    }
}
