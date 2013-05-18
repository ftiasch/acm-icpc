// Codeforces Round #110
// Problem C -- Cipher
#include <cstdio>
#include <cstring>

const int N = 100;
const int MOD = (int)1e9 + 7;

int ways[N + 1][N * 26 + 1];
char text[N + 1];

int main() {
    memset(ways, 0, sizeof(ways));
    ways[0][0] = 1;
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j <= 26 * i; ++ j) {
            for (int k = 0; k < 26; ++ k) {
                (ways[i + 1][j + k] += ways[i][j]) %= MOD;
            }
        }
    }
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        scanf("%s", text);
        int total = 0;
        for (int i = 0; text[i]; ++ i) {
            total += text[i] - 'a';
        }
        printf("%d\n", ways[strlen(text)][total] - 1);
    }
    return 0;
}
