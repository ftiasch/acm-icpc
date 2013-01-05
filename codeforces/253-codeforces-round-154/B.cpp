// Codeforces Round #154
// Problem B -- Physics Practical
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 100000;

int n, a[N];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    std::sort(a, a + n);
    int answer = n;
    for (int i = 0; i < n; ++ i) {
        answer = std::min(answer, (int)(i + n - (std::upper_bound(a, a + n, 2 * a[i]) - a)));
    }
    printf("%d\n", answer);
    return 0;
}
