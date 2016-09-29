#include <algorithm>
#include <cstdio>
#include <cstring>
#include <queue>

const int N = 1000;
const int M = 10000;
const int INF = (int)1e9 + 1;

int n, l, s, t, head[N], to[M << 1], next[M << 1], length[M], from_s[N], to_t[N];

bool solve(int pass)
{
    auto distance = pass ? from_s : to_t;
    auto source = pass ? s : t;
    std::fill(distance, distance + n, INF);
    distance[source] = 0;
    std::priority_queue<std::pair<int, int>> queue;
    queue.emplace(0, source);
    while (!queue.empty()) {
        auto t = queue.top();
        queue.pop();
        auto u = t.second;
        if (-t.first == distance[u]) {
            for (auto iterator = head[u]; ~iterator; iterator = next[iterator]) {
                auto v = to[iterator];
                auto& ref = length[iterator >> 1];
                if (!ref) {
                    if (pass) {
                        if (from_s[u] + v[to_t] < l) {
                            ref = l - from_s[u] - v[to_t];
                            return true;
                        }
                        ref = 1;
                    } else {
                        continue;
                    }
                }
                if (distance[v] > distance[u] + ref) {
                    distance[v] = distance[u] + ref;
                    queue.emplace(-distance[v], v);
                }
            }
        }
    }
    return false;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("B.in", "r", stdin);
#endif
    int m;
    scanf("%d%d%d%d%d", &n, &m, &l, &s, &t);
    for (int i = 0; i < m << 1; ++ i) {
        scanf("%d", to + i);
        if (i & 1) {
            scanf("%d", length + (i >> 1));
        }
    }
    memset(head, -1, sizeof(head));
    for (int i = 0; i < m << 1; ++ i) {
        auto& h = head[to[i ^ 1]];
        next[i] = h;
        h = i;
    }
    solve(0);
    if (s[to_t] < l) {
        puts("NO");
    } else {
        if (s[to_t] > l && !solve(1)) {
            puts("NO");
            return 0;
        }
        puts("YES");
        for (int i = 0; i < m << 1; ++ i) {
            printf("%d ", to[i]);
            if (i & 1) {
                auto l = length[i >> 1];
                printf("%d\n", l ? l : INF);
            }
        }
    }
}
