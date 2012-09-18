// Codeforces Round #137
// Problem A -- Shooshuns and Sequence
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, k, a[N];

int main() {
    scanf("%d%d", &n, &k);
    k --;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    int finalValue = *min_element(a + k, a + n);
    if (*max_element(a + k, a + n) != finalValue) {
        puts("-1");
    } else {
        int i = k - 1;
        while (i >= 0 && a[i] == finalValue) {
            i --;
        }
        printf("%d\n", i + 1);
    }
    return 0;
}
