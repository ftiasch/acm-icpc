// Codeforces Round #180
// Problem D -- Color the Carpet
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 1000;

int n, m, k, mat[N][N];
char vtc[N][N], hrz[N][N];

int main() {
    scanf("%d%d%d", &n, &m, &k);
    int diff = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%s", hrz[i]);
        for (int j = 0; j < m - 1; ++ j) {
            diff += hrz[i][j] == 'N';
        }
        if (i < n - 1) {
            scanf("%s", vtc[i]);
            for (int j = 0; j < m; ++ j) {
                diff += vtc[i][j] == 'N';
            }
        }
    }
    bool fliped = n > m;
    if (fliped) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                std::swap(vtc[i][j], hrz[j][i]);
            }
        }
        std::swap(n, m);
    }
    if (k == 1) {
        if (diff * 4 > (n * (m - 1) + m * (n - 1))) {
            puts("NO");
            return 0;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                mat[i][j] = 0;
            }
        }
    } else {
        for (int i = 0; i < n; ++ i) {
            mat[i][0] = 0;
            for (int j = 1; j < m; ++ j) {
                mat[i][j] = mat[i][j - 1] ^ (hrz[i][j - 1] == 'N');
            }
            if (i) {
                int diff = 0;
                for (int j = 0; j < m; ++ j) {
                    diff += (mat[i][j] == mat[i - 1][j]) ^ (vtc[i - 1][j] == 'E');
                }
                if (diff > m - diff) {
                    for (int j = 0; j < m; ++ j) {
                        mat[i][j] ^= 1;
                    }
                }
            }
        }
    }
    if (fliped) {
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < i; ++ j) {
                std::swap(mat[i][j], mat[j][i]);
            }
        }
        std::swap(n, m);
    }
    puts("YES");
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            printf("%d%c", 1 + mat[i][j], " \n"[j == m - 1]);
        }
    }
    return 0;
}
