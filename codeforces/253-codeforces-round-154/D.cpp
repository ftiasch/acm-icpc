// Codeforces Round #154
// Problem D -- Table with Letters - 2
#include <cstdio>
#include <cstring>
#include <iostream>

const int N = 400;

int n, m, k, sum[N + 1][N + 1], count[26];
char map[N][N + 1];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    memset(sum, 0, sizeof(sum));
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = m - 1; j >= 0; -- j) {
            sum[i][j] = sum[i][j + 1] + sum[i + 1][j] - sum[i + 1][j + 1] + (map[i][j] == 'a');
        }
    }
    long long answer = 0;
    for (int up = 0; up < n; ++ up) {
        for (int down = up + 1; down < n; ++ down) {
            memset(count, 0, sizeof(count));
            {
                int i, j;
                for (i = j = m - 1; i >= 0; -- i) {
                    if (map[up][i] == map[down][i]) {
                        while (j > i && sum[up][i] - sum[down + 1][i] - sum[up][j + 1] + sum[down + 1][j + 1] > k) {
                            if (map[up][j] == map[down][j]) {
                                count[map[up][j] - 'a'] --;
                            }
                            j --;
                        }
                        if (map[up][i] == map[down][i]) {
                            answer += count[map[up][i] - 'a'] ++;
                        }
                    }
                }
            }
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
