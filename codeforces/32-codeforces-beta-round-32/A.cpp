// Codeforces Beta Round #32
// Problem A -- Reconnaissance
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 1000;

int n, d, a[N];

int main() {
    scanf("%d%d", &n, &d);
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        for (int j = 0; j < i; ++ j) {
            if (std::abs(a[i] - a[j]) <= d) {
                answer ++;
            }
        }
    }
    printf("%d\n", answer * 2);
    return 0;
}
