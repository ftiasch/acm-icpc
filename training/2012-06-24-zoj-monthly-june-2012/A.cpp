// Problem A -- Ice Valley  
#include <cstdio>
#include <cstring>
#include <string>
using namespace std;

const int N = 555;
const int V = 222;

const int INF = 1000000000;
const string DIRECTIONS = "UDLR";
const int DELTA[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

int n, m, id[N][N], next[N][N], cost[N][N], degree[N][N], queue[N * N][2], minimum[V][1 << 10], to[V][4], length[V][4], x_1, y_1, x_2, y_2;
bool visit[V];
char map[N][N + 1];

void find(int i, int j) {
    if (next[i][j] == -1) {
        int k = DIRECTIONS.find(map[i][j]);
        int x = i + DELTA[k][0];
        int y = j + DELTA[k][1];
        if (0 <= x && x < n && 0 <= y && y < m && map[x][y] != 'W' && map[x][y] != '#') {
            find(x, y);
            next[i][j] = next[x][y];
            cost[i][j] = cost[x][y] + 1;
        } else {
            cost[x][y] = INF;
        }
    }
}

int count_one(int mask) {
    int result = 0;
    while (mask > 0) {
        result ++;
        mask ^= mask & -mask;
    }
    return result;
}

int main() {
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            scanf("%s", map[i]);
        }
        scanf("%d%d%d%d", &x_1, &y_1, &x_2, &y_2);
        int treasure_count = 0;
        memset(id, -1, sizeof(id));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (map[i][j] == '$') {
                    id[i][j] = treasure_count ++;
                }
            }
        }
        int nothing_count = treasure_count;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (map[i][j] == '0') {
                    id[i][j] = nothing_count ++;
                }
            }
        }
//for (int i = 0; i < n; ++ i) {
//    for (int j = 0; j < m; ++ j) {
//        printf("%4d", id[i][j]);
//    }
//    puts("");
//}
        memset(degree, 0, sizeof(degree));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int k = DIRECTIONS.find(map[i][j]);
                if (k != -1) {
                    int x = i + DELTA[k][0];
                    int y = j + DELTA[k][1];
                    if (0 <= x && x < n && 0 <= y && y < m) {
                        degree[x][y] ++;
                    }
                }
            }
        }
        int head = 0;
        int tail = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int k = DIRECTIONS.find(map[i][j]);
                if (k != -1 && degree[i][j] == 0) {
                    queue[tail][0] = i;
                    queue[tail][1] = j;
                    tail ++;
                }
            }
        }
        while (head != tail) {
            int i = queue[head][0];
            int j = queue[head][1];
            head ++;
            int k = DIRECTIONS.find(map[i][j]);
            int x = i + DELTA[k][0];
            int y = j + DELTA[k][1];
            if (0 <= x && x < n && 0 <= y && y < m && (int)DIRECTIONS.find(map[x][y]) != -1) {
                degree[x][y] --;
                if (degree[x][y] == 0) {
                    queue[tail][0] = x;
                    queue[tail][1] = y;
                    tail ++;
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if ((int)DIRECTIONS.find(map[i][j]) != -1 && degree[i][j] != 0) {
                    map[i][j] = '#';
                }
            }
        }
        memset(next, -1, sizeof(next));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (id[i][j] != -1) {
                    next[i][j] = id[i][j];
                    cost[i][j] = 0;
                }
                if (map[i][j] == 'W' || map[i][j] == '#') {
                    cost[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if ((int)DIRECTIONS.find(map[i][j]) != -1) {
                    find(i, j);
                }
            }
        }
        memset(to, -1, sizeof(to));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (id[i][j] != -1) {
                    for (int k = 0; k < 4; ++ k) {
                        int x = i + DELTA[k][0];
                        int y = j + DELTA[k][1];
                        if (0 <= x && x < n && 0 <= y && y < m && cost[x][y] != INF) {
                            to[id[i][j]][k] = next[x][y];
                            length[id[i][j]][k] = cost[x][y] + 1;
                        }
                    }
                }
            }
        }
//for (int i = 0; i < n; ++ i) {
//    for (int j = 0; j < m; ++ j) {
//        printf("(%10d, %10d)", next[i][j], cost[i][j]);
//    }
//    puts("");
//}
        for (int i = 0; i < nothing_count; ++ i) {
            for (int j = 0; j < (1 << treasure_count); ++ j) {
                minimum[i][j] = INF;
            }
        }
        minimum[id[x_1 - 1][y_1 - 1]][0] = 0;
        for (int mask = 0; mask < (1 << treasure_count); ++ mask) {
            int head = 0;
            int tail = 0;
            for (int i = 0; i < nothing_count; ++ i) {
                visit[i] = true;
                queue[tail ++][0] = i;
            }
            while (head != tail) {
                int i = queue[head][0];
                visit[i] = false;
                head = (head + 1) % V;
                for (int k = 0; k < 4; ++ k) {
                    if (to[i][k] != -1 && minimum[to[i][k]][mask] > minimum[i][mask] + length[i][k]) {
                        minimum[to[i][k]][mask] = minimum[i][mask] + length[i][k];
                        if (!visit[to[i][k]]) {
                            visit[to[i][k]] = true;
                            queue[tail][0] = to[i][k];
                            tail = (tail + 1) % V;
                        }
                    }
                }
            }
            for (int i = 0; i < nothing_count; ++ i) {
                if (i < treasure_count && (mask >> i & 1) == 0 && minimum[i][mask | (1 << i)] > minimum[i][mask] + 2) {
                    minimum[i][mask | (1 << i)] = minimum[i][mask] + 2;
                }
            }
        }
        int target = id[x_2 - 1][y_2 - 1];
        int maximum_treasure = -1;
        int minimum_time = INF;
        for (int mask = 0; mask < (1 << treasure_count); ++ mask) {
            if (minimum[target][mask] < INF) {
                if (count_one(mask) > maximum_treasure) {
                    maximum_treasure = count_one(mask);
                    minimum_time = minimum[target][mask];
                }
                if (count_one(mask) == maximum_treasure) {
                    minimum_time = min(minimum_time, minimum[target][mask]);
                }
            }
        }
        if (maximum_treasure == -1) {
            puts("-1");
        } else {
            printf("%d\n", minimum_time);
        }
    }
    return 0;
}
