// Codechef April Challenge 2012
// Stacking Pancakes
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1111;
const int MOD = 1000000007;

int ways[N][N], result[N];

void update(int &x, int a) {
    x += a;
    x %= MOD;
}

int main() {
    memset(ways, 0, sizeof(ways));
    ways[0][0] = 1;
    for (int i = 0; i + 1 < N; ++ i) {
        for (int j = 0; j <= i; ++ j) {
            update(ways[i + 1][j], (long long)ways[i][j] * j % MOD);
            update(ways[i + 1][j + 1], ways[i][j]);
        }
    }
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j <= i; ++ j) {
            update(result[i], ways[i][j]);
        }
    }
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        int n;
        scanf("%d", &n);
        printf("%d\n", result[n]);
    }
    return 0;
}
