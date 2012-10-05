// Codeforces Round #139
// Problem C -- Barcode
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1000;
const int INF = 1000000000;

int n, m, x, y, cost[N][2], sum[N][2], minimum[N + 1][2];

int main() {
    scanf("%d%d%d%d", &n, &m, &x, &y);
    memset(cost, 0, sizeof(cost));
    for (int i = 0; i < n; ++ i) {
        char buffer[N + 1];
        scanf("%s", buffer);
        for (int j = 0; j < m; ++ j) {
            cost[j][buffer[j] == '#'] ++;
        }
    }
    memset(sum, 0, sizeof(sum));
    for (int c = 0; c < 2; ++ c) {
        for (int i = m - 1; i >= 0; -- i) {
            sum[i][c] = sum[i + 1][c] + cost[i][c];
        }
    }
    minimum[m][0] = minimum[m][1] = 0;
    for (int i = m - 1; i >= 0; -- i) {
        for (int c = 0; c < 2; ++ c) {
            minimum[i][c] = INF;
            for (int j = i + x; j <= m && j <= i + y; ++ j) {
                minimum[i][c] = min(minimum[i][c], minimum[j][c ^ 1] - sum[j][c] + sum[i][c]);
            }
        }
    }
    printf("%d\n", min(minimum[0][0], minimum[0][1]));
    return 0;
}
