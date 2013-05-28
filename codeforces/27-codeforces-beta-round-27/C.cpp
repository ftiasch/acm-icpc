// Codeforces Beta Round #27 
// Problem C -- Unordered Subsequence
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <algorithm>

const int N = 100000;

int n, a[N];
std::pair <int, int> suffix[N + 1];

bool solve() {
    suffix[n] = std::make_pair(INT_MAX, n);
    for (int i = n - 1; i >= 0; -- i) {
        suffix[i] = std::min(suffix[i + 1], std::make_pair(a[i], i));
    }
    std::pair <int, int> prefix(INT_MAX, n);
    for (int i = 0; i < n; ++ i) {
        if (prefix.first < a[i] && a[i] > suffix[i + 1].first) {
            printf("3\n%d %d %d\n", prefix.second + 1, i + 1, suffix[i + 1].second + 1);
            return true;
        }
        prefix = std::min(prefix, std::make_pair(a[i], i));
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    if (solve()) {
        return 0;
    }
    for (int i = 0; i < n; ++ i) {
        a[i] *= -1;
    }
    if (solve()) {
        return 0;
    }
    puts("0");
    return 0;
}
