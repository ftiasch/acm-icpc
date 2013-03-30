// SGU 213 -- Strong Defence
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 400;

int n, s, t, graph[N][N], distance[N];
std::vector <int> sets[N];

int main() {
    int m;
    scanf("%d%d%d%d", &n, &m, &s, &t);
    s --;
    t --;
    memset(graph, 0, sizeof(graph));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph[a][b] = graph[b][a] = i + 1;
    }
    std::fill(distance, distance + n, INT_MAX);
    distance[s] = 0;
    std::queue <int> queue;
    queue.push(s);
    while (!queue.empty()) {
        int u = queue.front();
        queue.pop();
        for (int v = 0; v < n; ++ v) {
            if (graph[u][v] && distance[v] == INT_MAX) {
                distance[v] = distance[u] + 1;
                queue.push(v);
            }
        }
    }
    int count = distance[t];
    printf("%d\n", count);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            if (graph[i][j]) {
                sets[std::min(std::min(distance[i], distance[j]), count - 1)].push_back(graph[i][j]);
            }
        }
    }
    for (int i = 0; i < count; ++ i) {
        printf("%d", (int)sets[i].size());
        foreach (iter, sets[i]) {
            printf(" %d", *iter);
        }
        puts("");
    }
    return 0;
}
