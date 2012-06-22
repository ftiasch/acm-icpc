// SGU 168 -- Matrix
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 1111;

int n, m, a[N << 1][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i <= n + m - 1; ++ i) {
        for (int j = 0; j <= m; ++ j) {
            a[i][j] = INT_MAX;
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &a[i + j][j]);
        }
    }
    for (int i = n + m - 2; i >= 0; -- i) {
        for (int j = m - 1; j >= 0; -- j) {
            a[i][j] = min(a[i][j], min(a[i + 1][j], a[i][j + 1]));
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            printf("%d%c", a[i + j][j], j == m -1? '\n': ' ');
        }
    }
    return 0;
}
