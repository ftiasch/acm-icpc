// Codeforces Round #140
// Problem D -- The table
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;

int n, m, a[N][N], row_sum[N], column_sum[N];
bool row_change[N], column_change[N];

void print(bool change[], int n) {
    int size = 0;
    for (int i = 0; i < n; ++ i) {
        size += change[i];
    }
    printf("%d", size);
    for (int i = 0; i < n; ++ i) {
        if (change[i]) {
            printf(" %d", i + 1);
        }
    }
    puts("");
}

int main() {
    scanf("%d%d", &n, &m);
    memset(row_sum, 0, sizeof(row_sum));
    memset(column_sum, 0, sizeof(column_sum));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &a[i][j]);
            row_sum[i] += a[i][j];
            column_sum[j] += a[i][j];
        }
    }
    memset(row_change, 0, sizeof(row_change));
    memset(column_change, 0, sizeof(column_change));
    while (true) {
        int i = min_element(row_sum, row_sum + n) - row_sum;
        int j = min_element(column_sum, column_sum + m) - column_sum;
        if (row_sum[i] >= 0 && column_sum[j] >= 0) {
            break;
        }
        if (row_sum[i] < 0) {
            row_change[i] ^= 1;
            row_sum[i] *= -1;
            for (int j = 0; j < m; ++ j) {
                column_sum[j] -= a[i][j];
                a[i][j] *= -1;
                column_sum[j] += a[i][j];
            }
        }
        if (column_sum[j] < 0) {
            column_change[j] ^= 1;
            column_sum[j] *= -1;
            for (int i = 0; i < n; ++ i) {
                row_sum[i] -= a[i][j];
                a[i][j] *= -1;
                row_sum[i] += a[i][j];
            }
        }
    }
    print(row_change, n);
    print(column_change, m);
    return 0;
}
