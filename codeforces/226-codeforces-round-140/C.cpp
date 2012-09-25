// Codeforces Rund #140
// Problem C -- Anniversary
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long LL;

void getEvent(vector <LL> &events, LL n) {
    for (LL i = 1; i * i <= n; ++ i) {
        events.push_back(i);
    }
    for (LL i = 0; i * i <= n; ++ i) {
        events.push_back(n / (i + 1) + 1);
    }
}

struct Matrix {
    static LL mod;
    
    LL matrix[2][2];

    Matrix(bool identity = false) {
        memset(matrix, 0, sizeof(matrix));
        if (identity) {
            for (int i = 0; i < 2; ++ i) {
                matrix[i][i] = 1;
            }
        }
    }

    LL* operator[](int i) { 
        return matrix[i]; 
    }

    const LL* operator[](int i) const { 
        return matrix[i]; 
    }
};

LL Matrix::mod;

Matrix operator*(const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < 2; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            for (int k = 0; k < 2; ++ k) {
                c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % Matrix::mod;
            }
        }
    }
    return c;
}

Matrix pow(Matrix a, LL n) {
    Matrix result(true);
    while (n) {
        if (n & 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

LL fibonacci(LL n) {
    Matrix init;
    init[0][0] = 0;
    init[0][1] = 1;
    Matrix transfer;
    transfer[0][1] = 1;
    transfer[1][0] = 1;
    transfer[1][1] = 1;
    return (init * pow(transfer, n))[0][0];
}

int main() {
    LL m, l, r, k;
    cin >> m >> l >> r >> k;
    Matrix::mod = m;
    l --;
    vector <LL> events;
    getEvent(events, l);
    getEvent(events, r);
    sort(events.begin(), events.end());
    events.erase(unique(events.begin(), events.end()), events.end());
    LL result = 0;
    for (int i = 0; i < (int)events.size(); ++ i) {
        LL d = r / events[i] - l / events[i];
        if (d >= k) {
            result = max(result, events[i + 1] - 1);
        }
    }
    cout << fibonacci(result) << endl;
    return 0;
}
