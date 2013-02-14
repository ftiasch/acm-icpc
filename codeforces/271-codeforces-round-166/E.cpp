// Codeforces Round #166
// Problem E -- Three Horses
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

int n, m, a[N];

int main() {
    scanf("%d%d", &n, &m);
    int d = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        d = std::__gcd(d, a[i] - 1);
    }
    std::vector <int> divisors;
    for (int i = 1; i * i <= d; ++ i) {
        if (d % i == 0) {
            divisors.push_back(i);
            if (i * i != d) {
                divisors.push_back(d / i);
            }
        }
    }
    long long answer = 0;
    foreach (iter, divisors) {
        int p = *iter;
        if (p & 1) {
            for (int i = 0; (long long)p << i < m; ++ i) {
                answer += m - (p << i);
            }
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
