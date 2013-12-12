#include <cstdio>
#include <cstring>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 50000;
const int M = 500;
const int MOD = 10007;

int n, k, parent[N];
std::vector <int> tree[N];

int factorial[M + 1], stirling[M + 1][M + 1], down[N][M + 1], up[N][M + 1];

int main() {
    factorial[0] = 1;
    for (int i = 1; i <= M; ++ i) {
        factorial[i] = factorial[i - 1] * i % MOD;
    }
    memset(stirling, 0, sizeof(stirling));
    stirling[0][0] = 1;
    for (int i = 1; i <= M; ++ i) {
        for (int j = 1; j <= i; ++ j) {
            stirling[i][j] = (stirling[i - 1][j] * j + stirling[i - 1][j - 1]) % MOD;
        }
    }
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        int n, k;
        scanf("%d%d", &n, &k);
        for (int i = 0; i < n; ++ i) {
            tree[i].clear();
        }
        for (int i = 0; i < n - 1; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            a --, b --;
            tree[a].push_back(b);
            tree[b].push_back(a);
        }
        std::vector <int> queue;
        queue.push_back(0);
        memset(parent, -1, sizeof(parent));
        for (int head = 0; head < (int)queue.size(); ++ head) {
            int u = queue[head];
            foreach (iter, tree[u]) {
                int v = *iter;
                if (parent[u] != v) {
                    parent[v] = u;
                    queue.push_back(v);
                }
            }
        }
        for (int i = n - 1; i >= 0; -- i) {
            int u = queue[i];
            for (int j = 0; j <= k; ++ j) {
                down[u][j] = up[u][j] = 0;
            }
            foreach (iter, tree[u]) {
                int v = *iter;
                if (parent[u] != v) {
                    (down[u][0] += down[v][0]) %= MOD;
                    for (int j = 1; j <= k; ++ j) {
                        (down[u][j] += down[v][j] + down[v][j - 1]) %= MOD;
                    }
                }
            }
            (down[u][0] += 1) %= MOD;
        }
        for (int i = 0; i < n; ++ i) {
            int u = queue[i];
            for (int j = 0; j <= k; ++ j) {
                (up[u][j] += down[u][j]) %= MOD;
            }
            foreach (iter, tree[u]) {
                int v = *iter;
                if (parent[u] != v) {
                    (up[u][0] -= down[v][0]) %= MOD;
                    for (int j = 1; j <= k; ++ j) {
                        (up[u][j] -= down[v][j] + down[v][j - 1]) %= MOD;
                    }
                    (up[v][0] += up[u][0]) %= MOD;
                    for (int j = 1; j <= k; ++ j) {
                        (up[v][j] += up[u][j] + up[u][j - 1]) %= MOD;
                    }
                    (up[u][0] += down[v][0]) %= MOD;
                    for (int j = 1; j <= k; ++ j) {
                        (up[u][j] += down[v][j] + down[v][j - 1]) %= MOD;
                    }
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            int result = 0;
            for (int j = 0; j <= k; ++ j) {
                (result += stirling[k][j] * factorial[j] % MOD * up[i][j]) %= MOD;
            }
            printf("%d\n", (result + MOD) % MOD);
        }
    }
    return 0;
}
