// SGU 409 -- Berland Flag
#include <cstdio>
#include <cstring>

const int N = 20;

int n, k, pattern[N][N];

int main() {
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            pattern[i][j] = i * n + j < k ? '*' : '.';
        }
    }
    for (int i = 0; i < n * n; ++ i) {
        for (int j = 0; j < n * n; ++ j) {
            int x = j / n;
            int y = i / n;
            putchar(pattern[(x + i) % n][(y + j) % n]);
        }
        puts("");
    }
    return 0;
}
