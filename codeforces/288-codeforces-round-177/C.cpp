// Codeforces Round #177
// Problem C -- Polo the Penguin and XOR operation
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 1000000;

int n, p[N + 1];

int main() {
    scanf("%d", &n);
    int backup = n;
    for (int k = 19; k >= 0; -- k) {
        if (1 << k <= n) {
            for (int i = 1 << k; i <= n; ++ i) {
                int j = i ^ (1 << k + 1) - 1;
                p[i] = j;
                p[j] = i;
            }
            n = (1 << k + 1) - n - 2;
        }
    }
    n = backup;
    long long answer = 0;
    for (int i = 0; i <= n; ++ i) {
        answer += i ^ p[i];
    }
    std::cout << answer << std::endl;
    for (int i = 0; i <= n; ++ i) {
        std::cout << p[i] << " \n"[i == n];
    }
    return 0;
}
