// Codeforces Round #146
// Problem A -- LCM Challenge
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

typedef long long LL;

LL gcd(LL a, LL b) {
    return b ?  gcd(b, a % b) : a;
}

LL lcm(LL a, LL b) {
    return a / gcd(a, b) * b;
}

int main() {
    int n;
    cin >> n;
    LL result = 0;
    for (LL i = max(n - 50, 1); i <= n; ++ i) {
        for (LL j = max(n - 50, 1); j <= n; ++ j) {
            for (LL k = max(n - 50, 1); k <= n; ++ k) {
                result = max(result, lcm(lcm(i, j), k));
            }
        }
    }
    cout << result << endl;
    return 0;
}
