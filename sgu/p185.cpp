// SGU 185 -- Two shortest
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 400;
const int M = N * N * 2;
const int INF = 100000000;

int n, m, nodeCount, edgeCount, firstEdge[N], nextEdge[M], dist[N], queue[N], previous[N];
short to[M], capacity[M], cost[M];
bool visit[N];

void addEdge(int u, int v, int c, int w) {
    to[edgeCount] = v;
    capacity[edgeCount] = c;
    cost[edgeCount] = w;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

void dfs(int u) {
    if (u == n - 1) {
        printf("%d\n", n);
    } else {
        printf("%d ", u + 1);
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            if ((iter & 1) == 0 && capacity[iter] == 0) {
                capacity[iter] ++;
                capacity[iter ^ 1] --;
                dfs(to[iter]);
                break;
            }
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    nodeCount = n;
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        addEdge(a, b, 1, c);
        addEdge(b, a, 0, -c);
        addEdge(b, a, 1, c);
        addEdge(a, b, 0, -c);
    }
    int previousCost = -1;
    for (int k = 0; k < 2; ++ k) {
        for (int i = 0; i < nodeCount; ++ i) {
            dist[i] = INF;
        }
        dist[0] = 0;
        int head = 0;
        int tail = 0;
        queue[tail ++] = 0;
        while (head != tail) {
            int u = queue[head];
            head = (head + 1) % N;
            visit[u] = false;
            for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
                if (capacity[iter] > 0 && dist[u] + cost[iter] < dist[to[iter]]) {
                    dist[to[iter]] = dist[u] + cost[iter];
                    previous[to[iter]] = iter;
                    if (!visit[to[iter]]) {
                        visit[to[iter]] = true;
                        queue[tail] = to[iter];
                        tail = (tail + 1) % N;
                    }
                }
            }
        }
        if (dist[n - 1] == INF) {
            puts("No solution");
            return 0;
        }
        int currentCost = 0;
        for (int i = n - 1; i != 0; i = to[previous[i] ^ 1]) {
            currentCost += cost[previous[i]];
            capacity[previous[i]] --;
            capacity[previous[i] ^ 1] ++;
        }
        if (previousCost != -1 && previousCost != currentCost) {
            puts("No solution");
            return 0;
        }
        previousCost = currentCost;
    }
    for (int k = 0; k < 2; ++ k) {
        dfs(0);
    }
    return 0;
}
