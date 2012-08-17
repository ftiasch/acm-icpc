// SGU 370 -- Rifleman
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 1000000 + 1;

int n, m, primeCount, primes[N], divisor[N], miu[N];

int main() {
    scanf("%d%d", &n, &m);
    primeCount = 0;
    memset(divisor, -1, sizeof(divisor));
    miu[1] = 1;
    for (int i = 2; i < N; ++ i) {
        if (divisor[i] == -1) {
            divisor[i] = i;
            primes[primeCount ++] = i;
        }
        miu[i] = miu[i / divisor[i]] * -1;
        if ((i / divisor[i]) % divisor[i] == 0) {
            miu[i] = 0;
        }
        for (int j = 0; j < primeCount && i * primes[j] < N; ++ j) {
            divisor[i * primes[j]] = primes[j];
            if (i % primes[j] == 0) {
                break;
            }
        }
    }
    long long result = 2;
    for (int i = 1; i < n && i < m; ++ i) {
        if (miu[i] != 0) {
            result += miu[i] * (long long)((n - 1) / i) * ((m - 1) / i);
        }
    }
    if (n == 1 && m == 1) {
        result = 0;
    } else if (n == 1 || m == 1) {
        result = 1;
    }
    cout << result << endl;
    return 0;
}
