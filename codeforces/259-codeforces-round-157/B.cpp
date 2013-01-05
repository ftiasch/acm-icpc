// Codeforces Round #157
// Problem B -- Little Elephant and Magic Square
#include <cstdio>
#include <cstring>

int a[3][3];

int main() {
    for (int i = 0; i < 3; ++ i) {
        for (int j = 0; j < 3; ++ j) {
            scanf("%d", &a[i][j]);
        }
    }
    for (a[0][0] = 1; a[0][0] <= 100000; ++ a[0][0]) {
        int sum = a[0][0] + a[0][1] + a[0][2];
        a[1][1] = sum - a[1][0] - a[1][2];
        a[2][2] = sum - a[2][0] - a[2][1];
        bool valid = true;
        for (int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++ j) {
                valid &= 1 <= a[i][j] && a[i][j] <= 100000;
            }
        }
        for (int i = 0; i < 3; ++ i) {
            valid &= a[i][0] + a[i][1] + a[i][2] == sum;
            valid &= a[0][i] + a[1][i] + a[2][i] == sum;
        }
        valid &= a[0][0] + a[1][1] + a[2][2] == sum;
        valid &= a[0][2] + a[1][1] + a[2][0] == sum;
        if (valid) {
            for (int i = 0; i < 3; ++ i) {
                for (int j = 0; j < 3; ++ j) {
                    printf("%d ", a[i][j]);
                }
                puts("");
            }
            return 0;
        }
    }
    return 0;
}
