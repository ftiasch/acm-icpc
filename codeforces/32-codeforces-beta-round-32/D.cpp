// Codeforces Beta Round #32
// Problem D -- Constellation
#include <cstdio>
#include <cstring>

const int N = 300;

int n, m, k;
char buffer[N + 1];
bool map[N][N];

int main() {
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        for (int j = 0; j < m; ++ j) {
            map[i][j] = buffer[j] == '*';
        }
    }
    for (int r = 1; 2 * r < n && 2 * r < m; ++ r) {
        for (int i = r; i + r < n; ++ i) {
            for (int j = r; j + r < m; ++ j) {
                if (map[i][j] && map[i - r][j] && map[i + r][j] && map[i][j - r] && map[i][j + r]) {
                    k --;
                    if (k == 0) {
                        i ++, j ++;
                        printf("%d %d\n", i, j);
                        printf("%d %d\n", i - r, j);
                        printf("%d %d\n", i + r, j);
                        printf("%d %d\n", i, j - r);
                        printf("%d %d\n", i, j + r);
                        return 0;
                    }
                }
            }
        }
    }
    puts("-1");
    return 0;
}

