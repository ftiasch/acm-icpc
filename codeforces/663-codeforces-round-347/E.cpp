#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 20;
const int M = 100000;

char buffer[M + 1];
int n, m, masks[M], count[1 << N][N + 1];

int main()
{
    scanf("%d%d", &n, &m);
    memset(masks, 0, sizeof(masks));
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        for (int j = 0; j < m; ++ j) {
            masks[j] |= buffer[j] - '0' << i;
        }
    }
    memset(count, 0, sizeof(count));
    for (int i = 0; i < m; ++ i) {
        count[masks[i]][0] ++;
    }
    for (int i = 0; i < n; ++ i) {
        for (int c = i; c >= 0; -- c) {
            for (int mask = 0; mask < 1 << n; ++ mask) {
                count[mask ^ (1 << i)][c + 1] += count[mask][c];
            }
        }
    }
    int result = n * m;
    for (int mask = 0; mask < 1 << n; ++ mask) {
        int tmp = 0;
        for (int j = 0; j <= n; ++ j) {
            tmp += std::min(j, n - j) * count[mask][j];
        }
        result = std::min(result, tmp);
    }
    printf("%d\n", result);
    return 0;
}
