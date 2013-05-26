// Codeforces Beta Round #24
// Problem E -- Berland collider
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 500000;

int n, x[N], v[N];

bool check(double t) {
    double maximum = -1e100;
    for (int i = 0; i < n; ++ i) {
        if (v[i] < 0) {
            if (x[i] + v[i] * t <= maximum) {
                return true;
            }
        } else {
            maximum = std::max(maximum, x[i] + v[i] * t);
        }
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, v + i);
    }
    double low = 0;
    double high = 1e10;
    for (int _ = 0; _ < 100; ++ _) {
        double middle = (low + high) / 2.0;
        if (check(middle)) {
            high = middle;
        } else {
            low = middle;
        }
    }
    double answer = (low + high) / 2.0;
    if (answer > 1e9) {
        puts("-1");
    } else {
        printf("%.12f\n", (low + high) / 2.0);
    }
    return 0;
}
