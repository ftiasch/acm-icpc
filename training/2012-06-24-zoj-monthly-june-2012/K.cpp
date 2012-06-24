// Problem K -- Factorial Problem in Base K
#include <cctype>
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <climits>
#include <iostream>
#include <algorithm>
using namespace std;

int base;
char number[111];
vector <pair <int, int> >primes;

int main() {
    while (scanf("%s%d", number, &base) == 2) {
        int length = strlen(number);
        long long n = 0;
        for (int i = 0; i < length; ++ i) {
            n *= base;
            if (isdigit(number[i])) {
                n += number[i] - '0';
            } else if (isupper(number[i])) {
                n += 10 + number[i] - 'A';
            } else {
                n += 10 + 26 + number[i] - 'a';
            }
        }
        primes.clear();
        for (int p = 2; p * p <= base; ++ p) {
            if (base % p == 0) {
                int e = 0;
                while (base % p == 0) {
                    e ++;
                    base /= p;
                }
                primes.push_back(make_pair(p, e));
            }
        }
        if (base > 1) {
            primes.push_back(make_pair(base, 1));
        }
        unsigned long long result = 1ULL << 63;
        for (vector <pair <int, int> > :: iterator iter = primes.begin(); iter != primes.end(); ++ iter) {
            int p = iter->first;
            int e = iter->second;
            long long ret = 0;
            long long x = p;
            while (true) {
                ret += n / x;
                if (x > n / p) {
                    break;
                }
                x *= p;
            }
            result = min(result, (unsigned long long)ret / e);
        }
        cout << result << "\n";
    }
    return 0;
}
