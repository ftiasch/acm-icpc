// SGU 326 -- Perspective
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>

const int N = 20;

int n, w[N], r[N], a[N][N];

const int V = N * N + N + 2;
const int E = (N * N * 3 + N) * 2;

int edge_count, first_edge[V], to[E], capacity[E], next_edge[E], current[V], level[V];

void _add_edge(int u, int v, int w) {
    to[edge_count] = v;
    capacity[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void add_edge(int u, int v, int w) {
    _add_edge(u, v, w);
    _add_edge(v, u, 0);
}

bool bfs(int source, int target) {
    memset(level, -1, sizeof(level));
    level[target] = 0;
    std::queue <int> queue;
    queue.push(target);
    while (!queue.empty() && level[source] == -1) {
        int u = queue.front();
        queue.pop();
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            if (capacity[iter ^ 1] > 0 && level[to[iter]] == -1) {
                level[to[iter]] = level[u] + 1;
                queue.push(to[iter]);
            }
        }
    }
    return level[source] != -1;
}

int dfs(int source, int target, int left) {
    if (source == target) {
        return left;
    }
    int delta = 0;
    for (int &iter = current[source]; iter != -1; iter = next_edge[iter]) {
        if (capacity[iter] > 0 && level[source] == level[to[iter]] + 1) {
            int ret = dfs(to[iter], target, std::min(left - delta, capacity[iter]));
            capacity[iter] -= ret;
            capacity[iter ^ 1] += ret;
            delta += ret;
            if (delta == left) {
                break;
            }
        }
    }
    return delta;
}


int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", w + i);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", r + i);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &a[i][j]);
        }
    }
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    int upper_bound = w[0] + r[0];
    int source = n * n + n;
    int target = source + 1;
    bool found = false;
    for (int i = 1; i < n; ++ i) {
        found |= upper_bound < w[i];
        add_edge(i, target, std::max(upper_bound - w[i], 0));
    }
    int total = n;
    for (int i = 1; i < n; ++ i) {
        for (int j = 1; j < i; ++ j) {
            add_edge(source, total, a[i][j]);
            add_edge(total, i, INT_MAX);
            add_edge(total, j, INT_MAX); 
            total ++;
        }
    }
    while (bfs(source, target)) {
        for (int i = 0; i <= target; ++ i) {
            current[i] = first_edge[i];
        }
        dfs(source, target, INT_MAX);
    }
    for (int iter = first_edge[source]; iter != -1; iter = next_edge[iter]) {
        found |= capacity[iter] > 0;
    }
    puts(found ? "NO" : "YES");
    return 0;
}
