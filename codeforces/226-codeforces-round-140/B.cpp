// Codeforces Round #140
// Problem B -- Naughty Stone Piles
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 100000 + 1;

int n, a[N];
long long sum[N], result[N];

long long solve(int k) {
    long long result = 0;
    int counter = 0;
    long long length = 1;
    for (long long i = n - 1; i > 0; i -= length) {
        length *= k;
        result += (++ counter) * (sum[max(i - length, 0LL)] - sum[i]);
    }
    return result;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    sort(a, a + n);
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + a[i];
    }
    int limit = (int)sqrt(n);
    for (int i = 1; i < limit; ++ i) {
        result[i] = solve(i);
    }
    int m;
    scanf("%d", &m);
    while (m --) {
        int k;
        scanf("%d", &k);
        if (k < limit) {
            cout << result[k];
        } else {
            cout << solve(k);
        }
        putchar(m? ' ': '\n');
    }
    return 0;
}
