#include <algorithm>
#include <cstdio>

const int N = 100000;

int n, m, a[N], dp[N];

bool check(int limit)
{
    for (int start = 0; start < 2 && start < n; ++ start) {
        dp[start] = start ? std::max(a[0] + limit, a[1]) : a[0];
        for (int i = start + 1; i < n; ++ i) {
            dp[i] = dp[i - 1];
            if (dp[i - 1] >= a[i] - limit - 1) {
                dp[i] = std::max(dp[i], a[i]);
            }
            if (dp[i - 1] >= a[i] - 1) {
                dp[i] = std::max(dp[i], a[i] + limit);
            }
            if (i >= 2 && dp[i - 2] >= a[i] - limit - 1) {
                dp[i] = std::max(dp[i], a[i - 1] + limit);
            }
        }
        if (dp[n - 1] >= std::min(m - 1, m + a[start] - limit - 1)) {
            return true;
        }
    }
    return false;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("E.in", "r", stdin);
#endif
    scanf("%d%d", &m, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    std::sort(a, a + n);
    std::pair<int, int> best(a[0] + m - a[n - 1], 0);
    for (int i = 1; i < n; ++ i) {
        best = std::max(best, std::make_pair(a[i] - a[i - 1], i));
    }
    std::rotate(a, a + best.second, a + n);
    for (int i = n - 1; i >= 0; -- i) {
        a[i] -= a[0];
        if (a[i] < 0) {
            a[i] += m;
        }
    }
    int low = 0;
    int high = a[0] + m - a[n - 1] - 1;
    while (low < high) {
        int middle = low + high >> 1;
        if (check(middle)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    printf("%d\n", high);
}
