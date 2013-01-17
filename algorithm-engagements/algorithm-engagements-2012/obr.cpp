// Algorithm engagements 2012
// Round 0 -- Picture
#include <cstdio>

const int N = 1000;

int n, m;
char map[N][N + 1];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    for (int i = 0; i < m; ++ i) {
        for (int j = 0; j < n; ++ j) {
            putchar(map[n - 1 - j][i]);
        }
        puts("");
    }
    return 0;
}
