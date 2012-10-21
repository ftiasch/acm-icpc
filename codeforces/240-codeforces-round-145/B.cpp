#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 200 + 1;
const int M = 40000 + 1;

const int INF = 1000000000;

int n, a, b, height[N], minimum[2][M][2];

void update(int &x, int a) {
    x = min(x, a);
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d%d", &n, &a, &b);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", height + i);
    }
    height[n] = 0;
    for (int j = 0; j <= a; ++ j) {
        minimum[n & 1][j][0] = minimum[n & 1][j][1] = INF;
    }
    minimum[n & 1][0][0] = minimum[n & 1][0][1] = 0;
    int sum = 0;
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = 0; j <= a; ++ j) {
            minimum[i & 1][j][0] = minimum[i & 1][j][1] = INF;
        }
        for (int j = 0; j <= a; ++ j) {
            int k = max(sum - j, 0);
            for (int x = 0; x < 2; ++ x) {
                for (int y = 0; y < 2; ++ y) {
                    if ((x ? k : j) + height[i] <= (x ? b : a)) {
                        update(minimum[i & 1][j + (x ? 0 : height[i])][x], minimum[(i + 1) & 1][j][y] + (x != y ? min(height[i], height[i + 1]) : 0));
                    }
                }
            }
        }
        sum += height[i];
    }
    int result = INF;
    for (int i = 0; i <= a; ++ i) {
        update(result, min(minimum[0][i][0], minimum[0][i][1]));
    }
    printf("%d\n", result < INF ? result : -1);
    return 0;
}
