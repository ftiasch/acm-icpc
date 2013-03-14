// Codeforces Round #173
// Problem B -- Painting Eggs
#include <cstdio>
#include <cstring>

int n;

int main() {
    scanf("%d", &n);
    int sum = 0;
    for (int i = 0, a, b; i < n; ++ i) {
        scanf("%d%d", &a, &b);
        if (sum + a <= 500) {
            sum += a;
            putchar('A');
        } else {
            sum -= b;
            putchar('G');
        }
    }
    puts("");
    return 0;
}
