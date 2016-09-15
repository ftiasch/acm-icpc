#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 1000;
const int M = 10;

int a[N + 1][N + 1], down[N], LOG[N];
short max[M][N][M][N];

int query(int x1, int x2, int y1, int y2)
{
    int k = LOG[x2 - x1 - 1];
    int l = LOG[y2 - y1 - 1];
    int result[] = {max[k][x1][l][y1], max[k][x2 - (1 << k)][l][y1], max[k][x1][l][y2 - (1 << l)], max[k][x2 - (1 << k)][l][y2 - (1 << l)]};
    return *std::max_element(result, result + 4);
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("D.in", "r", stdin);
#endif
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", a[i] + j);
        }
    }
    memset(down, 0, sizeof(*down) * m);
    for (int i = n - 1; i >= 0; -- i) {
        int right = 0;
        for (int j = m - 1; j >= 0; -- j) {
            if (a[i][j]) {
                down[j] ++;
                right ++;
                a[i][j] = std::min(a[i + 1][j + 1] + 1, std::min(down[j], right));
            } else {
                down[j] = right = 0;
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            max[0][i][0][j] = a[i][j];
        }
        for (int k = 1; 1 << k <= m; ++ k) {
            for (int j = 0; j + (1 << k) <= m; ++ j) {
                max[0][i][k][j] = std::max(max[0][i][k - 1][j], max[0][i][k - 1][j + (1 << k - 1)]);
            }
        }
    }
    LOG[0] = 0;
    for (int i = 1; i < std::max(n, m); ++ i) {
        LOG[i] = LOG[i - 1];
        if (1 << LOG[i] + 1 < i + 1) {
            LOG[i] ++;
        }
    }
    for (int k = 1; 1 << k <= n; ++ k) {
        for (int i = n - (1 << k); i >= 0; -- i) {
            for (int l = 0; 1 << l <= m; ++ l) {
                for (int j = 0; j + (1 << l) <= m; ++ j) {
                    max[k][i][l][j] = std::max(max[k - 1][i][l][j], max[k - 1][i + (1 << k - 1)][l][j]);
                }
            }
        }
    }
    int q;
    scanf("%d", &q);
    for (int i = 0; i < q; ++ i) {
        int x1, y1, x2, y2;
        scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
        x1 --;
        y1 --;
        int low = 0;
        int high = std::min(x2 - x1, y2 - y1);
        while (low < high) {
            int middle = low + high + 1 >> 1;
            if (query(x1, x2 - middle + 1, y1, y2 - middle + 1) >= middle) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        printf("%d\n", low);
    }
}
