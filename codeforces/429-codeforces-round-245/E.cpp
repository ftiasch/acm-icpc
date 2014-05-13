#include <algorithm>
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>

const int N = 100000;

int n, color[N << 1];
std::vector <int> graph[N << 1];
std::vector <std::pair <int, int>> events;

void add_edge(int a, int b) {
    graph[a].push_back(b);
    graph[b].push_back(a);
}

void go(int u, int c) {
    color[u] = c;
    for (int v : graph[u]) {
        if (color[v] == -1) {
            go(v, c ^ 1);
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        events.push_back(std::make_pair(a * 2,     i << 1));
        events.push_back(std::make_pair(b * 2 + 1, i << 1 | 1));
        add_edge(i << 1, i << 1 | 1);
    }
    std::sort(events.begin(), events.end());
    for (int i = 0; i < (int)events.size(); i += 2) {
        add_edge(events[i].second, events[i + 1].second);
    }
    memset(color, -1, sizeof(color));
    for (int u = 0; u < n << 1; ++ u) {
        if (color[u] == -1) {
            go(u, 0);
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", color[i << 1], " \n"[i == n - 1]);
    }
    return 0;
}
