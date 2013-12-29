#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 500;
const long long INF = (long long)1e18;

int n, limit, color[N];
long long distance[N][N];
std::vector <int> tree[N];

int size[N];
short minimum[N][N + 1][N + 1];

const short MAX = 10000; 

void update(short &x, short a) {
    x = std::min(x, a);
}

void go(int p, int u) {
    size[u] = 1;
    foreach (iter, tree[u]) {
        int v = *iter;
        if (v != p) {
            go(u, v);
            size[u] += size[v];
        }
    }
    for (int c = 0; c < n; ++ c) {
        if (distance[u][c] > limit) {
            for (int j = 0; j <= size[u]; ++ j) {
                minimum[u][j][c] = MAX;
            }
        } else {
            static short result[2][N + 1];
            result[0][0] = result[0][1] = MAX;
            update(result[0][1], color[u] ^ 1);
            if (u != c) {
                update(result[0][0], 0);
            }
            int now = 0;
            int total = 1;
            foreach (iter, tree[u]) {
                int v = *iter;
                if (v != p) {
                    for (int j = 0; j <= total + size[v]; ++ j) {
                        result[now ^ 1][j] = MAX;
                    }
                    bool contains = distance[0][c] == distance[0][v] + distance[v][c];
                    for (int j = 0; j <= total; ++ j) {
                        for (int k = 0; k <= size[v]; ++ k) {
                            update(result[now ^ 1][j + k], result[now][j] + minimum[v][k][c]);
                            if (!contains) {
                                update(result[now ^ 1][j + k], result[now][j] + minimum[v][k][n]);
                            }
                        }
                    }
                    now ^= 1;
                    total += size[v];
                }
            }
            assert(total == size[u]);
            for (int j = 0; j <= total; ++ j) {
                minimum[u][j][c] = result[now][j];
            }
        }
    }
    for (int j = 0; j <= size[u]; ++ j) {
        minimum[u][j][n] = MAX;
        for (int c = 0; c < n; ++ c) {
            if (distance[0][c] == distance[0][u] + distance[u][c]) {
                update(minimum[u][j][n], minimum[u][j][c]);
            }
        }
    }
}

int main() {
#ifndef ONLINE_JUDGE
    freopen("E.in", "r", stdin);
#endif
    scanf("%d%d", &n, &limit);
    int m = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", color + i);
        m += color[i];
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            distance[i][j] = i == j ? 0 : INF;
        }
    }
    for (int i = 0; i < n - 1; ++ i) {
        static int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
        distance[a][b] = distance[b][a] = c;
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                distance[i][j] = std::min(distance[i][j], distance[i][k] + distance[k][j]);
            }
        }
    }
    go(-1, 0);
    short result = minimum[0][m][n];
    printf("%d\n", static_cast <int>(result == MAX ? -1 : result));
    return 0;
}
