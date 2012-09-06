#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, a[N], longest[N];

const int V = 2 * N + 2;

int nodeCount, capacity[V][V], level[V], queue[V];

bool bfs(int source, int target) {
    memset(level, -1, sizeof(level));
    level[target] = 0;
    int tail = 0;
    queue[tail ++] = target;
    int head = 0;
    while (head != tail && level[source] == -1) {
        int v = queue[head ++];
        for (int u = 0; u < nodeCount; ++ u) {
            if (capacity[u][v] > 0 && level[u] == -1) {
                level[u] = level[v] + 1;
                queue[tail ++] = u;
            }
        }
    }
    return level[source] != -1;
}

int dfs(int source, int target, int left) {
    if (source == target) {
        return left;
    }
    int delta = 0;
    for (int v = 0; v < nodeCount; ++ v) {
        if (capacity[source][v] > 0 && level[source] == level[v] + 1) {
            int ret = dfs(v, target, min(left - delta, capacity[source][v]));
            capacity[source][v] -= ret;
            capacity[v][source] += ret;
            delta += ret;
            if (delta == left) {
                break;
            }
        }
    }
    return delta;
}

int main() {
    while (scanf("%d", &n) == 1) {
        assert(n < N);
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        for (int i = 0; i < n; ++ i) {
            longest[i] = 1;
            for (int j = 0; j < i; ++ j) {
                if (a[j] < a[i]) {
                    longest[i] = max(longest[i], longest[j] + 1);
                }
            }
        }
        int maxLength = *max_element(longest, longest + n);
        memset(capacity, 0, sizeof(capacity));
        int source = 2 * n;
        int target = 2 * n + 1;
        nodeCount = 2 * n + 2;
        for (int i = 0; i < n; ++ i) {
            if (longest[i] == 1) {
                capacity[source][i] ++;
            }
            if (longest[i] == maxLength) {
                capacity[n + i][target] ++;
            }
            capacity[i][n + i] ++;
            for (int j = 0; j < i; ++ j) {
                if (a[j] < a[i] && longest[j] + 1 == longest[i]) {
                    capacity[n + j][i] ++;
                }
            }
        }
//for (int i = 0; i < n; ++ i) {
//    printf("%d, ", longest[i]);
//}
//puts("");
        int maxFlow = 0;
        while (bfs(source, target)) {
            maxFlow += dfs(source, target, INT_MAX);
        }
        printf("%d\n%d\n", maxLength, maxFlow);
    }
    return 0;
}
