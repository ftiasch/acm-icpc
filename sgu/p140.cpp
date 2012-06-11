// SGU 140 -- Integer Sequences
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 100;

int n;
long long m, mod[N], b, a[N], x[N];

long long gcd(long long a, long long b) {
    return b == 0? a: gcd(b, a % b);
}

void solve(long long a, long long b, long long c, long long &x, long long &y) {
    if (b == 0) {
        x = c / a;
        y = 0;
    } else {
        solve(b, a % b, c, y, x);
        y -= a / b * x;
    }
}

int main() {
    cin >> n >> m >> b;
    for (int i = 0; i < n; ++ i) {
        cin >> a[i];
    }
    long long d = m;
    for (int i = 0; i < n; ++ i) {
        d = gcd(d, a[i]);
    }
    if (b % d != 0) {
        puts("NO");
    } else {
        puts("YES");
        mod[0] = m;
        for (int i = 0; i + 1 < n; ++ i) {
            mod[i + 1] = gcd(mod[i], a[i]);
        }
        long long tmp;
        for (int i = n - 1; i >= 0; -- i) {
            solve(a[i], mod[i], b, x[i], tmp);
            x[i] = (x[i] % m + m) % m;
            b = ((b - a[i] * x[i]) % m + m) % m;
        }
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", (int)x[i], i + 1 == n? '\n': ' ');
        }
    }
    
    return 0;
}
