// VK Cup 2012 Finals, Practice Session
// Problem C -- Trails and Glades
#include <cstdio>
#include <cstring>

const int N = 1000000;

int n, m, degree[N], parent[N];

int find(int u) {
    if (parent[u] == -1) {
        return u;
    }
    return parent[u] = find(parent[u]);
}

int count[N];

int main() {
    scanf("%d%d", &n, &m);
    memset(degree, 0, sizeof(degree));
    memset(parent, -1, sizeof(parent));
    int component = 1;
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        if (a && !degree[a]) {
            component ++;
        }
        if (b && !degree[b]) {
            component ++;
        }
        degree[a] ++;
        degree[b] ++;
        if (find(a) != find(b)) {
            component --;
            parent[find(a)] = find(b);
        }
    }
    int answer = 0;
    memset(count, 0, sizeof(count));
    for (int i = 0; i < n; ++ i) {
        answer += degree[i] & 1;
        count[find(i)] += degree[i] & 1;
    }
    for (int i = 0; i < n; ++ i) {
        if (find(i) == i && !count[i] && component > 1 && (!i || degree[i] > 0)) {
            answer += 2;
        }
    }
    printf("%d\n", answer >> 1);
    return 0;
}
