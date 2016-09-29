#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 251;
const int MOD = 998244353;

int a[N], b[N], go[N], rgo[N], count[4], ways[N];
bool visited[N];

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("E.in", "r", stdin);
#endif
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 1; i <= n; ++ i) {
            scanf("%d", a + i);
        }
        memset(go, -1, sizeof(go));
        memset(rgo, -1, sizeof(rgo));
        for (int i = 1; i <= n; ++ i) {
            scanf("%d", b + i);
            if (a[i]) {
                go[a[i]] = b[i];
            }
            if (b[i]) {
                rgo[b[i]] = a[i];
            }
        }
        memset(count, 0, sizeof(count));
        memset(visited, 0, sizeof(visited));
        for (int s = 1; s <= n; ++ s) {
            if (rgo[s] > 0) {
                continue;
            }
            int t = s;
            visited[s] = true;
            while (go[t] > 0) {
                visited[t = go[t]] = true;
            }
            count[!rgo[s] | !go[t] << 1] ++;
        }
        int cycles = 0;
        for (int s = 1; s <= n; ++ s) {
            if (visited[s]) {
                continue;
            }
            cycles ++;
            for (int v = s; !visited[v]; v = go[v]) {
                visited[v] = true;
            }
        }
        // printf("! %d %d %d %d %d\n", cycles, count[0], count[1], count[2], count[3]);
        memset(ways, 0, sizeof(ways));
        ways[0] = 1;
        int total = 0;
        for (int i = 1; i <= n; ++ i) {
            if (!a[i] && !b[i]) {
                ways[0] = 1LL * ways[0] * (++ total) % MOD;
            }
        }
        total = 0;
        for (int i = 0; i < 4; ++ i) {
            for (int j = 0; j < count[i]; ++ j) {
                for (int k = n; k >= 0; -- k) {
                    int new_ways = 0;
                    if (k && i < 3) {
                        update(new_ways, ways[k - 1]);
                    }
                    int w = 0;
                    if (i < 2) {
                        w = total;
                    } else if (i == 2) {
                        w = total - count[1];
                    } else {
                        w = std::max(count[0] - j, 0);
                    }
                    update(new_ways, 1LL * w * ways[k] % MOD);
                    ways[k] = new_ways;
                }
                total ++;
            }
        }
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", i + cycles <= n ? ways[n - i - cycles] : 0, " \n"[i == n - 1]);
        }
    }
}
