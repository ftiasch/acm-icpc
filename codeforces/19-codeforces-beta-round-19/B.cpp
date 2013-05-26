// Codeforces Beta Round #19
// Problem B -- Checkout Assistant
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 2000;
const long long INF = 1000000000LL * N;

int n;
long long minimum[2][2 * N + 1];

void update(long long &x, long long a) {
    x = std::min(x, a);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i <= 2 * n; ++ i) {
        minimum[0][i] = INF;
    }
    minimum[0][n] = 0;
    for (int i = 0; i < n; ++ i) {
        int t, c;
        scanf("%d%d", &t, &c);
        for (int j = 0; j <= 2 * n; ++ j) {
            minimum[i + 1 & 1][j] = INF;
        }
        for (int j = 0; j <= 2 * n; ++ j) {
            update(minimum[i + 1 & 1][std::min(j + t, 2 * n)], minimum[i & 1][j] + c);
            if (j) {
                update(minimum[i + 1 & 1][j - 1], minimum[i & 1][j]);
            }
        }
    }
    long long answer = INF;
    for (int j = n; j <= 2 * n; ++ j) {
        answer = std::min(answer, minimum[n & 1][j]);
    }
    std::cout << answer << std::endl;
    return 0;
}
