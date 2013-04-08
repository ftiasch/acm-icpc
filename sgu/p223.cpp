// SGU 223 -- Little Kings
#include <cstdio>
#include <cstring>
#include <iostream>

const int N = 10;
typedef long long LL;

int n, m;
LL ways[2][1 << N + 1][N * N + 1];

int main() {
    scanf("%d%d", &n, &m);
    int all = (1 << n + 1) - 1;
    int total = 0;
    memset(ways, 0, sizeof(ways));
    ways[0][0][0] = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            memset(ways[total ^ 1], 0, sizeof(ways[total ^ 1]));
            for (int mask = 0; mask < 1 << n + 1; ++ mask) {
                for (int k = 0; k <= m; ++ k) {
                    ways[total ^ 1][mask << 1 & all][k] += ways[total][mask][k];
                    if (k == m) {
                        continue;
                    }
                    bool valid = true;
                    valid &= !j || !(mask & 1);
                    if (i) {
                        valid &= !j || !(mask >> n & 1);
                        valid &= !(mask >> n - 1 & 1);
                        valid &= j == n - 1 || !(mask >> n - 2 & 1);
                    }
                    if (valid) {
                        ways[total ^ 1][(mask << 1 | 1) & all][k + 1] += ways[total][mask][k];
                    }
                }
            }
            total ^= 1;
        }
    }
    LL answer = 0;
    for (int mask = 0; mask < 1 << n + 1; ++ mask) {
        answer += ways[total][mask][m];
    }
    std::cout << answer << std::endl;
    return 0;
}
