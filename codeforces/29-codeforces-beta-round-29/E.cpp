// Codeforces Beta Round #29 
// Problem E -- Quarrel
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 500;

int n, m;
bool graph[N][N];
std::vector <int> adjacent[N];

int distance[N][N][2], back[N][N][2];

int get_id(int x, int y, int z) {
    return (x * n + y) * 2 + z;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0, a, b; i < m; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph[a][b] = graph[b][a] = true;
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            distance[i][j][0] = distance[i][j][1] = INT_MAX;
        }
    }
    distance[0][n - 1][0] = 0;
    std::queue <int> queue;
    queue.push(get_id(0, n - 1, 0));
    while (!queue.empty()) {
        int ret = queue.front();
        queue.pop();
        int position[2] = {ret / 2 / n, ret / 2 % n};
        int z = ret % 2;
        int d = distance[position[0]][position[1]][z];
        int u = position[z];
        foreach (iter, adjacent[u]) {
            int v = *iter;
            if (v != position[z ^ 1]) {
                int new_position[2] = {position[0], position[1]};
                new_position[z] = v;
                if (d + 1 < distance[new_position[0]][new_position[1]][z ^ 1]) {
                    distance[new_position[0]][new_position[1]][z ^ 1] = d + 1;
                    back[new_position[0]][new_position[1]][z ^ 1] = get_id(position[0], position[1], z);
                    queue.push(get_id(new_position[0], new_position[1], z ^ 1));
                }
            }
        }
        if (z == 0 && graph[position[0]][position[1]] && d + 2 < distance[position[1]][position[0]][0]) {
            distance[position[1]][position[0]][0] = d + 2;
            back[position[1]][position[0]][0] = get_id(position[0], position[1], 0);
            queue.push(get_id(position[1], position[0], 0));
        }
    }
    if (distance[n - 1][0][0] == INT_MAX) {
        puts("-1");
    } else {
        int answer = distance[n - 1][0][0] >> 1;
        printf("%d\n", answer);
        std::vector <int> a, b;
        a.push_back(n - 1);
        b.push_back(0);
        for (int i = n - 1, j = 0, k = 0; i != 0 || j != n - 1; ) {
            int ret = back[i][j][k];
            if (ret % 2 == k) {
                std::swap(i, j);
                a.push_back(i);
                b.push_back(j);
            } else {
                i = ret / 2 / n;
                j = ret / 2 % n;
                k ^= 1;
                if (k) {
                    b.push_back(j);
                } else {
                    a.push_back(i);
                }
            }
        }
        for (int i = answer; i >= 0; -- i) {
            printf("%d%c", a[i] + 1, i ? ' ' : '\n');
        }
        for (int i = answer; i >= 0; -- i) {
            printf("%d%c", b[i] + 1, i ? ' ' : '\n');
        }
    }
    return 0;
}
