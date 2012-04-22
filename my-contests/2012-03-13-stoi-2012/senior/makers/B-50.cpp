#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1111;
const int MOD = 1000000007;

int n, m, k, x, y, ways[2][N][N];

void increase(int &x, int a) {
    x += a;
    x %= MOD;
}

const int DELTA_X[4] = {0, 0, -1, 1};
const int DELTA_Y[4] = {-1, 1, 0, 0};

int main() {
    scanf("%d%d%d%d%d", &n, &m, &k, &x, &y);
    memset(ways, 0, sizeof(ways));
    ways[0][x][y] = 1;
    for (int i = 0; i < k; ++ i) {
        memset(ways[(i + 1) & 1], 0, sizeof(ways[(i + 1) & 1]));
        for (int x = 1; x <= n; ++ x) {
            for (int y = 1; y <= m; ++ y) {
                for (int j = 0; j < 4; ++ j) {
                    int xx = x + DELTA_X[j];
                    int yy = y + DELTA_Y[j];
                    if (1 <= xx && xx <= n && 1 <= yy && yy <= m) {
                        increase(ways[(i + 1) & 1][xx][yy], ways[i & 1][x][y]);
                    }
                }
            }
        }
    }
    int result = 0;
    for (int x = 1; x <= n; ++ x) {
        for (int y = 1; y <= m; ++ y) {
            increase(result, ways[k & 1][x][y]);
        }
    }
    printf("%d\n", result);
    return 0;
}
