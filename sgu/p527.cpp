// SGU 527 -- Explode 'Em All
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 25;

int n, m;
char map[N][N + 1];

int row[N], answer, one[1 << 16];

void dfs(int i, int count, int mask) {
    if (count >= answer) {
        return;
    }
    if (i >= n) {
        answer = std::min(answer, std::max(count, one[mask >> 16] + one[mask & 65535]));
    } else {
        dfs(i + 1, count + 1, mask);
        dfs(i + 1, count, mask | row[i]);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
        for (int j = 0; j < m; ++ j) {
            if (map[i][j] == '*') {
                row[i] |= 1 << j;
            }
        }
    }
    answer = INT_MAX;
    one[0] = 0;
    for (int i = 1; i < 1 << 16; ++ i) {
        one[i] = one[i >> 1] + (i & 1);
    }
    dfs(0, 0, 0);
    printf("%d\n", answer);
    return 0;
}
