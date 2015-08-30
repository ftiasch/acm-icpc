#include <cstdio>

const int N = 100000;

int n, a[N];

int factor(int n) {
    while (n % 2 == 0) {
        n /= 2;
    }
    while (n % 3 == 0) {
        n /= 3;
    }
    return n;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    bool valid = true;
    for (int i = 0; i < n; ++ i) {
        valid &= factor(a[0]) == factor(a[i]);
    }
    puts(valid ? "Yes" : "No");
    return 0;
}
