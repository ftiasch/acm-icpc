// SGU 536 -- Berland Chess
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 15;

int n, m, k, id[N][N], minimum[1 << N][N][N];
bool valid[1 << N][N][N];
char map[N][N + 1];

const int MOVE[2][4][2] = {{{-1, 0}, {0, -1}, {0, 1}, {1, 0}}, {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}};
const int KNIGHT[8][2] = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
const int KING[8][2] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

struct Node {
    int x, y, mask;

    Node(int x = 0, int y = 0, int mask = 0): x(x), y(y), mask(mask) {}
};

Node queue[(1 << N) * N * N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    int x_0, y_0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (map[i][j] == '*') {
                x_0 = i;
                y_0 = j;
                map[i][j] = '.';
            }
        }
    }
    memset(id, -1, sizeof(id));
    k = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (map[i][j] != '.') {
                id[i][j] = k ++;
            }
        }
    }
#define MARK(x, y) if (0 <= (x) && (x) < n && 0 <= (y) && (y) < m) valid[mask][x][y] = false;
    memset(valid, true, sizeof(valid));
    for (int mask = 0; mask < 1 << k; ++ mask) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (id[i][j] != -1 && (mask >> id[i][j] & 1)) {
                    if (map[i][j] == 'K') {
                        for (int k = 0; k < 8; ++ k) {
                            MARK(i + KNIGHT[k][0], j + KNIGHT[k][1]);
                        }
                    } else {
                        int t = map[i][j] == 'B';
                        for (int k = 0; k < 4; ++ k) {
                            int x = i + MOVE[t][k][0];
                            int y = j + MOVE[t][k][1];
                            while (0 <= x && x < n && 0 <= y && y < m) {
                                valid[mask][x][y] = false;
                                if (map[x][y] != '.') {
                                    break;
                                }
                                x += MOVE[t][k][0];
                                y += MOVE[t][k][1];
                            }
                        }
                    }
                }
            }
        }
    }
    for (int mask = 0; mask < 1 << k; ++ mask) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                minimum[mask][i][j] = INT_MAX;
            }
        }
    }
    int head = 0;
    int tail = 0;
    queue[tail ++] = Node(x_0, y_0, (1 << k) - 1);
    minimum[(1 << k) - 1][x_0][y_0] = 0;
    while (head != tail) {
        Node ret = queue[head ++];
        for (int k = 0; k < 8; ++ k) {
            int x = ret.x + KING[k][0];
            int y = ret.y + KING[k][1];
            if (0 <= x && x < n && 0 <= y && y < m && valid[ret.mask][x][y]) {
                int mask = ret.mask;
                if (id[x][y] != -1) {
                    mask &= ~(1 << id[x][y]);
                }
                if (minimum[mask][x][y] == INT_MAX) {
                    minimum[mask][x][y] = minimum[ret.mask][ret.x][ret.y] + 1;
                    queue[tail ++] = Node(x, y, mask);
                }
            }
        }
    }
    int result = INT_MAX;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            result = min(result, minimum[0][i][j]);
        }
    }
    printf("%d\n", result == INT_MAX? -1: result);
    return 0;
}
