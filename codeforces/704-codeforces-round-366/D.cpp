#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 100000;

int x[N], y[N], xs[N], ys[N], xd[N], yd[N];

std::vector<int> rename(int* x, int n)
{
    std::vector<int> values;
    for (int i = 0; i < n; ++ i) {
        values.push_back(x[i]);
    }
    std::sort(ALL(values));
    values.erase(std::unique(ALL(values)), values.end());
    for (int i = 0; i < n; ++ i) {
        x[i] = std::lower_bound(ALL(values), x[i]) - values.begin();
    }
    return values;
}

int find(const std::vector<int>& values, int x)
{
    auto iterator = std::lower_bound(ALL(values), x);
    if (iterator == values.end()) {
        return -1;
    }
    return *iterator == x ? iterator - values.begin() : -1;
}

const int V = N + N + 4;
const int E = (N * 7 + 1) * 2;

int edge_count, head[V], first[V], to[E], capacity[E], next[E], level[V];

void add_edge__(int u, int v, int w)
{
    to[edge_count] = v;
    capacity[edge_count] = w;
    next[edge_count] = head[u];
    head[u] = edge_count ++;
}

void add_edge_(int a, int b, int c)
{
    add_edge__(a, b, c);
    add_edge__(b, a, 0);
}

void add_edge(int ss, int st, int a, int b, int low, int high)
{
    add_edge_(a, st, low);
    add_edge_(ss, b, low);
    add_edge_(a, b, high - low);
}

bool bfs(int V, int s, int t)
{
    memset(level, -1, sizeof(*level) * V);
    level[t] = 0;
    std::queue<int> queue;
    queue.push(t);
    while (!queue.empty() && !~level[s]) {
        int u = queue.front();
        queue.pop();
        for (int iterator = head[u]; ~iterator; iterator = next[iterator]) {
            int v = to[iterator];
            if (v < V && capacity[iterator ^ 1] && !~level[v]) {
                level[v] = level[u] + 1;
                queue.push(v);
            }
        }
    }
    return ~level[s];
}

int dfs(int V, int u, int t, int limit)
{
    if (u == t) {
        return limit;
    }
    int delta = 0;
    for (int& iterator = first[u]; ~iterator; iterator = next[iterator]) {
        int v = to[iterator];
        if (v < V && capacity[iterator] && level[u] == level[v] + 1) {
            int tmp = dfs(V, v, t, std::min(limit - delta, capacity[iterator]));
            capacity[iterator] -= tmp;
            capacity[iterator ^ 1] += tmp;
            delta += tmp;
            if (delta == limit) {
                return delta;
            }
        }
    }
    level[u] = -1;
    return delta;
}

int max_flow(int v, int s, int t)
{
    int result = 0;
    while (bfs(v, s, t)) {
        memcpy(first, head, sizeof(*head) * v);
        result += dfs(v, s, t, INT_MAX);
    }
    return result;
}

int main()
{
    int n, m, r, b;
    scanf("%d%d%d%d", &n, &m, &r, &b);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    auto x_values = rename(x, n);
    auto y_values = rename(y, n);
    for (int i = 0; i < n; ++ i) {
        xs[x[i]] ++;
        ys[y[i]] ++;
    }
    int nx = x_values.size();
    int ny = y_values.size();
    memcpy(xd, xs, sizeof(*xs) * nx);
    memcpy(yd, ys, sizeof(*ys) * ny);
    for (int i = 0; i < m; ++ i) {
        int t, l, d;
        scanf("%d%d%d", &t, &l, &d);
        l = find(t == 1 ? x_values : y_values, l);
        if (~l) {
            auto& ref = (t == 1 ? xd : yd)[l];
            ref = std::min(ref, d);
        }
    }
    int s = nx + ny;
    int t = s + 1;
    int ss = t + 1;
    int st = ss + 1;
    int v = st + 1;
    memset(head, -1, sizeof(*head) * v);
    add_edge_(t, s, n);
    for (int i = 0; i < n; ++ i) {
        add_edge_(x[i], nx + y[i], 1);
    }
    bool valid = true;
    for (int i = 0; i < nx; ++ i) {
        int low = xs[i] - xd[i] + 1 >> 1;
        int high = xs[i] + xd[i] >> 1;
        valid &= low <= high;
        add_edge(ss, st, s, i, low, high);
    }
    for (int i = 0; i < ny; ++ i) {
        int low = ys[i] - yd[i] + 1 >> 1;
        int high = ys[i] + yd[i] >> 1;
        valid &= low <= high;
        add_edge(ss, st, nx + i, t, low, high);
    }
    assert(edge_count < E);
    if (valid) {
        max_flow(v, ss, st);
        for (int iterator = head[ss]; ~iterator; iterator = next[iterator]) {
            valid &= !capacity[iterator];
        }
    }
    if (valid) {
        long long result = capacity[1];
        capacity[0] = capacity[1] = 0;
        result += max_flow(v - 2, s, t);
        std::cout << result * std::min(r, b) + (n - result) * std::max(r, b) << std::endl;
        char min_one = r < b ? 'r' : 'b';
        char max_one = r < b ? 'b' : 'r';
        for (int i = 0; i < n; ++ i) {
            putchar(capacity[i + 1 << 1] ? max_one : min_one);
        }
        puts("");
    } else {
        puts("-1");
    }
}
