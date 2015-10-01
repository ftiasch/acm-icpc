#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>

int main()
{
    int n, m, a, b;
    while (scanf("%d%d%d%d", &n, &m, &a, &b) == 4) {
        std::vector <std::vector <int>> graph(n);
        for (int i = 0; i < m; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            a --;
            b --;
            graph[a].push_back(b);
            graph[b].push_back(a);
        }
        for (int i = 0; i < n; ++ i) {
            std::sort(graph[i].begin(), graph[i].end());
            graph[i].erase(std::unique(graph[i].begin(), graph[i].end()), graph[i].end());
        }
        std::vector <int> distance(n, -1);
        distance[0] = 0;
        std::queue <int> queue;
        queue.push(0);
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            for (int v : graph[u]) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.push(v);
                }
            }
        }
        int result = b;
        if (~distance[n - 1]) {
            result = std::min((long long)result, (long long)distance[n - 1] * a);
        }
        printf("%d\n", result);
    }
    return 0;
}
