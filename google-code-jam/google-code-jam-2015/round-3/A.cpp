#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>

std::vector <int> generate(int x_0, int a, int c, int r, int n)
{
    std::vector <int> result;
    for (int i = 0; i < n; ++ i) {
        result.push_back(x_0);
        x_0 = ((long long)x_0 * a + c) % r;
    }
    return result;
}

int n, d;
std::vector <int> s;
std::vector <std::pair <int, int>> events;
std::vector <std::vector <int>> children;

void dfs(int u, int low, int high)
{
    for (int v : children[u]) {
        int new_low  = std::max(low,  std::max(s[u], s[v]));
        int new_high = std::min(high, std::min(s[u], s[v]) + d + 1);
        dfs(v, new_low, new_high);
    }
}

int main()
{
    int T;
    scanf("%d", &T);
    for (int t = 1; t <= T; ++ t)
    {
        scanf("%d%d", &n, &d);
        int s_0, as, cs, rs;
        scanf("%d%d%d%d", &s_0, &as, &cs, &rs);
        s = generate(s_0, as, cs, rs, n);
        int m_0, am, cm, rm;
        scanf("%d%d%d%d", &m_0, &am, &cm, &rm);
        std::vector <int> m = generate(m_0, am, cm, rm, n);
        children.clear();
        children.resize(n);
        for (int i = 1; i < n; ++ i) {
            children[m[i] % i].push_back(i);
        }
        events.clear();
        std::vector <int> low(n), high(n);
        low[0]  = INT_MIN;
        high[0] = INT_MAX;
        std::vector <int> queue;
        queue.push_back(0);
        for (int i = 0; i < n; ++ i) {
            int u = queue[i];
            if (low[u] < high[u]) {
                events.push_back({low[u],   1});
                events.push_back({high[u], -1});
            }
            for (int v : children[u]) {
                low[v]  = std::max(low[u],  std::max(s[u], s[v]));
                high[v] = std::min(high[u], std::min(s[u], s[v]) + d + 1);
                queue.push_back(v);
            }
        }
        std::sort(events.begin(), events.end());
        int result = 0;
        int count = 0;
        for (const auto &e : events) {
            count += e.second;
            result = std::max(result, count);
        }
        printf("Case #%d: %d\n", t, result);
    }
    return 0;
}
