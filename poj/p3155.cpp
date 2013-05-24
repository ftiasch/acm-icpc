// Northeastern Europe 2006
// Hard Life
#include <cstdio>
#include <cstring>
#include <cassert>
#include <queue>

const int N = 100 + 2;

const int TRIES = 100;

const double INF = 1e10;
const double EPS = 1e-9;

int level[N], current[N];

const int M = (N * 2 + 2000) * 2;

int edge_count, first_edge[N], to[M], next_edge[M];
double capacity[M];

void _add_edge(int u, int v , double w) {
    to[edge_count] = v;
    capacity[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void add_edge(int a, int b, double c) {
    _add_edge(a, b, c);
    _add_edge(b, a, 0);
}

bool bfs(int source, int target) {
    memset(level, -1, sizeof(level));
    std::queue <int> queue;
    queue.push(source);
    level[source] = 0;
    while (!queue.empty()) {
        int u = queue.front();
        queue.pop();
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
            if (capacity[iter] > EPS && level[v] == -1) {
                level[v] = level[u] + 1;
                queue.push(v);
            }
        }
    }
    return level[target] != -1;
}

double dfs(int u, int target, double limit) {
    if (u == target) {
        return limit;
    }
    double delta = 0.0;
    for (int &iter = current[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (capacity[iter] > EPS && level[v] == level[u] + 1) {
            double ret = dfs(v, target, std::min(limit - delta, capacity[iter]));
            capacity[iter] -= ret;
            capacity[iter ^ 1] += ret;
            delta += ret;
            if (limit - delta <= EPS) {
                break;
            }
        }
    }
    return delta;
}

int n, m, degree[N];
bool graph[N][N];

double solve(double lambda) {
    int source = n;
    int target = n + 1;
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n; ++ i) {
        add_edge(source, i, lambda);
        add_edge(i, target, degree[i]);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (graph[i][j]) {
                add_edge(i, j, 1.0);
            }
        }
    }
    while (bfs(source, target)) {
        memcpy(current, first_edge, sizeof(first_edge));
        dfs(source, target, INF);
    }
    int size = 0;
    double ret = 0.0;
    for (int j = 0; j < n; ++ j) {
        if (level[j] == -1) {
            size ++;
            ret += degree[j];
            for (int i = 0; i < n; ++ i) {
                if (level[i] != -1 && graph[i][j]) {
                    ret -= 1.0;
                }
            }
        }
    }
    assert(size > 0);
    return ret / size;
}

int main() {
    scanf("%d%d", &n, &m);
    memset(graph, 0, sizeof(graph));
    memset(degree, 0, sizeof(degree));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        degree[a] ++;
        degree[b] ++;
        graph[a][b] = graph[b][a] = true;
    }
    double lambda = 2.0 * m / n;
    for (int _ = 0; _ < TRIES; ++ _) {
        lambda = solve(lambda);
    }
    int size = 0;
    for (int i = 0; i < n; ++ i) {
        size += level[i] == -1;
    }
    printf("%d\n", size);
    for (int i = 0; i < n; ++ i) {
        if (level[i] == -1) {
            printf("%d\n", i + 1);
        }
    }
    return 0;
}
