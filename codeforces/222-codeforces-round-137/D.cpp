// Codeforces Round #137
// Problem D -- Olympiad
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, limit, a[N], b[N];

int main() {
    scanf("%d%d", &n, &limit);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    sort(a, a + n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", b + i);
    }
    sort(b, b + n);
    int result = 0;
    for (int i = n - 1, j = 0; i >= 0; -- i) {
        while (j < n && a[i] + b[j] < limit) {
            j ++;
        }
        if (j < n) {
            j ++;
            result ++;
        }
    }
    printf("1 %d\n", result);
    return 0;
}
