#include <algorithm>
#include <cstdio>
#include <numeric>
#include <queue>
#include <vector>

const int N = 1000;

std::vector <int> graph[N];

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    std::vector <int> result(n);
    for (int s = 0; s < n; ++ s) {
        std::vector <int> distance(n, -1);
        distance[s] = 0;
        std::queue <int> queue;
        queue.push(s);
        int s0 = n;
        while (!queue.empty()) {
            int u = queue.front();
            s0 = std::min(s0, u);
            queue.pop();
            for (int v : graph[u]) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.push(v);
                } else if (distance[u] == distance[v]) {
                    puts("-1");
                    return 0;
                }
            }
        }
        result[s0] = std::max(result[s0], *std::max_element(distance.begin(), distance.end()));
    }
    printf("%d\n", std::accumulate(result.begin(), result.end(), 0));
    return 0;
}
