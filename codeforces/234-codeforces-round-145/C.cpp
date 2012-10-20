#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 100000 + 2;

int n, t[N], prefix[N], suffix[N];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", t + i);
    }
    prefix[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        prefix[i] = prefix[i - 1] + (t[i] >= 0);
    }
    suffix[n + 1] = 0;
    for (int i = n; i >= 1; -- i) {
        suffix[i] = suffix[i + 1] + (t[i] <= 0);
    }
    int result = INT_MAX;
    for (int i = 1; i + 1 <= n; ++ i) {
        result = min(result, prefix[i] + suffix[i + 1]);
    }
    printf("%d\n", result);
    return 0;
}
