// Codeforces Beta Round #18
// Problem B -- Platforms
#include <cstdio>
#include <cstring>
#include <iostream>

int n, d, m, l;

int main() {
    scanf("%d%d%d%d", &n, &d, &m, &l);
    long long answer = 1000000000000000000LL;
    for (int i = 0; i < m; ++ i) {
        if ((long long)i * d % m > l) {
            answer = std::min(answer, (long long)i * d);
        }
    }
    answer = std::min(answer, (((long long)(n - 1) * m + l) / d + 1) * d);
    std::cout << answer << std::endl;
    return 0;
}
