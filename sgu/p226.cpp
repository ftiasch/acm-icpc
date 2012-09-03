// SGU 226 -- Colored graph
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 200;
const int M = N * N;

int n, m;
int edgeCount, firstEdge[N], to[M], color[M], nextEdge[M];

void addEdge(int u, int v, int w) {
    to[edgeCount] = v;
    color[edgeCount] = w;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int d[N][3];
bool in[N][3];

int main() {
    scanf("%d%d", &n, &m);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < m; ++ i) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        addEdge(-- u, -- v, -- w);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 3; ++ j) {
            d[i][j] = INT_MAX;
        }
    }
    queue <pair <int, int> > q;
    memset(in, 0, sizeof(in));
    for (int j = 0; j < 3; ++ j) {
        d[0][j] = 0;
        in[0][j] = true;
        q.push(make_pair(0, j));
    }
    while (!q.empty()) {
        pair <int, int> ret = q.front();
        q.pop();
        int u = ret.first;
        int p = ret.second;
        in[u][p] = false;
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            if (color[iter] != p && d[u][p] + 1 < d[to[iter]][color[iter]]) {
                d[to[iter]][color[iter]] = d[u][p] + 1;
                if (!in[to[iter]][color[iter]]) {
                    in[to[iter]][color[iter]] = true;
                    q.push(make_pair(to[iter], color[iter]));
                }
            }
        }
    }
    int result = INT_MAX;
    for (int j = 0; j < 3; ++ j) {
        result = min(result, d[n - 1][j]);
    }
    printf("%d\n", result == INT_MAX? -1: result);
    return 0;
}
