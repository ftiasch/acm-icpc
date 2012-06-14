// SGU 147 -- Black-white king
#include <cassert>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int n, x[3], y[3], current[2];

int get_y_min(int current_x) {
    return max(max(y[0] - (current_x - x[0]), y[1] - (x[1] - current_x)), 0);
}

int get_y_max(int current_x) {
    return min(min(y[0] + (current_x - x[0]), y[1] + (x[1] - current_x)), n);
}

bool check(int current_x, int step) {
    if (abs(current_x - x[2]) > step) {
        return false;
    }
    int y_min = get_y_min(current_x);
    int y_max = get_y_max(current_x);
    if (abs(current_x - x[2]) == step) {
        return max(y[2] - step, y_min) <= min(y[2] + step, y_max);
    }
    return (y_min <= y[2] - step && y[2] - step <= y_max) || (y_min <= y[2] + step && y[2] + step <= y_max);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < 3; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    if (abs(x[0] - x[1]) < abs(y[0] - y[1])) {
        for (int i = 0; i < 3; ++ i) {
            swap(x[i], y[i]);
        }
    }
    if (x[0] > x[1]) {
        for (int i = 0; i < 3; ++ i) {
            x[i] = n - x[i];
        }
    }
    if (y[0] > y[1]) {
        for (int i = 0; i < 3; ++ i) {
            y[i] = n - y[i];
        }
    }
    current[0] = x[0];
    current[1] = x[1];
    for (int step = 1;; ++ step) {
        current[0] ++;
        if (abs(current[0] - current[1]) <= 1) {
            break;
        }
        current[1] --;
        if (abs(current[0] - current[1]) <= 1) {
            break;
        }
        if (check(current[0], step) || check(current[1], step)) {
            puts("YES");
            printf("%d\n", step);
            return 0;
        }
    }
    puts("NO");
    printf("%d\n", abs(x[0] - x[1]) - 1);
    return 0;
}
