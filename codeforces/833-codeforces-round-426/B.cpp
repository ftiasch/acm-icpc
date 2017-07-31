#include <cstdio>
#include <cstring>
#include <map>

#define K first
#define V second

const int N = 35001;

int last[N], pre[N], dp[N];

int main()
{
    int n, m;
    while (scanf("%d%d", &n, &m) == 2) {
        memset(last, 0, sizeof(last));
        for (int i = 1, a; i <= n; ++ i) {
            scanf("%d", &a);
            pre[i] = last[a];
            last[a] = i;
        }
        dp[0] = 0;
        for (int i = 1; i <= n; ++ i) {
            dp[i] = dp[i - 1] + !pre[i];
        }
        for (int k = 2; k <= m; ++ k) {
            std::map<int, int> c;
            c[0] = n + 1;
            int last_dp = dp[k - 1];
            for (int i = k; i <= n; ++ i) {
                int now = 0;
                while (now + c.rbegin()->V <= last_dp) {
                    now += c.rbegin()->V;
                    c.erase(c.rbegin()->K);
                }
                c.rbegin()->V += now - last_dp;
                c[i] = last_dp + 1;
                auto it = c.upper_bound(pre[i]);
                it --;
                it->V --;
                if (it->V == 0) {
                    c.erase(it->K);
                }
                last_dp = dp[i];
                dp[i] = (n + 1) - c.begin()->V;
            }
        }
        printf("%d\n", dp[n]);
    }
}
