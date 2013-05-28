// Codeforces Beta Round #31
// Problem D -- Chocolate
#include <cstdio>
#include <cstring> 
#include <vector>
#include <algorithm>

const int N = 201;

const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int w, h, n;
bool block[N][N];

int count;

void dfs(int x, int y) {
    if (block[x][y]) {
        return;
    }
    count ++;
    block[x][y] = true;
    for (int k = 0; k < 4; ++ k) {
        int xx = x + DELTA[k][0];
        int yy = y + DELTA[k][1];
        if (!block[xx][yy]) {
            dfs(xx + DELTA[k][0], yy + DELTA[k][1]);
        }
    }
}

int main() {
    scanf("%d%d%d", &w, &h, &n);
    while (n --) {
        int x_1, y_1, x_2, y_2;
        scanf("%d%d%d%d", &x_1, &y_1, &x_2, &y_2);
        for (int i = x_1 * 2; i <= x_2 * 2; ++ i) {
            for (int j = y_1 * 2; j <= y_2 * 2; ++ j) {
                block[i][j] = true;
            }
        }
    }
    for (int i = 0; i <= 2 * w; ++ i) {
        block[i][0] = block[i][2 * h] = true;
    }
    for (int j = 0; j <= 2 * h; ++ j) {
        block[0][j] = block[2 * w][j] = true;
    }
    std::vector <int> areas;
    for (int i = 1; i <= 2 * w; i += 2) {
        for (int j = 1; j <= 2 * h; j += 2) {
            if (!block[i][j]) {
                count = 0;
                dfs(i, j);
                areas.push_back(count);
            }
        }
    }
    std::sort(areas.begin(), areas.end());
    for (int i = 0; i < (int)areas.size(); ++ i) {
        printf("%d%c", areas[i], " \n"[i == (int)areas.size() - 1]);
    }
    return 0;
}
