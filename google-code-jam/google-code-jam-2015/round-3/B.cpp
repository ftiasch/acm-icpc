#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>

int div(int x, int y)
{
    int q = x / y;
    while (q * y <= x) {
        q ++;
    }
    while (q * y > x) {
        q --;
    }
    return q;
}

int main()
{
    int T;
    scanf("%d", &T);
    for (int t = 1; t <= T; ++ t) {
        int n, m;
        scanf("%d%d", &n, &m);
        std::vector <int> s(n - m + 1);
        for (int i = 0; i <= n - m; ++ i) {
            scanf("%d", &s[i]);
        }
        std::vector <int> up(m), down(m);
        for (int i = 0; i < m; ++ i) {
            int offset = 0;
            for (int j = i; j < n - m; j += m) {
                offset += s[j + 1] - s[j];
                up[i]   = std::max(up[i],   offset);
                down[i] = std::min(down[i], offset);
            }
        }
        int result = INT_MAX;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int gap  = INT_MIN;
                int dsum = 0;
                int usum = 0;
                for (int k = 0; k < m; ++ k) {
                    gap = std::max(gap, down[i] - down[k] + up[k] - up[j]);
                    dsum += down[i] - down[k];
                    usum += up[j]   - up[k];
                }
                int x = div(s[0] - dsum, m);
                int y = div(s[0] - usum + m - 1, m);
                result = std::min(result, std::max(y - x, gap) + up[j] - down[i]);
            }
        }
        printf("Case #%d: %d\n", t, result);
    }
    return 0;
}
