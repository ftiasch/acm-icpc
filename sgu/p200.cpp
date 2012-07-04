// SGU 200 -- Cracking RSA
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;

int primeCount, n, primes[N], a[N];
bool coefficient[N][N];

bool isPrime(int n) {
    if (n < 2) {
        return false;
    }
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int getExponent(int p, int n) {
    int result = 0;
    while (n % p == 0) {
        result ++;
        n /= p;
    }
    return result;
}

int countFree(int n, int m) {
    int result = 0;
    int baseCount = 0;
    for (int j = 0; j < m; ++ j) {
        int pivot = baseCount;
        while (pivot < n && !coefficient[pivot][j]) {
            pivot ++;
        }
        if (pivot >= n) {
            result ++;
        } else {
            for (int k = 0; k < m; ++ k) {
                swap(coefficient[baseCount][k], coefficient[pivot][k]);
            }
            for (int i = 0; i < n; ++ i) {
                if (i != baseCount && coefficient[i][j]) {
                    for (int k = 0; k < m; ++ k) {
                        coefficient[i][k] ^= coefficient[baseCount][k];
                    }
                }
            }
            baseCount ++;
        }
    }
    return result;
}

const int M = 100;

struct BigInteger {
    int length, digit[M];

    BigInteger(int n = 0): length(0) {
        memset(digit, 0, sizeof(digit));
        while (n > 0) {
            digit[length ++] = n % 10;
            n /= 10;
        }
    }

    int &operator[](int i) {
        return digit[i];
    }

    int operator[](int i) const {
        return digit[i];
    }

    void show() {
        for (int i = length - 1; i >= 0; -- i) {
            printf("%d", digit[i]);
        }
        puts("");
    }
};

BigInteger operator *(const BigInteger &a, int b) {
    BigInteger c;
    int delta = 0;
    for (int i = 0; i <= a.length; ++ i) {
        delta += a[i] * b;
        c[i] = delta % 10;
        delta /= 10;
    }
    c.length = a.length + 1;
    while (c.length > 0 && c[c.length - 1] == 0) {
        c.length --;
    }
    return c;
}


int main() {
    scanf("%d%d", &primeCount, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 0, p = 2; i < primeCount; ++ i) {
        while (!isPrime(p)) {
            p ++;
        }
        primes[i] = p ++;
    }
    for (int i = 0; i < primeCount; ++ i) {
        for (int j = 0; j < n; ++ j) {
            coefficient[i][j] = (getExponent(primes[i], a[j]) & 1) == 1;
        }
    }
    int freeCount = countFree(primeCount, n);
    BigInteger result(1);
    for (int i = 0; i < freeCount; ++ i) {
        result = result * 2;
    }
    result[0] --;
    result.show();
    return 0;
}
