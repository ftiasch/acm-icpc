// VK Cup 2012 Finals, Practice Session
// Problem A -- Multicolored Marbles
#include <cstdio>
#include <cstring>
using namespace std;

const int MOD = 1000000000 + 7;

int n, sum[2];

int main() {
    scanf("%d", &n);
    int answer = 0;
    memset(sum, 0, sizeof(sum));
    for (int i = n - 1; i >= 0; -- i) {
        int j = i & 1;
        int ways = (sum[j ^ 1] + 1) % MOD;
        (sum[j] += ways) %= MOD;
        (answer += ways) %= MOD;
    }
    printf("%d\n", answer);
    return 0;
}
