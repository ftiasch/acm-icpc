// Codeforces Round #147
// Problem D -- T-decomposition
#include <cstdio>
#include <cstring>

const int N = 100000;

int n, edge_count, first_edge[N], to[N << 1], next_edge[N << 1];

void add_edge(int u, int v) {
    to[edge_count] = v;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int main() {
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        add_edge(a, b);
        add_edge(b, a);
    }
    printf("%d\n", n - 1);
    for (int i = 0; i < n - 1; ++ i) {
        printf("2 %d %d\n", 1 + to[i << 1], 1 + to[(i << 1) | 1]);
    }
    for (int i = 0; i < n; ++ i) {
        int p = -1;
        for (int iter = first_edge[i]; iter != -1; iter = next_edge[iter]) {
            if (p != -1) {
                printf("%d %d\n", 1 + (p >> 1), 1 + (iter >> 1));
            }
            p = iter;
        }
    }
    return 0;
}
