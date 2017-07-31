#include <iostream>
#include <cstring>
#include <climits>

const int N = 19;

using LL = int64_t;

int a[N], b[N], c[10], cc[10];

bool check(int i, int need, bool gt, bool lt)
{
    if (gt && lt) {
        return need <= N - i;
    }
    if (i == N || need > N - i) {
        return false;
    }
    for (int d = gt ? 0 : a[i]; d <= (lt ? 9 : b[i]); ++ d) {
        cc[d] ++;
        if (cc[d] <= c[d] && check(i + 1, need - !!d, gt || a[i] < d, lt || d < b[i])) {
            return true;
        }
        cc[d] --;
    }
    return false;
}

int search(int d, int used)
{
    if (d == 10) {
        memset(cc, 0, sizeof(cc));
        return check(0, used, false, false);
    }
    int result = 0;
    for (c[d] = 0; used + c[d] <= N - 1; ++ c[d]) {
        result += search(d + 1, used + c[d]);
    }
    return result;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("C.in", "r", stdin);
#endif
    LL l, r;
    while (std::cin >> l >> r) {
        l --, r ++;
        for (int i = N - 1; i >= 0; -- i) {
            a[i] = l % 10, b[i] = r % 10;
            l /= 10, r /= 10;
        }
        c[0] = INT_MAX;
        printf("%d\n", search(1, 0));
    }
}
