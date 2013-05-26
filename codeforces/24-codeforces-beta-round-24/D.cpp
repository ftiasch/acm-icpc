#include <cstdio>
#include <cstring>

const int N = 1000;

int n, m, x, y;
double expected[N][N];

int main() {
    scanf("%d%d", &n, &m);
    memset(expected, 0, sizeof(expected));
    for (int i = n - 2; i >= 0; -- i) {
        for (int _ = 0; _ < 50; ++ _) {
            for (int j = 0; j < m; ++ j) {
                int count = 2;
                expected[i][j] += expected[i + 1][j];
                if (j > 0) {
                    count ++;
                    expected[i][j] += expected[i][j - 1];
                }
                if (j + 1 < m) {
                    count ++;
                    expected[i][j] += expected[i][j + 1];
                }
                expected[i][j] /= count;
                expected[i][j] += 1.0;
            }
        }
    }
    scanf("%d%d", &x, &y);
    printf("%.10f\n", expected[x - 1][y - 1]);
    return 0;
}
