// Codeforces Round #142
// Problem B -- T-primes
#include <cstdio>
#include <cstring>
#include <set>
#include <iostream>
using namespace std;

set <long long> tprimes;

bool isPrime(int n) {
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    tprimes.insert(4);
    for (int i = 3; i <= 1000000; i += 2) {
        if (isPrime(i)) {
            tprimes.insert((long long)i * i);
        }
    }
    int n;
    scanf("%d", &n);
    while (n --) {
        long long x;
        cin >> x;
        puts(tprimes.find(x) == tprimes.end()? "NO": "YES");
    }
    return 0;
}
