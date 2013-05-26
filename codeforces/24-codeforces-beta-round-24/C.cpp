#include <cstdio>
#include <cstring>
#include <iostream>

using std::cin;

const int N = 100000;

int n, x_0, y_0, x[N], y[N];
long long m;

int solve(int a_0, int* a) {
    m %= 2 * n;
    for (int i = 0; i < m; ++ i) {
        a_0 = 2 * a[i % n] - a_0;
    }
    return a_0;
}

int main() {
    cin >> n >> m >> x_0 >> y_0;
    for (int i = 0; i < n; ++ i) {
        cin >> x[i] >> y[i];
    }
    printf("%d %d\n", solve(x_0, x), solve(y_0, y));
    return 0;
}
