// Codeforces Beta Round #26 
// Problem C -- Parquet
#include <cstdio>
#include <cstring>

const int N = 100;

int n, m, a, b, c;
char config[N][N + 1];

int main() {
    scanf("%d%d%d%d%d", &n, &m, &a, &b, &c);
    if (n % 2 == 1 && m % 2 == 1) {
        puts("IMPOSSIBLE");
    } else {
        int backup_n = n;
        memset(config, 0, sizeof(config));
        if (n % 2 == 1) {
            a -= m / 2;
            n --;
            for (int j = 0; j < m; ++ j) {
                config[n][j] = 'z' - (j / 2) % 2;
            }
        }
        if (m % 2 == 1) {
            b -= n / 2;
            m --;
            for (int i = 0; i < n; ++ i) {
                config[i][m] = 'z' - (i / 2) % 2;
            }
        }
        if (a < 0 || b < 0 || a / 2 + b / 2 + c < n * m / 4) {
            puts("IMPOSSIBLE");
        } else {
            for (int i = 0; i < n; i += 2) {
                for (int j = 0; j < m; j += 2) {
                    int color = ((i >> 1) + (j >> 1) & 1) << 1;
                    if (a >= 2) {
                        a -= 2;
                        config[i][j] = config[i][j + 1] = 'a' + (color ^ 0);
                        config[i + 1][j] = config[i + 1][j + 1] = 'a' + (color ^ 1);
                    } else if (b >= 2) {
                        b -= 2;
                        config[i][j] = config[i + 1][j] = 'a' + (color ^ 0);
                        config[i][j + 1] = config[i + 1][j + 1] = 'a' + (color ^ 1);
                    } else if (c >= 1) {
                        c -= 1;
                        config[i][j] = config[i + 1][j] = config[i][j + 1] = config[i + 1][j + 1] = 'a' + color;
                    }
                }
            }
            for (int i = 0; i < backup_n; ++ i) {
                puts(config[i]);
            }
        }
    }
    return 0;
}
