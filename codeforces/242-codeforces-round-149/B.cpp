// Codeforces Round #149
// Problem B -- Big Segment
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100000;

int n, l[N], r[N];


int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", l + i, r + i);
    }
    int x = 0;
    for (int i = 1; i < n; ++ i) {
        if (l[i] < l[x] || l[i] == l[x] && r[i] > r[x]) {
            x = i;
        }
    }
    for (int i = 0; i < n; ++ i) {
        if (r[i] > r[x]) {
            puts("-1");
            return 0;
        }
    }
    printf("%d\n", x + 1);
    return 0;
}
