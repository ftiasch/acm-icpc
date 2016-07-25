#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>

const int N = 1000;
const int M = 30000;

int n, m, s, t, x[M + M], w[M], depth[N], low[N], head[N], next[M + M];
bool used[M];
std::vector<int> candidates;

bool dfs(int u)
{
    low[u] = depth[u];
    bool has = u == t;
    for (auto iterator = head[u]; ~iterator; iterator = next[iterator]) {
        if (!used[iterator >> 1]) {
            used[iterator >> 1] = true;
            int v = x[iterator];
            if (~depth[v]) {
                low[u] = std::min(low[u], depth[v]);
            } else {
                depth[v] = depth[u] + 1;
                if (dfs(v)) {
                    has = true;
                    candidates.push_back(iterator);
                }
                low[u] = std::min(low[u], low[v]);
            }
        }
    }
    return has;
}

void dfs_(int ban)
{
    memset(depth, -1, sizeof(*depth) * n);
    memset(used, 0, sizeof(*used) * m);
    if (~ban) {
        used[ban] = true;
    }
    candidates.clear();
    depth[s] = 0;
    dfs(s);
}

int main()
{
    scanf("%d%d%d%d", &n, &m, &s, &t);
    s --;
    t --;
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", x + (i << 1), x + (i << 1 | 1), w + i);
        x[i << 1] --;
        x[i << 1 | 1] --;
    }
    memset(head, -1, sizeof(*head) * n);
    for (int i = 0; i < m << 1; ++ i) {
        next[i] = head[x[i ^ 1]];
        head[x[i ^ 1]] = i;
    }
    dfs_(-1);
    if (candidates.empty()) {
        printf("%d\n%d\n\n", 0, 0);
    } else {
        auto candidates_ = candidates;
        std::pair<int, std::vector<int>> best(INT_MAX, {});
        for (auto&& c : candidates_) {
            auto&& c2 = c >> 1;
            dfs_(c2);
            if (candidates.empty()) {
                best = std::min(best, {w[c2], {c2}});
            } else {
                for (auto&& d : candidates) {
                    auto&& d2 = d >> 1;
                    if (depth[x[d]] <= low[x[d]]) {
                        best = std::min(best, {w[c2] + w[d2], {c2, d2}});
                    }
                }
            }
        }
        if (best.first < INT_MAX) {
            printf("%d\n%d\n", best.first, static_cast<int>(best.second.size()));
            for (auto&& e : best.second) {
                printf("%d ", e + 1);
            }
            puts("");
        } else {
            puts("-1");
        }
    }
}
