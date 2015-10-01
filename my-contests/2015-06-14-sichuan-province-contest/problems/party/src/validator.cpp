#include "testlib.h"

#include <vector>
#include <queue>

bool bipartite(std::vector <std::vector <int>> edges)
{
    int n = edges.size();
    std::vector <int> color(n, -1);
    for (int s = 0; s < n; ++ s) {
        if (color[s] == -1) {
            color[s] = 0;
            std::queue <int> queue;
            queue.push(s);
            while (!queue.empty()) {
                int u = queue.front();
                queue.pop();
                for (int v : edges[u]) {
                    if (color[v] == -1) {
                        color[v] = color[u] ^ 1;
                        queue.push(v);
                    }
                    if (color[u] == color[v]) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

int main()
{
    registerValidation();
    int n = inf.readInt(1, 1000);
    inf.readSpace();
    int m = inf.readInt(0, 10000);
    inf.readEoln();
    for (int i = 0; i < n; ++ i) {
        inf.readInt(1, 1000000);
        if (i + 1 < n) {
            inf.readSpace();
        } else {
            inf.readEoln();
        }
    }
    for (int i = 0; i < n; ++ i) {
        inf.readInt(1, 3);
        if (i + 1 < n) {
            inf.readSpace();
        } else {
            inf.readEoln();
        }
    }
    std::vector <std::vector <int>> edges(n);
    for (int i = 0; i < m; ++ i) {
        int a = inf.readInt(1, n) - 1;
        inf.readSpace();
        int b = inf.readInt(1, n) - 1;
        inf.readEoln();
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    ensure(bipartite(edges));
    inf.readEof();
    return 0;
}
