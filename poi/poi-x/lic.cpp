// POI X Stage I -- Numerals of Przesmyks(lic)
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

struct BigInteger {
    static const int MAX_LENGTH = 35;
    static const int BASE = (1U << 30) - 1;

    int length, digit[MAX_LENGTH];

    BigInteger(int number = 0): length(0) {
        memset(digit, 0, sizeof(digit));
        while (number > 0) {
            digit[length ++] = number & BASE;
            number >>= 30;
        }
    }

    int operator[](int k) const {
        return digit[k];
    }

    BigInteger &operator +=(const BigInteger &other) {
        length = max(length, other.length) + 1;
        for (int i = 0; i + 1 < length; ++ i) {
            digit[i] += other[i];
            digit[i + 1] += digit[i] >> 30;
            digit[i] &= BASE;
        }
        while (length > 0 && digit[length - 1] == 0) {
            length --;
        }
        return *this;
    }

    BigInteger &operator -=(const BigInteger &other) {
        for (int i = 0; i < length; ++ i) {
            digit[i] -= other[i];
            if (digit[i] < 0) {
                digit[i] += BASE + 1;
                digit[i + 1] --;
            }
        }
        while (length > 0 && digit[length - 1] == 0) {
            length --;
        }
        return *this;
    }
};

bool operator <=(const BigInteger &a, const BigInteger &b) {
    if (a.length == b.length) {
        for (int i = a.length - 1; i >= 0; -- i) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return true;
    }
    return a.length <= b.length;
}

static const BigInteger ZERO(0);
static const BigInteger ONE(1);

static const int N = 1445;
static const int M = 115;

struct Pool {

    int m;
    bool solved[N][M];
    BigInteger ways[N][M];

    Pool(int m): m(m) {
        memset(solved, 0, sizeof(solved));
    }

    BigInteger count(int left, int cons) {
        if (cons > m) {
            return ZERO;
        }
        if (left == 0) {
            return ONE;
        }
        if (!solved[left][cons]) {
            solved[left][cons] = true;
            ways[left][cons] += count(left - 1, 0);
            ways[left][cons] += count(left - 1, cons + 1);
        }
        return ways[left][cons];
    }
};

static const int T = 10;

char text[N];
BigInteger codes[T];

int main() {
    int m[2], testCount;
    scanf("%d%d%d", m, m + 1, &testCount);
    Pool *pool = new Pool(m[0]);
    for (int t = 0; t < testCount; ++ t) {
        scanf("%s", text);
        int n = strlen(text);
        // encode 
        BigInteger code = ZERO;
        for (int i = 1; i < n; ++ i) {
            code += pool->count(i, 0);
        }
        int cons = 0;
        for (int i = 0; i < n; ++ i) {
            if (text[i] == '-') {
                cons ++;
            }
            if (text[i] == '+') {
                code += pool->count(n - i - 1, cons + 1);
                cons = 0;
            }
        }
        codes[t] = code;
    }
    delete pool;
    pool = new Pool(m[1]);
    for (int t = 0; t < testCount; ++ t) {
        // decode
        BigInteger code = codes[t];
        int length = 1;
        while (pool->count(length, 0) <= code) {
            code -= pool->count(length ++, 0);
        }
        int cons = 0;
        for (int i = 0; i < length; ++ i) {
            BigInteger ret = pool->count(length - i - 1, cons + 1);
            if (ret <= code) {
                code -= ret;
                putchar('+');
                cons = 0;
            } else {
                putchar('-');
                cons ++;
            }
        }
        puts("");
    }
    delete pool;
    return 0;
}
