// Codeforces Round #144
// Problem B -- Non-square Equation
#include <cmath>
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int LIMIT = 1000;

long long n;

long long sqrt2(long long n) {
    long long r = max((long long)sqrt(n) + 1, 0LL);
    while (r * r > n) {
        r --;
    }
    return r;
}

int sum(long long n) {
    int result = 0;
    while (n) {
        result += n % 10;
        n /= 10;
    }
    return result;
}

int main() {
    cin >> n;
    for (long long s = 1; s < LIMIT; ++ s) {
        long long d = s * s + 4 * n;
        long long r = sqrt2(d);
        if (r * r == d && (r - s) % 2 == 0) {
            long long x = (r - s) / 2;
            if (sum(x) == s) {
                cout << x << endl;
                return 0;
            }
        }
    }
    cout << -1 << endl;
    return 0;
}
