// SGU 175 -- Encoding
#include <cstdio>
#include <cstring>
using namespace std;

int solve(int n, int q) {
    if (n == 1) {
        return 1;
    }
    int k = n / 2;
    if (q <= k) {
        return n - k + solve(k, k - q + 1);
    }
    return solve(n - k, n - q + 1);
}

int main() {
    int n, q;
    scanf("%d%d", &n, &q);
    printf("%d\n", solve(n, q));
    return 0;
}
