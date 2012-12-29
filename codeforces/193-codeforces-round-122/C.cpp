// Codeforces Round #122
// Problem C -- Hamming Distance
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

using std::vector;

const char* PATTERNS[7] = {"aaab", "aaba", "aabb", "abaa", "abab", "abba", "abbb"};

int coefficient[6][8];

void swap_row(int i, int j) {
    for (int k = 0; k <= 7; ++ k) {
        std::swap(coefficient[i][k], coefficient[j][k]);
    }
}

int main() {
    int n = 0;
    for (int i = 0; i < 4; ++ i) {
        for (int j = i + 1; j < 4; ++ j) {
            for (int k = 0; k < 7; ++ k) {
                coefficient[n][k] = PATTERNS[k][i] != PATTERNS[k][j];
            }
            scanf("%d", coefficient[n ++] + 7);
        }
    }
    int rank = 0;
    for (int j = 0; j < 7; ++ j) {
        int i = rank;
        while (i < n && !coefficient[i][j]) {
            i ++;
        }
        if (i == n) {
            continue;
        }
        swap_row(i, rank);
        for (int i = rank + 1; i < n; ++ i) {
            while (coefficient[i][j]) {
                swap_row(i, rank);
                int times = coefficient[i][j] / coefficient[rank][j];
                for (int k = 0; k <= 7; ++ k) {
                    coefficient[i][k] -= coefficient[rank][k] * times;
                }
            }
        }
        rank ++;
    }
    int best = -1;
    vector <int> best_solution;
    for (int x = 0; x <= 100000; ++ x) {
        vector <int> solution(7, 0);
        solution[6] = x;
        bool valid = true;
        for (int i = n - 1; i >= 0 && valid; -- i) {
            int left = coefficient[i][7];
            for (int j = i + 1; j < 7; ++ j) {
                left -= coefficient[i][j] * solution[j];
            }
            if (left % coefficient[i][i] == 0) {
                solution[i] = left / coefficient[i][i];
            } else {
                valid = false;
            }
        }
        if (valid) {
            int sum = 0;
            for (int i = 0; i < 7; ++ i) {
                valid &= solution[i] >= 0;
                sum += solution[i];
            }
            if (valid && (best == -1 || sum < best)) {
                best = sum;
                best_solution = solution;
            }
        }
    }
    printf("%d\n", best);
    if (best != -1) {
        for (int j = 0; j < 4; ++ j) {
            for (int i = 0; i < 7; ++ i) {
                for (int k = 0; k < best_solution[i]; ++ k) {
                    putchar(PATTERNS[i][j]);
                }
            }
            puts("");
        }
    }
    return 0;
}
