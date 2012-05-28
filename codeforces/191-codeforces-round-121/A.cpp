// Codeforces Round #121
// Problem A -- Dynasty Puzzles
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

int n, dp[2][26][26];

void update(int &x, int a) {
    x = max(x, a);
}

int main() {
    scanf("%d", &n);
    int pointer = 0;
    for (int i = 0; i < 26; ++ i) {
        for (int j = 0; j < 26; ++ j) {
            dp[pointer][i][j] = INT_MIN;
        }
    }
    for (int i = 0; i < n; ++ i) {
        pointer ^= 1;
        char name[22];
        scanf("%s", name);
        int length = strlen(name);
        int first = name[0] - 'a';
        int second = name[length - 1] - 'a';
        for (int j = 0; j < 26; ++ j) {
            for (int k = 0; k < 26; ++ k) {
                dp[pointer][j][k] = dp[pointer ^ 1][j][k];
            }
        }
        update(dp[pointer][first][second], length);
        for (int j = 0; j < 26; ++ j) {
            update(dp[pointer][j][second], dp[pointer ^ 1][j][first] + length);
        }
    }
    int result = 0;
    for (int i = 0; i < 26; ++ i) {
        result = max(result, dp[pointer][i][i]);
    }
    printf("%d\n", result);
    return 0;
}
