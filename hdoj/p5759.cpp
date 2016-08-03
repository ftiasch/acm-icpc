#include <cstdio>
#include <cstring>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 300001;

int n, q, dfs_count, parent[N], prefer[N], chain[N], head[N], position[N];
unsigned weight[N], count[N], delta[N], multiplier[N][3], size[N], son[N][3], result[N];
std::vector<int> tree[N];

unsigned prepare(int u)
{
    position[u] = dfs_count ++;
    unsigned sum = weight[u];
    size[u] = 1U;
    result[u] = weight[u];
    std::pair<unsigned, int> best(0, u);
    chain[u] = u;
    foreach (iterator, tree[u]) {
        int v = *iterator;
        int s = prepare(v);
        best = std::max(best, std::make_pair(size[v], v));
        result[u] -= s * size[v];
        size[u] += size[v];
        sum += s;
    }
    head[chain[u] = chain[prefer[u] = best.second]] = u;
    result[u] += sum * size[u];
    return sum;
}

unsigned query(int k) {
    unsigned result = 0;
    for (; k < n; k += ~k & k + 1) {
        result += count[k];
    }
    return result;
}

int main()
{
    while (scanf("%d%d", &n, &q) == 2) {
        for (int i = 0; i < n; ++ i) {
            tree[i].clear();
            memset(son[i], 0, sizeof(son[i]));
        }
        parent[0] = -1;
        for (int i = 1; i < n; ++ i) {
            scanf("%d", parent + i);
            tree[-- parent[i]].push_back(i);
        }
        for (int i = 0, w; i < n; ++ i) {
            scanf("%d", &w);
            weight[i] = static_cast<unsigned>(w);
        }
        dfs_count = 0;
        prepare(0);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0, u = i; j < 3 && ~u; ++ j, u = parent[u]) {
                son[u][j] ++;
            }
        }
        for (int u = 0; u < n; ++ u) {
            son[u][1] += son[u][0];
            son[u][2] += son[u][1];
        }
        for (int u = 0; u < n; ++ u) {
            for (int j = 0; j < 2; ++ j) {
                multiplier[u][j] = 1U + son[u][2 - j] * size[u];
                foreach (iterator, tree[u]) {
                    multiplier[u][j] -= son[*iterator][1 - j] * size[*iterator];
                }
            }
            multiplier[u][2] = 1U + size[u];
        }
        memset(delta, 0, sizeof(*delta) * n);
        memset(count, 0, sizeof(*count) * n);
        for (int op, u, x; q --; ) {
            scanf("%d%d", &op, &u);
            u --;
            if (op == 1) {
                scanf("%d", &x);
                delta[u] += x;
                unsigned d = son[u][2] * x;
                for (int p = head[chain[u]]; p; p = head[chain[parent[p]]]) {
                    result[parent[p]] += (size[parent[p]] - size[p]) * d;
                }
                for (int k = position[u]; k >= 0; k -= ~k & k + 1) {
                    count[k] += d;
                }
            } else {
                unsigned r = result[u];
                for (int i = 0, p = u; i < 3 && ~p; ++ i, p = parent[p]) {
                    r += multiplier[u][i] * delta[p];
                }
                if (prefer[u] != u) {
                    r += (size[u] - size[prefer[u]]) * (query(position[prefer[u]]) - query(position[prefer[u]] + size[prefer[u]]));
                }
                printf("%u\n", r);
            }
        }
    }
}
