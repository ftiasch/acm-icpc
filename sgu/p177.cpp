// SGU 177 -- Square
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1002;
const int M = 5000;

int n, m, x[M][2], y[M][2], p[N][N];
char c[M];

int find(int i, int j) {
    if (p[i][j] != j) {
        p[i][j] = find(i, p[i][j]);
    }
    return p[i][j];
}

int paint(int x_1, int x_2, int y_1, int y_2) {
    int result = 0;
    for (int i = x_1; i <= x_2; ++ i) {
        int j = find(i, y_1);
        while (j <= y_2) {
            result ++;
            p[i][j] = j + 1;
            j = find(i, j);
        }
    }
    return result;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        char buffer[22];
        scanf("%d%d%d%d%s", &x[i][0], &y[i][0], &x[i][1], &y[i][1], buffer);
        c[i] = *buffer;
        if (x[i][0] > x[i][1]) {
            swap(x[i][0], x[i][1]);
        }
        if (y[i][0] > y[i][1]) {
            swap(y[i][0], y[i][1]);
        }
    }
    int result = 0;
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= n + 1; ++ j) {
            p[i][j] = j;
        }
    }
    for (int k = m - 1; k >= 0; -- k) {
        int ret = paint(x[k][0], x[k][1], y[k][0], y[k][1]);
        if (c[k] == 'w') {
            result += ret;
        }
    }
    for (int i = 1; i <= n; ++ i) {
        for (int j = 1; j <= n; ++ j) {
            if (p[i][j] == j) {
                result ++;
            }
        }
    }
    printf("%d\n", result);
    return 0;
}
