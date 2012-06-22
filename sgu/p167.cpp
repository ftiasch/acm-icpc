// SGU 167 -- I-country
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 16;
const int INF = 1000000000;

int row_count, colume_count, limit, weight[N][N], sum[N][N], memory[N][2][N][2][N][N * N], choice[N][2][N][2][N][N * N];

int transform(int mode, int value) {
    return mode? -value: value;
}

void update(int &r, int &w, int v, int c) {
    if (v > r) {
        r = v;
        w = c;
    }
}

int solve(int row, int left_mode, int left, int right_mode, int right, int total) {
    if (row >= row_count || left > right) {
        return -INF;
    }
    int &reg = memory[row][left_mode][left][right_mode][right][total];
    int &way = choice[row][left_mode][left][right_mode][right][total];
    if (reg == -1) {
        reg = -INF;
        if (right - left == total) {
            update(reg, way, sum[row][left] - sum[row][right], -1);
        }
        if (left_mode == 0) {
            update(reg, way, solve(row, 1, left, right_mode, right, total), -2);
        }
        if (right_mode == 1) {
            update(reg, way, solve(row, left_mode, left, 0, right, total), -3);
        }
        for (int next_left = 0; next_left < colume_count; ++ next_left) {
            for (int next_right = next_left + 1; next_right <= colume_count; ++ next_right) {
                if (max(left, next_left) < min(right, next_right) && transform(left_mode, left) >= transform(left_mode, next_left) && transform(right_mode, right) >= transform(right_mode, next_right) && right - left <= total) {
                    update(reg, way, solve(row + 1, left_mode, next_left, right_mode, next_right, total - (right - left)) + sum[row][left] - sum[row][right], next_left + next_right * colume_count);
                }
            }
        }
    }
    return reg;
}

int main() {
    scanf("%d%d%d", &row_count, &colume_count, &limit);
    for (int i = 0; i < row_count; ++ i) {
        for (int j = 0; j < colume_count; ++ j) {
            scanf("%d", &weight[i][j]);
        }
        sum[i][colume_count] = 0;
        for (int j = colume_count - 1; j >= 0; -- j) {
            sum[i][j] = sum[i][j + 1] + weight[i][j];
        }
    }
    memset(memory, -1, sizeof(memory));
    int result = -INF, row_choice, left_choice, right_choice;
    for (int row = 0; row < row_count; ++ row) {
        for (int left = 0; left < colume_count; ++ left) {
            for (int right = left + 1; right <= colume_count; ++ right) {
                if (solve(row, 0, left, 1, right, limit) > result) {
                    result = solve(row, 0, left, 1, right, limit);
                    row_choice = row;
                    left_choice = left;
                    right_choice = right;
                }
            }
        }
    }
    printf("Oil : %d\n", result);
    int row = row_choice;
    int left_mode = 0;
    int left = left_choice;
    int right_mode = 1;
    int right = right_choice;
    int total = limit;
    while (true) {
        int &way = choice[row][left_mode][left][right_mode][right][total];
        if (way < -1) {
            if (way == -2) {
                left_mode = 1;
            } else {
                right_mode = 0;
            }
        } else {
            for (int i = left; i < right; ++ i) {
                printf("%d %d\n", row + 1, i + 1);
            }
            if (row == row_count - 1 || way == -1) {
                break;
            }
            row ++;
            total -= right - left;
            left = way % colume_count;
            right = way / colume_count;
        }
    }
    return 0;
}
