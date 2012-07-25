// POI X Stage II -- Trinomial(tro)
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

int choose[3][3];

int solve(long long n, long long k) {
    int result = 1;
    while (n > 0 || k > 0) {
        result = (result * choose[n % 3][k % 3]) % 3;
        n /= 3;
        k /= 3;
    }
    return result;
}

int main() {
    memset(choose, 0, sizeof(choose));
    for (int i = 0; i < 3; ++ i) {
        choose[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            choose[i][j] = (choose[i - 1][j] + choose[i - 1][j - 1]) % 3;
        }
    }
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        long long n, i;
        cin >> n >> i;
        int result = solve(n * 2, i);
        if (((2 * n - i) & 1) == 1) {
            result = (3 - result) % 3;
        }
        printf("%d\n", result);
    }
    return 0;
}
