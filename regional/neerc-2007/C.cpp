// Northeastern Europe 2007
// Problem C -- Cactus Reloaded
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 50000;
const int M = N << 1;

int n, firstEdge[N], edgeCount, to[M << 1], nextEdge[M << 1], stackSize, stack[N], depth[N], lowest[N], longest[N], result, queue[N + N];
bool used[M];

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

void update(int &x, int a) {
    x = max(x, a);
}

void dfs(int u) {
    lowest[u] = depth[u];
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        if (!used[iter >> 1]) {
            used[iter >> 1] = true;
            int v = to[iter];
            if (depth[v] == -1) {
//printf("%d->%d\n", u, v);
                depth[v] = depth[u] + 1;
                stack[stackSize ++] = iter;
                dfs(v);
                if (depth[u] < lowest[v]) {
                    update(result, longest[u] + (longest[v] + 1));
                    update(longest[u], longest[v] + 1);
                    stackSize --;
                }
                if (depth[u] == lowest[v]) {
                    vector <int> cycle(1, longest[u]);
                    do {
                        cycle.push_back(longest[to[stack[-- stackSize]]]);
                    } while (stack[stackSize] != iter);
                    int cycleLength = cycle.size();
                    for (int i = 0; i < cycleLength; ++ i) {
                        update(longest[u], cycle[i] + min(i, cycleLength - i));
                    }
                    for (int i = 0; i < cycleLength; ++ i) {
                        cycle.push_back(cycle[i]);
                    }
                    int head = 0;
                    int tail = 0;
                    for (int i = 0; i < (int)cycle.size(); ++ i) {
                        while (head != tail && i - queue[head] > (cycleLength >> 1)) {
                            head ++;
                        }
                        if (head != tail) {
                            update(result, cycle[i] + cycle[queue[head]] + i - queue[head]);
                        }
                        while (head != tail && cycle[queue[tail - 1]] - queue[tail - 1] < cycle[i] - i) {
                            tail --;
                        }
                        queue[tail ++] = i;
                    }
                }
                lowest[u] = min(lowest[u], lowest[v]);
            } else {
//printf("%d->%d [style=\"dotted\"]\n", u, v);
                lowest[u] = min(lowest[u], depth[v]);
            }
        }
    }
}

int main() {
    int pathCount;
    scanf("%d%d", &n, &pathCount);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    while (pathCount > 0) {
        pathCount --;
        int pathLength;
        vector <int> path;
        scanf("%d", &pathLength);
        while (pathLength > 0) {
            pathLength --;
            int next;
            scanf("%d", &next);
            path.push_back(next - 1);
        }
        for (int i = 1; i < (int)path.size(); ++ i) {
            addEdge(path[i - 1], path[i]);
            addEdge(path[i], path[i - 1]);
        }
    }
    result = 0;
    stackSize = 0;
    memset(used, 0, sizeof(used));
    memset(depth, -1, sizeof(depth));
    memset(longest, 0, sizeof(longest));
    depth[0] = 0;
    dfs(0);
    for (int i = 0; i < n; ++ i) {
        update(result, longest[i]);
    }
    printf("%d\n", result);
    return 0;
}
