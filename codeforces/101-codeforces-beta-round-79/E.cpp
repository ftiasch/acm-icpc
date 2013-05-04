// Codeforces Beta Round #79
// Problem E -- Candies and Stones
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 20000;
const long long INF = 1000000000000000000LL;

int n, m, p, x[N], y[N];
long long maximum[2][N];

inline int weight(int i, int j) {
    return (x[i] + y[j]) % p;
}

void construct(int x_0, int y_0, int x_1, int y_1, long long answer) {
    if (x_0 == x_1) {
        for (int y = y_0; y < y_1; ++ y) {
            putchar('S');
        }
    } else {
        int middle = x_0 + x_1 >> 1;
        std::fill(maximum[0] + y_0, maximum[0] + y_1 + 1, -INF);
        maximum[0][y_0] = 0;
        for (int i = x_0; i <= middle; ++ i) {
            maximum[0][y_0] += weight(i, y_0);
            for (int j = y_0 + 1; j <= y_1; ++ j) {
                (maximum[0][j] = std::max(maximum[0][j], maximum[0][j - 1])) += weight(i, j);
            }
        }
        std::fill(maximum[1] + y_0, maximum[1] + y_1 + 1, -INF);
        maximum[1][y_1] = 0;
        for (int i = x_1; i > middle; -- i) {
            maximum[1][y_1] += weight(i, y_1);
            for (int j = y_1 - 1; j >= y_0; -- j) {
                (maximum[1][j] = std::max(maximum[1][j], maximum[1][j + 1])) += weight(i, j);
            }
        }
        for (int j = y_0; j <= y_1; ++ j) {
            if (maximum[0][j] + maximum[1][j] == answer) {
                long long up = maximum[0][j];
                long long down = maximum[1][j];
                construct(x_0, y_0, middle, j, up);
                putchar('C');
                construct(middle + 1, j, x_1, y_1, down);
                return;
            }
        }
    }
}

int main() {
    scanf("%d%d%d", &n, &m, &p);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", x + i);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d", y + i);
    }
    std::fill(maximum[0], maximum[0] + m, -INF);
    maximum[0][0] = 0;
    for (int i = 0; i < n; ++ i) {
        maximum[0][0] += weight(i, 0);
        for (int j = 1; j < m; ++ j) {
            (maximum[0][j] = std::max(maximum[0][j], maximum[0][j - 1])) += weight(i, j);
        }
    }
    std::cout << maximum[0][m - 1] << std::endl;
    construct(0, 0, n - 1, m - 1, maximum[0][m - 1]);
    puts("");
    return 0;
}
