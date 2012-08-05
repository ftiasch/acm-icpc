// POI X Stage I -- Smugglers(prz)
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>
using namespace std;

const int N = 5000;
const int M = 100000;

struct Graph {
    int n, firstEdge[N], edgeCount, to[M], length[M], nextEdge[M], shortest[N];
    bool visit[N];

    Graph(int n): n(n) {
        edgeCount = 0;
        memset(firstEdge, -1, sizeof(firstEdge));
    }

    void addEdge(int u, int v, int w) {
        to[edgeCount] = v;
        length[edgeCount] = w;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }

    void prepare() {
        for (int i = 0; i < n; ++ i) {
            shortest[i] = INT_MAX;
        }
        shortest[0] = 0;
        memset(visit, 0, sizeof(visit));
        queue <int> q;
        q.push(0);
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            visit[u] = false;
            for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
                if (shortest[u] + length[iter] < shortest[to[iter]]) {
                    shortest[to[iter]] = shortest[u] + length[iter];
                    if (!visit[to[iter]]) {
                        visit[to[iter]] = true;
                        q.push(to[iter]);
                    }
                }
            }
        }
    }

    int get(int i) {
        return shortest[i];
    }
};

int n, m, weight[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", weight + i);
    }
    scanf("%d", &m);
    Graph *graph = new Graph(n);
    Graph *revGraph = new Graph(n);
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        graph->addEdge(a, b, c);
        revGraph->addEdge(b, a, c);
    }
    graph->prepare();
    revGraph->prepare();
    int result = INT_MAX;
    for (int i = 0; i < n; ++ i) {
        if (graph->get(i) != INT_MAX && revGraph->get(i) != INT_MAX) {
            result = min(result, graph->get(i) + revGraph->get(i) + weight[i] / 2);
        }
    }
    printf("%d\n", result);
    return 0;
}
