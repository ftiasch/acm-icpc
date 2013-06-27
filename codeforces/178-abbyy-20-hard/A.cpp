// ABBYY Cup 2.0 Hard
// Problem A -- Educational Game
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, a[N], next[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 0; i < n; ++ i) {
        int j = 0;
        while (i + (1 << j + 1) < n) {
            j ++;
        }
        next[i] = i + (1 << j);
    }
    long long answer = 0;
    for (int i = 0; i < n - 1; ++ i) {
        answer += a[i];
        a[next[i]] += a[i];
        std::cout << answer << std::endl;
    }
    return 0;
}
