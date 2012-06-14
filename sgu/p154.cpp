// SGU 154 -- Factorial
#include <iostream>
using namespace std;

long long count_5(long long n) {
    long long result = 0;
    for (long long i = 5; i <= n; i *= 5) {
        result += n / i;
    }
    return result;
}

int main() {
    long long n;
    cin >> n;
    long long lower = 1;
    long long upper = 1;
    while (count_5(upper) < n) {
        upper *= 2;
    }
    while (lower < upper) {
        long long mider = (lower + upper) >> 1;
        if (count_5(mider) >= n) {
            upper = mider;
        } else {
            lower = mider + 1;
        }
    }
    if (count_5(upper) != n) {
        cout << "No solution" << endl;
    } else {
        cout << upper << endl;
    }
    return 0;
}
