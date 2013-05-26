// Codeforces Beta Round #24
// Problem A -- Ring road
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 100;

int n, graph[N][N];
bool visit[N];
std::vector <int> cycle;

int get_cost(std::vector <int> &cycle) {
    int ret = 0;
    for (int i = 0; i < n; ++ i) {
        ret += graph[cycle[i]][cycle[(i + 1) % n]];
    }
    return ret;
}

int main() {
    scanf("%d", &n);
    memset(graph, -1, sizeof(graph));
    for (int i = 0; i < n; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --, b --;
        graph[a][b] = c;
        graph[b][a] = 0;
    }
    cycle.push_back(0);
    visit[0] = true;
    for (int i = 1; i < n; ++ i) {
        int u = cycle[i - 1];
        for (int v = 0; v < n; ++ v) {
            if (graph[u][v] != -1 && !visit[v]) {
                visit[v] = true;
                cycle.push_back(v);
                break;
            }
        }
    }
    int answer = get_cost(cycle);
    std::reverse(cycle.begin(), cycle.end());
    answer = std::min(answer, get_cost(cycle));
    printf("%d\n", answer);
    return 0;
}
