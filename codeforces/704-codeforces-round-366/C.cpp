#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <vector>

const int N = 200000;
const int MOD = (int)1e9 + 7;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int head[N], to[N], next[N], degree[N];
bool visited[N], used[N];

void solve(int n, int* ways, int s)
{
    std::vector<int> edges;
    int v = s;
    while (true) {
        visited[v] = true;
        int iterator = head[v];
        while (~iterator && used[iterator >> 1]) {
            iterator = next[iterator];
        }
        if (!~iterator) {
            break;
        }
        used[iterator >> 1] = true;
        edges.push_back(iterator);
        v = to[iterator] >> 1;
    }
    assert(!edges.empty());
    int new_ways[2];
    memset(new_ways, 0, sizeof(new_ways));
    for (int first = 0; first <= (s < n); ++ first) {
        int dp[2][2][2];
        memset(dp, 0, sizeof(dp));
        dp[0][first][0] = 1;
        int m = edges.size();
        for (int i = 0; i < m; ++ i) {
            memset(dp[i + 1 & 1], 0, sizeof(dp[i + 1 & 1]));
            int e = edges[i];
            for (int x = 0; x < 2; ++ x) {
                for (int s = 0; s < 2; ++ s) {
                    for (int y = 0; y <= ((to[e] >> 1) < n); ++ y) {
                        int v = (x ^ (to[e ^ 1] & 1)) || (y ^ (to[e] & 1));
                        update(dp[i + 1 & 1][y][s ^ v], dp[i & 1][x][s]);
                    }
                }
            }
        }
        for (int last = 0; last < 2; ++ last) {
            if (s == v && first != last) {
                continue;
            }
            for (int s = 0; s < 2; ++ s) {
                for (int ps = 0; ps < 2; ++ ps) {
                    update(new_ways[s ^ ps], static_cast<long long>(ways[ps]) * dp[m & 1][last][s] % MOD);
                }
            }
        }
    }
    memcpy(ways, new_ways, sizeof(new_ways));
}

int main()
{
    int n, m;
    scanf("%d%d", &m, &n);
    int v = n;
    for (int i = 0; i < m; ++ i) {
        int k;
        scanf("%d", &k);
        for (int j = 0; j < 2; ++ j) {
            int a;
            if (j < k) {
                scanf("%d", &a);
            } else {
                a = ++ v;
            }
            to[i << 1 | j] = std::abs(a) - 1 << 1 | (a < 0);
        }
    }
    memset(head, -1, sizeof(*head) * v);
    for (int i = 0; i < m << 1; ++ i) {
        int u = to[i ^ 1] >> 1;
        next[i] = head[u];
        head[u] = i;
        degree[u] ++;
    }
    int ways[2];
    ways[0] = 1;
    ways[1] = 0;
    for (int i = 0; i < v; ++ i) {
        if (!degree[i]) {
            visited[i] = true;
            update(ways[0], ways[0]);
        }
    }
    for (int i = 0; i < v; ++ i) {
        if (degree[i] < 2 && !visited[i]) {
            solve(n, ways, i);
        }
    }
    for (int i = 0; i < v; ++ i) {
        if (!visited[i]) {
            solve(n, ways, i);
        }
    }
    printf("%d\n", ways[1]);
}
