// SGU 212 -- Data Transmission
#include <cstdio>
#include <cstring>
#include <climits>
#include <stack>
#include <algorithm>

const int N = 1500;
const int M = 300000;

int n, m, l, level[N], first_edge[N], from[M], to[M], flow[M], capacity[M], next_edge[M];

int order[N], extract[N];
bool frozen[N];

std::stack <int> stack[N];

bool by_level(int i, int j) {
    return level[i] < level[j];
}

int main() {
    scanf("%d%d%d", &n, &m, &l);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", level + i);
        order[i] = i;
    }
    std::sort(order, order + n, by_level);
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", from + i, to + i, capacity + i);
        from[i] --;
        to[i] --;
        next_edge[i] = first_edge[from[i]];
        first_edge[from[i]] = i;
    }
    memset(flow, 0, sizeof(flow));
    memset(extract, 0, sizeof(extract));
    memset(frozen, 0, sizeof(frozen));
    for (int i = 0; i < n; ++ i) {
        if (level[i] == 1) {
            extract[i] = INT_MAX;
        }
        if (level[i] == l) {
            extract[i] = INT_MIN;
        }
    }
    while (true) {
        for (int i = 0; i < n; ++ i) {
            int u = order[i];
            if (extract[u] > 0) {
                for (int &iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                    int v = to[iter];
                    if (!frozen[v]) {

                        int delta = std::min(extract[u], capacity[iter] - flow[iter]);
                        extract[u] -= delta;
                        extract[v] += delta;
                        flow[iter] += delta;
                        stack[v].push(iter);
                        if (extract[u] == 0) {
                            break;
                        }
                    }
                }
            }
        }
        int i = n - 1;
        while (extract[order[i]] <= 0) {
            i --;
        }
        if (level[order[i]] == 1) {
            break;
        }
        int u = order[i];
        frozen[u] = true;
        while (extract[u] > 0) {
            int iter = stack[u].top();
            stack[u].pop();
            int delta = std::min(extract[u], flow[iter]);
            extract[from[iter]] += delta;
            extract[u] -= delta;
            flow[iter] -= delta;
        }
    }
    for (int i = 0; i < m; ++ i) {
        printf("%d\n", flow[i]);
    }
    return 0;
}
