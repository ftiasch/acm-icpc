#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>

const int N = 100000;
const int MOD = (int)1e9 + 7;

struct Edge { int v, x, c; };
struct Sum { int c, p; };

Sum& operator += (Sum& a, const Sum& b)
{
    a.c += b.c;
    a.p = (long long)a.p * b.p % MOD;
}

int n, m, result, size[N], imbalance[N], w[2];
bool resolved[N];
Sum sum[N << 2];
std::vector<int> vertices;
std::vector<std::pair<int, int>> todos;
std::vector<Edge> tree[N];

int pow(int a, int n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % MOD;
        }
        a = (long long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

int prepare(int p, int u)
{
    int size = 1;
    for (auto&& iterator : tree[u]) {
        auto v = iterator.v;
        if (v != p) {
            int s = prepare(u, v);
            result = (long long)result * pow(iterator.x, (long long)s * (n - s) % (MOD - 1)) % MOD;
            size += s;
        }
    }
    return size;
}

int prepare2(int p, int u)
{
    vertices.push_back(u);
    size[u] = 1, imbalance[u] = 0;
    for (auto&& iterator : tree[u]) {
        auto&& v = iterator.v;
        if (v != p && !resolved[v]) {
            prepare2(u, v);
            size[u] += size[v];
            imbalance[u] = std::max(imbalance[u], size[v]);
        }
    }
}

void add(int k, const Sum& v)
{
    for (; k < m << 2; k += ~k & k + 1) {
        sum[k] += v;
    }
}

void dfs(int p, int u, int offset, int product)
{
    todos.emplace_back(offset, product);
    Sum s {0, 1};
    for (int k = offset - 1; k >= 0; k -= ~k & k + 1) {
        s += sum[k];
    }
    result = (long long)result * pow((long long)pow(product, s.c) * s.p % MOD, MOD - 2) % MOD;
    for (auto&& iterator : tree[u]) {
        auto&& v = iterator.v;
        if (v != p && !resolved[v]) {
            dfs(u, v, offset + w[iterator.c], (long long)product * iterator.x % MOD);
        }
    }
}

void divide(int root)
{
    vertices.clear();
    prepare2(-1, root);
    m = size[root];
    for (auto&& u : vertices) {
        imbalance[u] = std::max(imbalance[u], m - size[u]);
    }
    for (auto&& u : vertices) {
        if (imbalance[u] < imbalance[root]) {
            root = u;
        }
    }
    for (int t = 0; t < 2; ++ t) {
        w[t] = 1, w[t ^ 1] = -2;
        for (int i = 0; i < m << 2; ++ i) {
            sum[i] = {0, 1};
        }
        add(m << 1, {1, 1});
        for (auto&& iterator : tree[root]) {
            auto&& v = iterator.v;
            if (!resolved[v]) {
                dfs(root, v, (m << 1) + w[iterator.c], iterator.x);
                for (auto&& todo : todos) {
                    add((m << 2) - todo.first, {1, todo.second});
                }
                todos.clear();
            }
        }
    }
    resolved[root] = true;
    for (auto&& iterator : tree[root]) {
        auto&& v = iterator.v;
        if (!resolved[v]) {
            divide(v);
        }
    }
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("D.in", "r", stdin);
#endif
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            tree[i].clear();
        }
        for (int i = 0, a, b, x, c; i < n - 1; ++ i) {
            scanf("%d%d%d%d", &a, &b, &x, &c);
            a --;
            b --;
            tree[a].push_back({b, x, c});
            tree[b].push_back({a, x, c});
        }
        result = 1;
        prepare(-1, 0);
        memset(resolved, 0, sizeof(*resolved) * n);
        divide(0);
        printf("%d\n", result);
    }
}
