// Codeforces Round #173 
// Problem D -- Yet Another Number Game
#include <cstdio>
#include <cstring>

const char* FIRST = "BitLGM";
const char* SECOND = "BitAryo";

int n, a[3];
bool win[300][300];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    if (n == 1) {
        puts(a[0] ? FIRST : SECOND);
    } else if (n == 2) {
        memset(win, 0, sizeof(win));
        for (int i = 0; i <= a[0]; ++ i) {
            for (int j = 0; j <= a[1]; ++ j) {
                for (int k = 1; k < 300; ++ k) {
                    if (k <= i) {
                        win[i][j] |= !win[i - k][j];
                    }
                    if (k <= j) {
                        win[i][j] |= !win[i][j - k];
                    }
                    if (k <= i && k <= j) {
                        win[i][j] |= !win[i - k][j - k];
                    }
                }
            }
        }
        puts(win[a[0]][a[1]] ? FIRST : SECOND);
    } else {
        puts(a[0] ^ a[1] ^ a[2] ? FIRST : SECOND);
    }
    return 0;
}
