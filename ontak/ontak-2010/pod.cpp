// ONTAK 2010 Day 1 -- Non-Squarefree Numbers(pod)
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
using namespace std;

typedef long long LL;

LL mySqrt(LL n) {
    LL r = (LL)sqrt(n) + 10;
    while (r * r > n) {
        r --;
    }
    return r;
}

LL countFree(LL n) {
    LL m = mySqrt(n);
    vector <LL> primes;
    vector <short> miu(m + 1, -2);
    LL result = 0;
    for (LL p = 2; p <= m; ++ p) {
        if (miu[p] == -2) {
            miu[p] = -1;
            primes.push_back(p);
        }
        result -= miu[p] * (n / (p * p));
        for (int i = 0; i < (int)primes.size() && p * primes[i] <= m; ++ i) {
            miu[p * primes[i]] = -miu[p];
            if (p % primes[i] == 0) {
                miu[p * primes[i]] = 0;
                break;
            }
        }
    }
    return result;
}

int main() {
    LL rank;
    cin >> rank;
    LL low = 1;
    LL high = 1;
    while (countFree(high) < rank) {
        high <<= 1;
    }
    while (low < high) {
        //printf("%lld %lld\n", low, high);
        LL middle = (low + high) >> 1;
        if (countFree(middle) < rank) {
            low = middle + 1;
        } else {
            high = middle;
        }
    }
    cout << high << endl;
    return 0;
}
