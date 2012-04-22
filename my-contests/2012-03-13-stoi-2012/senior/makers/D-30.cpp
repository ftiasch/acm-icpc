#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 111111;

int n, m, a[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    while (m --) {
        int l, r, b;
        scanf("%d%d%d", &l, &r, &b);
        int result = 0;
        for (int j = l; j <= r; ++ j) {
            result = max(result, a[j] ^ b);
        }
        printf("%d\n", result);
    }
    return 0;
}
