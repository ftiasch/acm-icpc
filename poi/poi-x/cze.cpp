// POI X Stage I -- Chocolate(cze)
#include <cstdio>
#include <cstring>
#include <climits>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, m, a[N], b[N], f[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n - 1; ++ i) {
        scanf("%d", a + i);
    }
    sort(a, a + n, greater <int>());
    for (int j = 0; j < m - 1; ++ j) {
        scanf("%d", b + j);
    }
    sort(b, b + m, greater <int>());
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = m - 1; j >= 0; -- j) {
            if (i == n - 1 && j == m - 1) {
                f[i][j] = 0;
            } else {
                f[i][j] = INT_MAX;
                if (i < n - 1) {
                    f[i][j] = min(f[i][j], f[i + 1][j] + a[i] * (j + 1));
                }
                if (j < m - 1) {
                    f[i][j] = min(f[i][j], f[i][j + 1] + b[j] * (i + 1));
                }
            }
        }
    }
    printf("%d\n", f[0][0]);
    return 0;
}
