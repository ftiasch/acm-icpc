#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>

const int N = 100;
const int V = 102;
const int E = 802;

int edge_count, head[V], to[E], capacity[E], cost[E], next[E], balance[N], distance[V], back[V];
bool visited[V];

void add_edge(int u, int v, int ca, int co)
{
    // if (ca) {
    //     printf("%d->%d %d %d\n", u + 1, v + 1, ca, co);
    // }
    to[edge_count] = v;
    capacity[edge_count] = ca;
    cost[edge_count] = co;
    next[edge_count] = head[u];
    head[u] = edge_count ++;
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    edge_count = 0;
    memset(head, -1, sizeof(*head) * (n + 2));
    int result = 0;
    for (int i = 0; i < m; ++ i) {
        int u, v, c, f;
        scanf("%d%d%d%d", &u, &v, &c, &f);
        u --;
        v --;
        balance[u] -= f;
        balance[v] += f;
        if (f <= c) {
            add_edge(u, v, c - f, 1);
            add_edge(v, u, 0, -1);
            add_edge(v, u, f, 1);
            add_edge(u, v, 0, -1);
            add_edge(u, v, INT_MAX, 2);
            add_edge(v, u, 0, -2);
        } else {
            result += f - c;
            add_edge(u, v, INT_MAX, 2);
            add_edge(v, u, 0, -2);
            add_edge(v, u, f - c, 0);
            add_edge(u, v, 0, 0);
            add_edge(v, u, c, 1);
            add_edge(u, v, 0, -1);
        }
    }
    add_edge(n - 1, 0, INT_MAX, 0);
    add_edge(0, n - 1, 0, 0);
    int s = n;
    int t = n + 1;
    for (int i = 0; i < n; ++ i) {
        if (balance[i] > 0) {
            add_edge(s, i, balance[i], 0);
            add_edge(i, s, 0, 0);
        }
        if (balance[i] < 0) {
            add_edge(i, t, -balance[i], 0);
            add_edge(t, i, 0, 0);
        }
    }
    while (true) {
        memset(visited, 0, sizeof(*visited) * (n + 2));
        std::fill(distance, distance + (n + 2), INT_MAX);
        distance[s] = 0;
        std::queue<int> queue;
        queue.push(s);
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            visited[u] = false;
            for (int iterator = head[u]; ~iterator; iterator = next[iterator]) {
                int v = to[iterator];
                if (capacity[iterator] > 0 && distance[u] + cost[iterator] < distance[v]) {
                    back[v] = iterator;
                    distance[v] = distance[u] + cost[iterator];
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.push(v);
                    }
                }
            }
        }
        if (distance[t] == INT_MAX) {
            break;
        }
        int delta = INT_MAX;
        for (int v = t; v != s; v = to[back[v] ^ 1]) {
            delta = std::min(delta, capacity[back[v]]);
        }
        for (int v = t; v != s; v = to[back[v] ^ 1]) {
            capacity[back[v]] -= delta;
            capacity[back[v] ^ 1] += delta;
        }
        result += distance[t] * delta;
    }
    printf("%d\n", result);
}
