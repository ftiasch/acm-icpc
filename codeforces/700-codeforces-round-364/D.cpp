#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <deque>
#include <numeric>
#include <utility>
#include <vector>

const int N = 100000;

int a[N], count[N], count_count[N + 1], link[2][N + 1], result[N];

void make_link(int* prev, int* next, int u, int v)
{
    prev[v] = u;
    next[u] = v;
}

void add(int v, int d)
{
    auto& prev = link[d != 1];
    auto& next = link[d == 1];
    auto& c = count[v];
    if (!(count_count[c + d] ++)) {
        make_link(prev, next, c + d, next[c]);
        make_link(prev, next, c, c + d);
    }
    if (!-- count_count[c]) {
        make_link(prev, next, prev[c], next[c]);
    }
    c += d;
}

struct Query
{
    Query(int l, int r, int id) : l(l - 1), r(r - 1), id(id) {}

    int l, r, id;
};

bool operator < (const Query& a, const Query& b)
{
    return a.r < b.r;
}

std::deque<std::pair<int, int>> queue[2];

std::pair<int, int> get_min()
{
    int index = queue[0].empty() || !queue[1].empty() && queue[0].front().first > queue[1].front().first;
    auto front = queue[index].front();
    queue[index].pop_front();
    return front;
}

int solve()
{
    queue[0].clear();
    queue[1].clear();
    for (int v = link[1][0]; v; v = link[1][v]) {
//printf("(%d, %d)\n", v, count_count[v]);
        queue[0].emplace_back(v, count_count[v]);
    }
//puts("");
    int result = 0;
    std::pair<int, int> buffer;
    while (!(queue[0].empty() && queue[1].empty())) {
        auto m = get_min();
        if (!buffer.second) {
            buffer = m;
        } else {
            auto&& cost = buffer.first + m.first;
            result += cost;
            queue[1].emplace_back(cost, 1);
            buffer = {m.first, m.second - 1};
        }
        if (buffer.second > 1) {
            auto&& cost = buffer.first << 1;
            result += cost * (buffer.second >> 1);
            queue[1].emplace_back(cost, buffer.second >> 1);
            buffer.second &= 1;
        }
    }
    return result;
}

int main()
{
    int n, q;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        a[i] --;
    }
    scanf("%d", &q);
    std::vector<Query> queries;
    for (int i = 0; i < q; ++ i) {
        int l, r;
        scanf("%d%d", &l, &r);
        queries.emplace_back(l, r, i);
    }
    std::sort(queries.begin(), queries.end());
    int block = sqrt(n);
    for (int x = 0; x < n; x += block) {
        memset(count, 0, sizeof(count));
        memset(count_count, 0, sizeof(count_count));
        count_count[0] = N + 1;
        link[0][0] = link[1][0] = 0;
        for (int y = std::min(x + block, n) - 1, i = 0; y < n; ++ y) {
            if (x + block <= y) {
                add(a[y], 1);
            }
            while (i < q && queries[i].r <= y){
                if (x <= queries[i].l && queries[i].l < x + block) {
                    int end = std::min(x + block, queries[i].r + 1);
                    for (int j = queries[i].l; j < end; ++ j) {
                        add(a[j], 1);
                    }
                    result[queries[i].id] = solve();
                    for (int j = queries[i].l; j < end; ++ j) {
                        add(a[j], -1);
                    }
                }
                i ++;
            }
        }
    }
    for (int i = 0; i < q; ++ i) {
        printf("%d\n", result[i]);
    }
}
