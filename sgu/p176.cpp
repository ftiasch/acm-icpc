// SGU 176 -- Flow construction
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 111;
const int M = N * N;

int n, m, from[M], to[M], capacity[M], full[M], nodeCount, flow[N][N], level[N], queue[N];

bool bfs(int source, int target) {
    memset(level, -1, sizeof(level));
    level[target] = 0;
    int head = 0;
    int tail = 0;
    queue[tail ++] = target;
    while (head != tail) {
        int u = queue[head ++];
        for (int v = 0; v < nodeCount; ++ v) {
            if (flow[v][u] > 0 && level[v] == -1) {
                level[v] = level[u] + 1;
                queue[tail ++] = v;
            }
        }
    }
    return level[source] != -1;
}

int dfs(int source, int target, int limit) {
    if (source == target) {
        return limit;
    }
    int delta = 0;
    for (int v = 0; v < nodeCount; ++ v) {
        if (flow[source][v] > 0 && level[source] == level[v] + 1) {
            int ret = dfs(v, target, min(flow[source][v], limit - delta));
            delta += ret;
            flow[source][v] -= ret;
            flow[v][source] += ret;
            if (delta == limit) {
                break;
            }
        }
    }
    return delta;
}

bool check(int limit) {
    int superSource = 0;
    int superTarget = n + 1;
    memset(flow, 0, sizeof(flow));
    for (int i = 0; i < m; ++ i) {
        if (full[i]) {
            flow[superSource][to[i]] += capacity[i];
            flow[from[i]][superTarget] += capacity[i];
        } else {
            flow[from[i]][to[i]] = capacity[i];
        }
    }
    flow[n][1] = limit;
    while (bfs(superSource, superTarget)) {
        dfs(superSource, superTarget, INT_MAX);
    }
    for (int i = 1; i <= n; ++ i) {
        if (flow[superSource][i] > 0) {
            return false;
        }
    }
    return true;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d%d", from + i, to + i, capacity + i, full + i);
    }
    nodeCount = n + 2;
    int lower = 0;
    int upper = 1000000000;
    if (check(upper)) {
        while (lower < upper) {
            int mider = (lower + upper) >> 1;
            if (check(mider)) {
                upper = mider;
            } else {
                lower = mider + 1;
            }
        }
        printf("%d\n", upper);
        check(upper);
        for (int i = 0; i < m; ++ i) {
            printf("%d%c", capacity[i] - flow[from[i]][to[i]], i == m - 1? '\n': ' ');
        }
    } else {
        puts("Impossible");
    }
    return 0;
}
