#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 300000;
const int MOD = (int)1e9 + 7;

int n, size[N], position[N], depth[N];
std::vector <int> children[N];

void dfs(int u, int d, int &&count)
{
    position[u] = count ++;
    depth[u] = d;
    size[u] = 1;
    for (auto &v : children[u]) {
        dfs(v, d + 1, std::move(count));
        size[u] += size[v];
    }
}

struct BIT {
    int count[N];

    BIT() {
        memset(count, 0, sizeof(count));
    }

    void __add(int k, int v) {
        for (; k < n; k += ~k & k + 1) {
            (count[k] += v) %= MOD;
        }
    }

    void add(int a, int b, int v) {
        __add(a, v);
        __add(b, (MOD - v) % MOD);
    }

    int ask(int k) {
        int result = 0;
        for (; k >= 0; k -= ~k & k + 1) {
            (result += count[k]) %= MOD;
        }
        return result;
    }
};

BIT bit[2];

int main()
{
    scanf("%d", &n);
    for (int i = 1; i < n; ++ i) {
        static int p;
        scanf("%d", &p);
        children[-- p].push_back(i);
    }
    dfs(0, 0, 0);
    int q;
    scanf("%d", &q);
    while (q --) {
        static int t, v;
        scanf("%d%d", &t, &v);
        v --;
        if (t == 1) {
            static int x, k;
            scanf("%d%d", &x, &k);
            bit[0].add(position[v], position[v] + size[v], ((long long)k * depth[v] % MOD + x) % MOD);
            bit[1].add(position[v], position[v] + size[v], (MOD - k) % MOD);
        } else {
            int result = (bit[0].ask(position[v]) + (long long)bit[1].ask(position[v]) * depth[v] % MOD) % MOD;
            printf("%d\n", result);
        }
    }
}
