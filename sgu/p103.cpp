// SGU 103 -- Traffic Lights
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 333;
const int M = 33333;
const int INF = 1000000000;

int source, target, n, m, initial_color[N], remainding_time[N], period[N][2], edge_count, first_edge[N], to[M], length[M], next_edge[M], shortest[N], next_node[N];
bool visit[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    length[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int get_current_color(int u, int t) {
    if (t < remainding_time[u]) {
        return initial_color[u];
    }
    int remainder = (t - remainding_time[u]) % (period[u][0] + period[u][1]);
    return initial_color[u] ^ (remainder < period[u][0]);
}

int get_current_remainder(int u, int t) {
    if (t < remainding_time[u]) {
        return remainding_time[u] - t;
    }
    int remainder = (t - remainding_time[u]) % (period[u][0] + period[u][1]);
    if (remainder < period[u][0]) {
        return period[u][0] - remainder;
    }
    return period[u][0] + period[u][1] - remainder;
}

void print_path(int s, int t) {
    if (s != t) {
        print_path(s, next_node[t]);
    }
    printf("%d ", t);
}

int main() {
    scanf("%d%d%d%d", &source, &target, &n, &m);
    for (int i = 1; i <= n; ++ i) {
        char buffer[22];
        scanf("%s%d", buffer, remainding_time + i);
        initial_color[i] = *buffer == 'P';
        scanf("%d%d", &period[i][initial_color[i] ^ 1], &period[i][initial_color[i]]);
    }
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        add_edge(a, b, c);
        add_edge(b, a, c);
    }
    for (int i = 1; i <= n; ++ i) {
        shortest[i] = INF;
    }
    shortest[source] = 0;
    memset(visit, 0, sizeof(visit));
    while (true) {
        int pivot = 1;
        while (pivot <= n && visit[pivot]) {
            pivot ++;
        }
        if (pivot > n) {
            break;
        }
        for (int i = 1; i <= n; ++ i) {
            if (!visit[i] && shortest[i] < shortest[pivot]) {
                pivot = i;
            }
        }
        visit[pivot] = true;
        int u = pivot;
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
// update
            int waiting_time = 0;
            int t = shortest[u];
            for (int times = 0; times < 10; ++ times) {
//printf("%d: %d\n", times, t);
                if (get_current_color(u, t) == get_current_color(v, t)) {
//printf("@%d %d->%d %d\n", t, u, to[iter], waiting_time);
                    if (shortest[u] + waiting_time + length[iter] < shortest[v]) {
                        shortest[v] = shortest[u] + waiting_time + length[iter];
                        next_node[v] = u;
                    }
                    break;
                } else {
                    int delta = min(get_current_remainder(u, t), get_current_remainder(v, t));
                    waiting_time += delta;
                    t += delta;
                }
            }
        }
    }
    if (shortest[target] == INF) {
        puts("0");
    } else {
        printf("%d\n", shortest[target]);
        print_path(source, target);
        puts("");
    }
    return 0;
}
