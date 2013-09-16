#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, a[N], d[N];
long long sum[N + 1];

int main() {
    scanf("%d", &n);
    int total = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        total += a[i];
    }
    d[n - 1] = 0;
    for (int i = n - 1; i >= 1; -- i) {
        d[i - 1] = d[i] - total / n + a[i];
    }
    std::sort(d, d + n);
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + d[i];
    }
    long long answer = 1000000000000000000LL;
    long long prefix = 0;
    for (int i = 0; i < n; ++ i) {
        answer = std::min(answer, sum[i] - (long long)(n - i) * d[i] + (long long)i * d[i] - prefix);
        prefix += d[i];
    }
    std::cout << answer << std::endl;
    return 0;
}
