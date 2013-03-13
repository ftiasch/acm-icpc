// TUD Programming Contest 2006
// Relocation
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 10;
const int INF = 1000000000;

int n, c[2], w[N], sum[1 << N], minimum[2][1 << N];

void prepare(int c, int minimum[1 << N]) {
    minimum[0] = 0;
    for (int mask = 1; mask < 1 << n; ++ mask) {
        minimum[mask] = INF;
        for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
            if (sum[sub] <= c) {
                minimum[mask] = std::min(minimum[mask], minimum[mask ^ sub] + 1);
            }
        }
    }
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    for (int t = 1; t <= test_count; ++ t) {
        scanf("%d%d%d", &n, c, c + 1);
        for (int i = 0; i < n; ++ i) {
            scanf("%d", w + i);
        }
        memset(sum, 0, sizeof(sum));
        for (int mask = 0; mask < 1 << n; ++ mask) {
            for (int i = 0; i < n; ++ i) {
                if (mask >> i & 1) {
                    sum[mask] += w[i];
                }
            }
        }
        prepare(c[0], minimum[0]);
        prepare(c[1], minimum[1]);
        int answer = INF;
        for (int mask = 0; mask < 1 << n; ++ mask) {
            answer = std::min(answer, std::max(minimum[0][mask], minimum[1][(1 << n) - 1 ^ mask]));
        }
        printf("Scenario #%d:\n%d\n\n", t, answer);
    }
    return 0;
}
