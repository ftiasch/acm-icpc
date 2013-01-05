// Codeforces Round #147
// Problem E -- Build String
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>

const int N = 100;

const int V = 26 + N + 2;
const int E = 26 + 26 * N + N << 1;

int edge_count, first_edge[V], to[E], next_edge[E], capacity[E], cost[E];

void _add_edge(int u, int v, int _capacity, int _cost) {
    to[edge_count] = v;
    capacity[edge_count] = _capacity;
    cost[edge_count] = _cost;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void add_edge(int u, int v, int capacity, int cost) {
    _add_edge(u, v, capacity, cost);
    _add_edge(v, u, 0, -cost);
}

char string[N];
int n, count[26];

bool visit[V];
int from[V], distance[V];

int main() {
    scanf("%s", string);
    memset(count, 0, sizeof(count));
    for (int i = 0; string[i]; ++ i) {
        count[string[i] - 'a'] ++;
    }
    scanf("%d", &n);
    int source = 26 + n;
    int target = source + 1;
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < 26; ++ i) {
        add_edge(source, i, count[i], 0);
    }
    for (int j = 0; j < n; ++ j) {
        scanf("%s", string);
        memset(count, 0, sizeof(count));
        for (int k = 0; string[k]; ++ k) {
            count[string[k] - 'a'] ++;
        }
        for (int i = 0; i < 26; ++ i) {
            add_edge(i, 26 + j, count[i], 0);
        }
        int limit;
        scanf("%d", &limit);
        add_edge(26 + j, target, limit, j + 1);
    }
    int answer = 0;
    while (true) {
        for (int i = 0; i <= target; ++ i) {
            visit[i] = false;
            distance[i] = INT_MAX;
        }
        visit[source] = true;
        distance[source] = 0;
        std::queue <int> queue;
        queue.push(source);
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            visit[u] = false;
            for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                if (capacity[iter] > 0 && distance[u] + cost[iter] < distance[to[iter]]) {
                    from[to[iter]] = iter;
                    distance[to[iter]] = distance[u] + cost[iter];
                    if (!visit[to[iter]]) {
                        visit[to[iter]] = true;
                        queue.push(to[iter]);
                    }
                }
            }
        }
        if (distance[target] == INT_MAX) {
            break;
        }
        int delta = INT_MAX;
        for (int i = target; i != source; i = to[from[i] ^ 1]) {
            delta = std::min(delta, capacity[from[i]]);
        }
        for (int i = target; i != source; i = to[from[i] ^ 1]) {
            capacity[from[i]] -= delta;
            capacity[from[i] ^ 1] += delta;
            answer += cost[from[i]] * delta;
        }
    }
    for (int iter = first_edge[source]; iter != -1; iter = next_edge[iter]) {
        if (capacity[iter] > 0) {
            puts("-1");
            return 0;
        }
    }
    printf("%d\n", answer);
    return 0;
}
