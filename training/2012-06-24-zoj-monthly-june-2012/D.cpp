// Problem D -- Choir
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 333;

int n, m, height[N][N], row_max[N][N], mat_max[N][N], queue[N];
long long sum[N][N][2];

double sqr(double x) {
    return x * x;
}

int main() {
    int test_count = 0;
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                scanf("%d", &height[i][j]);
            }
        }
        memset(sum, 0, sizeof(sum));
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                sum[i][j][0] = sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i + 1][j + 1][0] + height[i][j];
                sum[i][j][1] = sum[i + 1][j][1] + sum[i][j + 1][1] - sum[i + 1][j + 1][1] + height[i][j] * height[i][j];
            }
        }
        printf("Case %d:\n", ++ test_count);
        int query_count;
        scanf("%d", &query_count);
        while (query_count > 0) {
            query_count --;
            int a, b;
            scanf("%d%d", &a, &b);
            for (int i = 0; i < n; ++ i) {
                int head = 0;
                int tail = 0;
                for (int j = m - 1; j >= 0; -- j) {
                    while (head != tail && height[i][j] >= height[i][queue[tail - 1]]) {
                        tail --;
                    }
                    queue[tail ++] = j;
                    while (queue[head] - j >= b) {
                        head ++;
                    }
                    row_max[i][j] = height[i][queue[head]];
                }
            }
            for (int j = 0; j < m; ++ j) {
                int head = 0;
                int tail = 0;
                for (int i = n - 1; i >= 0; -- i) {
                    while (head != tail && row_max[i][j] >= row_max[queue[tail - 1]][j]) {
                        tail --;
                    }
                    queue[tail ++] = i;
                    while (queue[head] - i >= a) {
                        head ++;
                    }
                    mat_max[i][j] = row_max[queue[head]][j];
                }
            }
            int x = -1, y = -1;
            double best = 1e9;
            int size = a * b - 1;
            for (int i = 0; i + a <= n; ++ i) {
                for (int j = 0; j + b <= m; ++ j) {
                    int max = mat_max[i][j];
                    double var = (double)(sum[i][j][1] - sum[i + a][j][1] - sum[i][j + b][1] + sum[i + a][j + b][1] - max * max) / size 
                        - sqr((double)(sum[i][j][0] - sum[i + a][j][0] - sum[i][j + b][0] + sum[i + a][j + b][0] - max) / size);
                    if (var < best) {
                        x = i;
                        y = j;
                        best = var;
                    }
                }
            }
            printf("(%d, %d), %.2f\n", x + 1, y + 1, best);
        }
    }
    return 0;
}
