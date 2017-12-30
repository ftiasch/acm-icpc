#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 50;
const int DELTA_X[] = {-1, 0, 0, 1};
const int DELTA_Y[] = {0, -1, 1, 0};

int n, m;
bool ok[N + 2][N + 2];
char ins[101], buffer[N + 1];

int main()
{
#ifdef LOCAL_JUDGE
    freopen("B.in", "r", stdin);
#endif
    memset(ok, 0, sizeof(ok));
    scanf("%d%d", &n, &m);
    int sx, sy, tx, ty;
    for (int i = 1; i <= n; ++ i) {
        scanf("%s", buffer);
        for (int j = 1; j <= m; ++ j) {
            char c = buffer[j - 1];
            ok[i][j] = c != '#';
            if (c == 'S') {
                sx = i, sy = j;
            }
            if (c == 'E') {
                tx = i, ty = j;
            }
        }
    }
    scanf("%s", ins);
    int perm[] = {0, 1, 2, 3};
    int result = 0;
    do {
        int x = sx, y = sy;
        for (int i = 0; ins[i]; ++ i) {
            int d = perm[ins[i] - '0'];
            x += DELTA_X[d];
            y += DELTA_Y[d];
            if (x == tx && y == ty) {
                result ++;
                break;
            }
            if (!ok[x][y]) {
                break;
            }
        }
    } while (std::next_permutation(perm, perm + 4));
    printf("%d\n", result);
}
