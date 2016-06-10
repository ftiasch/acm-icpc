#include <cstdio>
#include <cstring>
#include <vector>

const int N = 400;

int distance[N][N], stamp[N];
bool visited[N];
double tmr_prob[N], max_prob[N];
std::vector<int> graph[N];

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            distance[i][j] = i == j ? 0 : n;
        }
    }
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        distance[a][b] = distance[b][a] = 1;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                distance[i][j] = std::min(distance[i][j], distance[i][k] + distance[k][j]);
            }
        }
    }
    double result = 0.;
    int time = 0;
    for (int a = 0; a < n; ++ a) {
        double new_result = 0.;
        for (int da = 0; da < n; ++ da) {
            memset(visited, 0, sizeof(*visited) * n);
            memset(tmr_prob, 0, sizeof(*tmr_prob) * n);
            for (int u = 0; u < n; ++ u) {
                if (distance[a][u] == da) {
                    int deg = graph[u].size();
                    for (int v : graph[u]) {
                        visited[v] = true;
                        tmr_prob[v] += 1. / n / deg;
                    }
                }
            }
            std::vector<int> vertices;
            for (int u = 0; u < n; ++ u) {
                if (visited[u]) {
                    vertices.push_back(u);
                }
            }
            if (!vertices.empty()) {
                double best = 1. / n;
                for (int b = 0; b < n; ++ b) {
                    double cum_prob = 0.;
                    time ++;
                    for (int v : vertices) {
                        int g = distance[b][v];
                        if (stamp[g] != time) {
                            stamp[g] = time;
                            max_prob[g] = 0.;
                        }
                        if (tmr_prob[v] > max_prob[g]) {
                            cum_prob += tmr_prob[v] - max_prob[g];
                            max_prob[g] = tmr_prob[v];
                        }
                    }
                    best = std::max(best, cum_prob);
                }
                new_result += best;
            }
        }
        result = std::max(result, new_result);
    }
    printf("%.9f\n", result);
}
