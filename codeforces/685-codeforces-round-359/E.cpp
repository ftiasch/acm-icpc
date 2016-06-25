#include <algorithm>
#include <bitset>
#include <cstdio>
#include <cstring>
#include <functional>
#include <numeric>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 1000;
const int M = 200000;

using Bitset = std::bitset<N>;

int n, m, q, vertex[M + M], low[M], high[M], source[M], target[M], asc[M + M], dsc[M + M];
bool answer[M];
Bitset from[M + M], to[M + M];
std::vector<int> edges[N];

void update(Bitset* from, int* asc, int l, int r, int i)
{
    from[i].set(vertex[i ^ 1]);
    if (l <= asc[i] && asc[i] < r) {
        from[i] |= from[asc[i]];
    }
    if (l <= asc[i ^ 1] && asc[i ^ 1] < r) {
        from[i] |= from[asc[i ^ 1]];
    }
}

void solve(int l, int r, const std::vector<int>& queries)
{
    if (queries.empty()) {
        return;
    }
    for (int i = l << 1; i < r << 1; ++ i) {
        from[i].reset();
        from[i].set(vertex[i]);
        to[i] = from[i];
    }
    int middle = l + r >> 1;
    for (int i = (middle << 1) - 1; i >= l << 1; -- i) {
        update(from, asc, i, middle << 1, i);
    }
    for (int i = middle << 1; i < r << 1; ++ i) {
        update(to, dsc, middle << 1, i + 1, i);
    }
    std::vector<int> left_queries, right_queries;
    for (auto&& q : queries) {
        if (high[q] < middle - 1) {
            left_queries.push_back(q);
        } else if (middle < low[q]) {
            right_queries.push_back(q);
        } else {
            auto&& low_iterator = std::lower_bound(ALL(edges[source[q]]), low[q] << 1);
            auto high_iterator = std::upper_bound(ALL(edges[target[q]]), high[q] << 1 | 1);
            if (low_iterator != edges[source[q]].end() && high_iterator != edges[target[q]].begin()) {
                high_iterator --;
                if ((*low_iterator >> 1) <= high[q] && low[q] <= (*high_iterator >> 1)) {
                    auto&& from_mask = from[*low_iterator];
                    auto&& to_mask = to[*high_iterator];
                    answer[q] = (from_mask & to_mask).any();
                    if (from_mask.test(target[q])) {
                        answer[q] = true;
                    }
                }
            }
        }
    }
    solve(l, middle, left_queries);
    solve(middle, r, right_queries);
}

int main()
{
    scanf("%d%d%d", &n, &m, &q);
    for (int i = 0; i < m + m; ++ i) {
        scanf("%d", vertex + i);
        vertex[i] --;
    }
    memset(asc, -1, sizeof(asc));
    memset(dsc, -1, sizeof(dsc));
    for (int i = 0; i < m + m; ++ i) {
        edges[vertex[i]].push_back(i);
    }
    for (int i = 0; i < n; ++ i) {
        auto& e = edges[i];
        std::sort(ALL(e));
        for (int j = 0; j < static_cast<int>(e.size()); ++ j) {
            if (j + 1 < static_cast<int>(e.size())) {
                asc[e[j]] = e[j + 1];
            }
            if (j) {
                dsc[e[j]] = e[j - 1];
            }
        }
    }
    for (int i = 0; i < q; ++ i) {
        scanf("%d%d%d%d", low + i, high + i, source + i, target + i);
        low[i] --;
        high[i] --;
        source[i] --;
        target[i] --;
    }
    std::vector<int> queries(q);
    std::iota(ALL(queries), 0);
    solve(0, m, queries);
    for (int i = 0; i < q; ++ i) {
        puts(answer[i] ? "Yes" : "No");
    }
}
