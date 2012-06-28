// SGU 194 -- Reactor Cooling
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 202;
const int M = N * N;

int n, m, from[M], to[M], lower[M], upper[M], nodeCount, capacity[N][N], level[N], queue[N];

bool bfs(int source, int target) {
    memset(level, -1, sizeof(level));
    level[target] = 0;
    int head = 0;
    int tail = 0;
    queue[tail ++] = target;
    while (head != tail && level[source] == -1) {
        int j = queue[head ++];
        for (int i = 0; i < nodeCount; ++ i) {
            if (capacity[i][j] > 0 && level[i] == -1) {
                level[i] = level[j] + 1;
                queue[tail ++] = i;
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
    for (int i = 0; i <nodeCount; ++ i) {
        if (capacity[source][i] > 0 && level[source] == level[i] + 1) {
            int buffer = dfs(i, target, min(limit - delta, capacity[source][i]));
            delta += buffer;
            capacity[source][i] -= buffer;
            capacity[i][source] += buffer;
            if (delta == limit) {
                break;
            }
        }
    }
    return delta;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d%d", from + i, to + i, lower + i, upper + i);
    }
    nodeCount = n + 2;
    int source = 0;
    int target = n + 1;
    memset(capacity, 0, sizeof(capacity));
    for (int i = 0; i < m; ++ i) {
        capacity[from[i]][target] += lower[i];
        capacity[source][to[i]] += lower[i];
        capacity[from[i]][to[i]] += upper[i] - lower[i];
    }
    while (bfs(source, target)) {
        dfs(source, target, INT_MAX);
    }
    bool check = true;
    for (int i = 1; i <= n; ++ i) {
        check &= capacity[source][i] == 0;
    }
    if (!check) {
        puts("NO");
    } else {
        puts("YES");
        for (int i = 0; i < m; ++ i) {
            printf("%d\n", upper[i] - capacity[from[i]][to[i]]);
        }
    }
    return 0;
}
