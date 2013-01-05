// Northeastern Europe 2000
// POJ 1753 -- Flip Game
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

char configuration[4][5];

int get_id(int i, int j) {
    return (i << 2) | j;
}

const int DELTA_X[5] = {0, -1, 0, 0, 1};
const int DELTA_Y[5] = {0, 0, -1, 1, 0};

int main() {
    for (int i = 0; i < 4; ++ i) {
        scanf("%s", configuration[i]);
    }
    int answer = INT_MAX;
    for (int mask = 0; mask < 1 << 16; ++ mask) {
        int state = 0;
        for (int i = 0; i < 4; ++ i) {
            for (int j = 0; j < 4; ++ j) {
                if (configuration[i][j] == 'b') {
                    state ^= 1 << get_id(i, j);
                }
                if (mask >> get_id(i, j) & 1) {
                    for (int k = 0; k < 5; ++ k) {
                        int x = i + DELTA_X[k];
                        int y = j + DELTA_Y[k];
                        if (0 <= x && x < 4 && 0 <= y && y < 4) {
                            state ^= 1 << get_id(x, y);
                        }
                    }
                }
            }
        }
        if (state == 0 || state == (1 << 16) - 1) {
            int ones = 0;
            for (int k = 0; k < 16; ++ k) {
                ones += mask >> k & 1;
            }
            answer = std::min(answer, ones);
        }
    }
    if (answer == INT_MAX) {
        puts("Impossible");
    } else {
        printf("%d\n", answer);
    }
    return 0;
}
