// SGU 141 -- Jumping Joe
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const long long INF = 1000000000000000000LL;

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

long long find(long long x_0, long long y_0, long long approximation, long long delta_x, long long delta_y, long long limit) {
    for (long long t = approximation - 10; t <= approximation + 10; ++ t) {
        long long tmp = abs(x_0 + delta_x * t) + abs(y_0 - delta_y * t);
        if (tmp <= limit && ((limit - tmp) & 1) == 0) {
            return t;
        }
    }
    return INF;
}

long long a, b, c, m;

int main() {
    cin >> a >> b >> c >> m;
    long long d = gcd(a, b);
    if (c % d == 0) {
        long long x_0, y_0;
        solve(a, b, c, x_0, y_0);
        long long t_x = -x_0 / (b / d);
        long long t_y = y_0 / (a / d);
        long long choice = find(x_0, y_0, t_x, b / d, a / d, m);
        if (choice == INF) {
            choice = find(x_0, y_0, t_y, b / d, a / d, m);
        }
        if (choice == INF) {
            cout << "NO" << endl;
        } else {
            long long x_1 = x_0 + b / d * choice;
            long long y_1 = y_0 - a / d * choice;
            long long left = m - abs(x_1) - abs(y_1);
            long long x_positive = x_1 > 0? x_1: 0;
            long long x_negative = x_1 < 0? -x_1: 0;
            long long y_positive = y_1 > 0? y_1: 0;
            long long y_negative = y_1 < 0? -y_1: 0;
            x_positive += left / 2;
            x_negative += left / 2;
            cout << "YES" << endl;
            cout << x_positive << " " << x_negative << " " << y_positive << " " << y_negative << endl;
        }
    } else {
        cout << "NO" << endl;
    }
    return 0;
}
