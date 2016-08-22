#include <algorithm>
#include <iostream>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>

using Long = long long;

const int N = 5000;
const Long INF = (Long)1e18;

int x[N], a[N], b[N], c[N], d[N];
Long dp[2][N + 1];

void update(Long& x, Long a)
{
    x = std::min(x, a);
}

int main()
{
    int n, s, e;
    scanf("%d%d%d", &n, &s, &e);
    s --;
    e --;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", x + i);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        a[i] += x[i];
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", b + i);
        b[i] -= x[i];
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", c + i);
        c[i] += x[i];
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", d + i);
        d[i] -= x[i];
    }
    // -> a[i]
    //    b[i] <-
    // <- c[i]
    //    d[i] ->
    dp[1][0] = 0;
    for (int i = 0; i < n; ++ i) {
        int m = std::min(i, n - i + 1);
        assert(m + 1 <= N);
        std::fill(dp[i & 1], dp[i & 1] + (m + 2), INF);
        bool has_s = s < i;
        bool has_e = e < i;
        if (has_s && has_e) {
            dp[i + 1 & 1][1] = INF;
        }
        for (int j = 0; j <= m; ++ j) {
            auto&& value = dp[i + 1 & 1][j];
            if (i == s) {
                if (j > 0) {
                    update(dp[i & 1][j], value + c[i]);
                }
                update(dp[i & 1][j + 1], value + d[i]);
            } else if (i == e) {
                if (j > 0) {
                    update(dp[i & 1][j], value + a[i]);
                }
                update(dp[i & 1][j + 1], value + b[i]);
            } else {
                if (j > 1) {
                    update(dp[i & 1][j - 1], value + a[i] + c[i]);
                }
                if (j > 1 || j == 1 && !has_s) {
                    update(dp[i & 1][j], value + b[i] + c[i]);
                }
                if (j > 1 || j == 1 && !has_e) {
                    update(dp[i & 1][j], value + a[i] + d[i]);
                }
                update(dp[i & 1][j + 1], value + b[i] + d[i]);
            }
        }
    }
    std::cout << dp[n + 1 & 1][1] << std::endl;
}
