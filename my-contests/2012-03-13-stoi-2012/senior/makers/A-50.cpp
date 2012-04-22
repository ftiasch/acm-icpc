#include <cstdio>
#include <cstring>
using namespace std;

const int N = 111111;
const int MOD = 1000000007;

int n, a[N];

int multiply(int a, int b) {
    return (long long)a * b % MOD;
}

int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 1; i <= n; ++ i) {
        int result = 1;
        for (int j = 1; j <= n; ++ j) {
            if (i != j) {
                result = multiply(result, a[j]);
            }
        }
        printf("%d%c", result, i == n? '\n': ' ');
    }
    return 0;
}
