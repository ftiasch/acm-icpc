// Codeforces Round #142
// Problem D -- Planets
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 100000;
const int M = 100000 << 1;


int n, m;
bool visit[N];
int edgeCount, firstEdge[N], to[M], length[M], nextEdge[M], dist[N];
vector <int> arrive[N];

void addEdge(int u, int v, int w) {
    to[edgeCount] = v;
    length[edgeCount] = w;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int main() {
    scanf("%d%d", &n, &m);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        addEdge(a, b, c);
        addEdge(b, a, c);
    }
    for (int i = 0; i < n; ++ i) {
        int k;
        scanf("%d", &k);
        while (k --) {
            int t;
            scanf("%d", &t);
            arrive[i].push_back(t);
        }
    }
    arrive[n - 1].clear();
    for (int i = 0; i < n; ++ i) {
        dist[i] = INT_MAX;
    }
    dist[0] = 0;
    priority_queue <pair <int, int> > heap;
    heap.push(make_pair(0, 0));
    memset(visit, 0, sizeof(visit));
    while (!heap.empty()) {
        int u = heap.top().second;
        heap.pop();
        if (!visit[u]) {
            visit[u] = true;
            while (binary_search(arrive[u].begin(), arrive[u].end(), dist[u])) {
                dist[u] ++;
            }
            for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
                if (dist[u] + length[iter] < dist[to[iter]]) {
                    dist[to[iter]] = dist[u] + length[iter];
                    heap.push(make_pair(-dist[to[iter]], to[iter]));
                }
            }
        }
    }
    printf("%d\n", dist[n - 1] == INT_MAX? -1: dist[n - 1]);
    return 0;
}
