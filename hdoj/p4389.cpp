// HDOJ 4389 -- X mod f(x)
#include <cstdio>
#include <cstring>

const int T = 50;
const int N = 11;
const int M = 99;

int a[T], b[T], result[T], sum, length, digit[N];
int memory[N][M][M];

int count(bool less, int length, int sum, int mod) {
    if (length == 0) {
        return less && sum == ::sum && mod == 0;
    }
    if (less && memory[length][sum][mod] != -1) {
        return memory[length][sum][mod];
    }
    int ret = 0;
    for (int d = 0; d < 10; ++ d) {
        if (!less && d > digit[length - 1]) {
            continue;
        }
        ret += count(less || d < digit[length - 1], length - 1, sum + d, (mod * 10 + d) % ::sum);
    }
    if (less) {
        memory[length][sum][mod] = ret;
    }
    return ret;
}

int solve(int n) {
    length = 0;
    while (n) {
        digit[length ++] = n % 10;
        n /= 10;
    }
    return count(false, length, 0, 0);
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    for (int t = 0; t < test_count; ++ t) {
        scanf("%d%d", a + t, b + t);
    }
    memset(result, 0, sizeof(result));
    for (::sum = 1; ::sum < M; ++ ::sum) {
        memset(memory, -1, sizeof(memory));
        for (int t = 0; t < test_count; ++ t) {
            result[t] -= solve(a[t]);
            result[t] += solve(b[t] + 1);
        }
    }
    for (int t = 0; t < test_count; ++ t) {
        printf("Case %d: %d\n", t + 1, result[t]);
    }
    return 0;
}
