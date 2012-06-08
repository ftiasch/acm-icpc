// SGU 126 -- Boxes
#include <cstdio>
#include <set>
#include <utility>
#include <iostream>
#include <algorithm>
using namespace std;

long long a, b;
set <pair <long long, long long> > states;

long long gcd(long long a, long long b) {
    return b == 0? a: gcd(b, a % b);
}

int main() {
    cin >> a >> b;
    if (a > b) {
        swap(a, b);
    }
    long long d = gcd(a, b);
    a /= d;
    b /= d;
    int step = 0;
    while (a > 0) {
        long long d = gcd(a, b);
        a /= d;
        b /= d;
        if (((a + b) & 1) == 1 || states.find(make_pair(a, b)) != states.end()) {
            puts("-1");
            return 0;
        }
        states.insert(make_pair(a, b));
        step ++;
        b -= a;
        a *= 2;
        if (a > b) {
            swap(a, b);
        }
    }
    printf("%d\n", step);
    return 0;
}
