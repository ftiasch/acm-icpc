#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

typedef long long Long;

const int N = 200000;

int n;
Long x[N], position[N];

int get_next(int d, int a, Long l)
{
    if (d == +1) {
        return std::upper_bound(x, x + n, x[a] + l) - x - 1;
    } else {
        return std::lower_bound(x, x + a, x[a] - l) - x;
    }
}

int solve(int d, int a, Long l)
{
    int b = get_next(d, a, l);
    Long l0 = std::abs(x[a] - x[b]);
    int c = get_next(-d, b, l - l0);
    if (a == b && b == c) {
        return a;
    }
    if (l0 != 0 && a == c) {
        return solve(d, a, l % (2 * l0));
    }
    return solve(-d, b, l - l0);
}

int main()
{
    int m;
    scanf("%d%d", &n, &m);
    std::vector <std::pair <int, int>> order;
    for (int i = 0; i < n; ++ i) {
        int x;
        scanf("%d", &x);
        order.push_back({x, i});
    }
    std::sort(ALL(order));
    for (int i = 0; i < n; ++ i) {
        x[i] = order[i].first;
        position[order[i].second] = i;
    }
    while (m --) {
        int a, l;
        scanf("%d%d", &a, &l);
        printf("%d\n", order[solve(+1, position[a - 1], l)].second + 1);
    }
    return 0;
}
