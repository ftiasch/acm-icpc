// Problem F -- Root of the Problem
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long LL;

const LL INF = 1000000000000000000LL;

LL power(LL a, int n) {
    if (a == 0) {
        return 0;
    }
    LL result = 1;
    for (int i = 0; i < n; ++ i) {
        if (result <= INF  / a) {
            result *= a;
        } else {
            return INF;
        }
    }
    return result;
}

int main() {
    while (true) {
        LL b, n;
        cin >> b >> n;
        if (n == 0) {
            break;
        }
        LL lower = 0;
        LL upper = b;
        while (lower < upper) {
            LL mider = (lower + upper + 1) >> 1;
            if (power(mider, n) <= b) {
                lower = mider;
            } else {
                upper = mider - 1;
            }
        }
        LL choice = -1;
        LL difference = INF;
        for (LL i = lower - 5; i <= lower + 5; ++ i) {
            LL buffer = abs(b - power(i, n));
            if (buffer < difference) {
                difference = buffer;
                choice = i;
            }
        }
        cout << choice << "\n";
    }
    cout << flush;
    return 0;
}
