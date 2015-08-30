#include <cstdio>
#include <algorithm>

const int N = 100000;

int n, a[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    a[0] = a[n - 1] = 1;
    for (int i = 1; i < n; ++ i) {
        a[i] = std::min(a[i], a[i - 1] + 1);
    }
    for (int i = n - 2; i >= 0; -- i) {
        a[i] = std::min(a[i], a[i + 1] + 1);
    }
    printf("%d\n", *std::max_element(a, a + n));
    return 0;
}
