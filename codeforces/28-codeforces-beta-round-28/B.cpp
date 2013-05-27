// Codeforces Beta Round #27 
// Problem B -- Tournament
#include <cstdio>
#include <cstring>

const int N = 50;

int n;
bool exists[N][N], reachable[N][N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n * (n - 1) / 2 - 1; ++ i) {
        int x, y;
        scanf("%d%d", &x, &y);
        x --, y --;
        exists[x][y] = exists[y][x] = reachable[x][y] = true;
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                reachable[i][j] |= reachable[i][k] && reachable[k][j];
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (i != j && !exists[i][j] && !reachable[j][i]) {
                printf("%d %d\n", 1 + i, 1 + j);
                return 0;
            }
        }
    }
    return 0;
}
