// Codeforces Beta Round #48
// Problem D -- Geometrical problem
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

using std::max;
using std::min;

const int N = 100000;

int n, a[N], length[N][3];

int solve(int i, int j) {
    if (a[i] == 0 && a[j] != 0) {
        return INT_MIN;
    }
    int &ret = length[i][j - i];
    if (ret == -1) {
        ret = 2;
        for (int k = j + 1; k < n && k < j + 3; ++ k) {
            if (a[i] * a[k] == a[j] * a[j]) {
                ret = max(ret, solve(j, k) + 1);
            }
        }
    }
    return ret;
}

int work() {
    memset(length, -1, sizeof(length));
    int maximum = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = i + 1; j < n && j < i + 3; ++ j) {
            maximum = max(maximum, solve(i, j));
        }
    }
    return min(n - maximum, 2);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    printf("%d\n", work());
    return 0;
}
