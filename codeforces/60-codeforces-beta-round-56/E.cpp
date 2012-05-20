// Codeforces Beta Round #56
// Problem E -- Mushroom Gnomes
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int SIZE = 5;

int MOD;

struct Matrix {
    int data[SIZE][SIZE];

    Matrix(int t = 0) {
        memset(data, 0, sizeof(data));
        if (t) {
            for (int i = 0; i < SIZE; ++ i) {
                data[i][i] = 1;
            }
        }
    }

    int* operator[](int i) {
        return data[i];
    }
};

Matrix operator*(Matrix a, Matrix b) {
    Matrix c;
    for (int i = 0; i < SIZE; ++ i) {
        for (int j = 0; j < SIZE; ++ j) {
            int tmp = 0;
            for (int k = 0; k < SIZE; ++ k) {
                tmp = (tmp + (long long)a[i][k] * b[k][j]) % MOD;
            }
            c[i][j] = tmp;
        }
    }
    return c;
}

Matrix pow_mod(Matrix a, long long n) {
    Matrix result(1);
    while (n > 0) {
        if ((n & 1) == 1) {
            result = result * a;
        } 
        a = a * a;
        n >>= 1;
    }
    return result;
}

const int N = 1000000;

int n, m[N];
long long x, y;

int main() {
    scanf("%d", &n);
    cin >> x >> y;
    scanf("%d", &MOD);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", m + i);
    }
    if (n == 1) {
        printf("%d\n", m[0] % MOD);
    } else {
        Matrix a;
        a[0][0] = m[0];
        for (int i = 1; i < n - 1; ++ i) {
            a[0][1] = (a[0][1] + m[i]) % MOD;
        }
        a[0][2] = m[n - 1];
        a[0][3] = m[n - 2];
        a[0][4] = m[n - 1];
        Matrix t;
        t[0][0] = 1;
        t[0][1] = 1;
        t[1][1] = 3;
        t[2][1] = 1;
        t[2][2] = 1;

        t[3][4] = 1;
        t[4][3] = 1;
        t[4][4] = 1;
        a = a * pow_mod(t, x);
        a[0][1] = (((a[0][1] - a[0][4]) % MOD + m[n - 1]) % MOD + MOD) % MOD;
        a[0][2] = a[0][4];
        a = a * pow_mod(t, y);
        printf("%d\n", ((a[0][0] + a[0][1]) % MOD + a[0][2]) % MOD);
    }
    return 0;
}
