// SGU 111 - Very simple problem
#include <cstdio>
#include <cstring>
#include <string>
#include <iostream>
using namespace std;

const int N = 333;

struct BigInteger {
    int length;
    int digit[N];

    BigInteger(): length(0) {
        memset(digit, 0, sizeof(digit));
    }

    const int &operator[](int i) const {
        return digit[i];
    }

    int &operator[](int i) {
        return digit[i];
    }
};

istream &operator >>(istream &in, BigInteger &a) {
    string buffer;
    in >> buffer;
    a.length = ((int)buffer.size() + 3) >> 2;
    for (int i = 0; i < (int)buffer.size(); ++ i) {
        int &memory = a[((int)buffer.size() - i - 1) >> 2];
        memory = memory * 10 + buffer[i] - '0';
    }
    return in;
}

ostream &operator <<(ostream &out, BigInteger &a) {
    out << a[a.length - 1];
    for (int i = a.length - 2; i >= 0; -- i) {
        for (int j = 1000; j >= 10; j /= 10) {
            if (a[i] < j) {
                out << 0;
            }
        }
        out << a[i];
    }
    return out;
}

bool operator<(const BigInteger &a, const BigInteger &b) {
    if (a.length == b.length) {
        for (int i = a.length - 1; i >= 0; -- i) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return false;
    }
    return a.length < b.length;
}

BigInteger operator*(const BigInteger &a, const BigInteger &b) {
    BigInteger c;
    for (int i = 0; i < a.length; ++ i) {
        int delta = 0;
        for (int j = 0; j <= b.length; ++ j) {
            delta += a[i] * b[j] + c[i + j];
            c[i + j] = delta % 10000;
            delta /= 10000;
        }
    }
    c.length = a.length + b.length;
    while (c.length > 0 && c[c.length - 1] == 0) {
        c.length --;
    }
    return c;
}

BigInteger n, r;

int main() {
    cin >> n;
    r.length = (n.length >> 1) + 5;
    for (int i = (n.length >> 1) + 4; i >= 0; -- i) {
        int lower = 0;
        int upper = 9999;
        while (lower < upper) {
            //printf("%d, %d\n", lower, upper);
            int mider = (lower + upper + 1) >> 1;
            r[i] = mider;
            if (n < r * r) {
                upper = mider - 1;
            } else {
                lower = mider;
            }
        }
        r[i] = lower;
    }
    while (r.length > 0 && r[r.length - 1] == 0) {
        r.length --;
    }
    cout << r << endl;
    return 0;
}
