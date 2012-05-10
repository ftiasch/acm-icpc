// Codeforces Round #119
// Problem A -- Permutations
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 222222;

int n, a[N], p[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 0; i < n; ++ i) {
        int b_i;
        scanf("%d", &b_i);
        p[b_i] = i;
    }
    int result = 0;
    int last_index = -1;
    for (int i = 0; i < n; ++ i) {
        if (p[a[i]] > last_index) {
            result ++;
            last_index = p[a[i]];
        } else {
            break;
        }
    }
    printf("%d\n", n - result);
    return 0;
}
