#include <algorithm>
#include <cstdio>
#include <cstring>
#include <deque>
#include <vector>

int main()
{
    int n, q;
    scanf("%d%d", &n, &q);
    std::deque <int> prefix, suffix;
    for (int i = 0; i <= n; ++ i) {
        prefix.push_back(i);
        suffix.push_back(i);
    }
    while (q --) {
        int t;
        scanf("%d", &t);
        if (t == 1) {
            int p;
            scanf("%d", &p);
            if (p + p > n) {
                p = n - p;
                std::swap(prefix, suffix);
            }
            n -= p;
            std::vector <int> a;
            for (int i = 1; i <= p; ++ i) {
                a.push_back(prefix[1] - prefix[0]);
                prefix.pop_front();
                suffix.pop_back();
            }
            for (int i = 1, delta = 0; i <= p; ++ i) {
                delta += a[i - 1];
                prefix[p - i] -= delta;
                suffix[n - p + i] += delta;
            }
        } else if (t == 2) {
            int l, r;
            scanf("%d%d", &l, &r);
            printf("%d\n", prefix[r] - prefix[l]);
        }
    }
    return 0;
}
