// Codeforces Beta Round #33 
// Problem C -- Wonderful Randomized Sum
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>

const int N = 100000;

int n, a[N], sum[N + 1];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + a[i];
    }
    int answer = INT_MIN;
    int min_suffix = INT_MAX;
    int prefix_sum = sum[0];
    for (int i = n; i >= 0; -- i) {
        min_suffix = std::min(min_suffix, sum[i]);
        if (i < n) {
            prefix_sum -= a[i];
        }
        answer = std::max(answer, sum[0] - 2 * prefix_sum - 2 * min_suffix);
    }
    printf("%d\n", answer);
    return 0;
}
