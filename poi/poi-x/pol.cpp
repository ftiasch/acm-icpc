// POI X Stage II -- Connections(pol)
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <queue>
using namespace std;

const int N = 101;
const int M = N * N;
const int K = 101;

int n, m, result[N][N][K], edgeCount, firstEdge[N], to[M], length[M], nextEdge[M], counter[N];

int main() {
    scanf("%d%d", &n, &m);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < m; ++ i) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        to[edgeCount] = v;
        length[edgeCount] = w;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }
    for (int source = 1; source <= n; ++ source) {
        memset(counter, 0, sizeof(counter));
        for (int i = 1; i <= n; ++ i) {
            for (int k = 1; k <= K; ++ k) {
                result[source][i][k] = INT_MAX;
            }
        }
        counter[source] = -1;
        priority_queue <pair <int, int> > heap;
        heap.push(make_pair(0, source));
        while (!heap.empty()) {
            int d = -heap.top().first;
            int u = heap.top().second;
            heap.pop();
            if (counter[u] >= K) {
                continue;
            }
            result[source][u][++ counter[u]] = d;
            for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
                heap.push(make_pair(-(d + length[iter]), to[iter]));
            }
        }
    }
    int queryCount;
    scanf("%d", &queryCount);
    while (queryCount > 0) {
        queryCount --;
        int u, v, k;
        scanf("%d%d%d", &u, &v, &k);
        printf("%d\n", result[u][v][k] == INT_MAX? -1: result[u][v][k]);
    }
    return 0;
}
