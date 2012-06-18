// Problem I -- Here We Go(relians) Again
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 500;
const int INF = 1000000000;

int s, n, m, graph[N][N], dist[N];
bool visit[N];

int get(int i, int j) {
    return i * (m + 1) + j;
}

int main() {
    while (true) {
        scanf("%d%d", &n, &m);
        if (n == 0 && m == 0) {
            break;
        }
        s = (n + 1) * (m + 1);
        for (int i = 0; i < s; ++ i) {
            for (int j = 0; j < s; ++ j) {
                graph[i][j] = INF;
            }
        }
        char buffer[22];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int length;
                scanf("%d%s", &length, buffer);
                if (length == 0) {
                    continue;
                }
                length = 2520 / length;
                if (*buffer == '>' || *buffer == '*') {
                    graph[get(i, j)][get(i, j + 1)] = length;
                }
                if (*buffer == '<' || *buffer == '*') {
                    graph[get(i, j + 1)][get(i, j)] = length;
                }
            }
            if (i < n) {
                for (int j = 0; j <= m; ++ j) {
                    int length;
                    scanf("%d%s", &length, buffer);
                    if (length == 0) {
                        continue;
                    }
                    length = 2520 / length;
                    if (*buffer == 'v' || *buffer == '*') {
                        graph[get(i, j)][get(i + 1, j)] = length;
                    }
                    if (*buffer == '^' || *buffer == '*') {
                        graph[get(i + 1, j)][get(i, j)] = length;
                    }
                }
            }
        }
        for (int i = 0; i < s; ++ i) {
            dist[i] = INF;
        }
        dist[0] = 0;
        memset(visit, 0, sizeof(visit));
        for (int i = 0; i < s; ++ i) {
            int j = 0;
            while (visit[j]) {
                j ++;
            }
            for (int k = 0; k < s; ++ k) {
                if (!visit[k] && dist[k] < dist[j]) {
                    j = k;
                }
            }
            if (dist[j] == INF) {
                break;
            }
            visit[j] = true;
            for (int k = 0; k < s; ++ k) {
                if (dist[j] + graph[j][k] < dist[k]) {
                    dist[k] = dist[j] + graph[j][k];
                }
            }
        }
        int result = dist[s - 1];
        if (result == INF) {
            puts("Holiday");
        } else {
            printf("%d blips\n", result);
        }
    }

    return 0;
}
