#include <cstdio>
#include <cstring>
using namespace std;

const int N = 111111;
const int MOD = 1000000007;

int n, a[N], prefix[N], suffix[N];

int multiply(int a, int b) {
    return (long long)a * b % MOD;
}

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    prefix[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        prefix[i] = multiply(prefix[i - 1], a[i]);
    }
    suffix[n + 1] = 1;
    for (int i = n; i >= 1; -- i) {
        suffix[i] = multiply(suffix[i + 1], a[i]);
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%d%c", multiply(prefix[i - 1], suffix[i + 1]), i == n? '\n': ' ');
    }
    return 0;
}
