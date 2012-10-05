// Codeforces Round #142
// Problem C -- Shifts
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;
const int M = 10000;

int n, m, need[N][M];
char map[N][M + 1];

void update(int &x, int a) {
    x = min(x, a);
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            need[i][j] = map[i][j] == '1'? 0: M;
        }
        for (int j = 0; j < m << 1; ++ j) {
            update(need[i][(j + 1) % m], need[i][j % m] + 1);
        }
        for (int j = (m << 1) - 1; j >= 0; -- j) {
            update(need[i][(j + m - 1) % m], need[i][j % m] + 1);
        }
    }
    int result = N * M;
    for (int j = 0; j < m; ++ j) {
        bool valid = true;
        int ret = 0;
        for (int i = 0; i < n; ++ i) {
            valid &= need[i][j] < M;
            ret += need[i][j];
        }
        if (valid) {
            update(result, ret);
        }
    }
    if (result == N * M) {
        result = -1;
    }
    printf("%d\n", result);
    return 0;
}
