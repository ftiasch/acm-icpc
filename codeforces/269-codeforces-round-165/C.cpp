// Codeforces Round #165
// Problem C -- Flawed Flow
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>

const int N = 200000;

int n, m, edge_count, first_edge[N], to[N << 1], capacity[N << 1], next_edge[N << 1], direction[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    capacity[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int balance[N];

int main() {
    scanf("%d%d", &n, &m);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    memset(balance, 0, sizeof(balance));
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --, b --;
        add_edge(a, b, c);
        add_edge(b, a, c);
        balance[a] += c;
        balance[b] += c;
    }
    for (int i = 0; i < n; ++ i) {
        balance[i] >>= 1;
    }
    balance[0] = 0;
    balance[n - 1] = INT_MAX;
    memset(direction, -1, sizeof(direction));
    std::queue <int> queue;
    queue.push(0);
    while (!queue.empty()) {
        int u = queue.front();
        queue.pop();
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            if (direction[iter >> 1] == -1) {
                direction[iter >> 1] = iter & 1;
                int v = to[iter];
                balance[v] -= capacity[iter];
                if (!balance[v]) {
                    queue.push(v);
                }
            }
        }
    }
    for (int i = 0; i < m; ++ i) {
        printf("%d\n", direction[i]);
    }
    return 0;
}
