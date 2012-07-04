// July Challenge 2012
// My Fair Coins
#include <cstdio>
#include <cstring>
using namespace std;

const int SIZE = 2;
const int MOD = 1000000007;

struct Matrix {
    int a[SIZE][SIZE];

    Matrix(bool identity = false) {
        memset(a, 0, sizeof(a));
        if (identity) {
            for (int i = 0; i < SIZE; ++ i) {
                a[i][i] = 1;
            }
        }
    }

    int* operator[](int i) {
        return a[i];
    }

    const int* operator[](int i) const { 
        return a[i];
    }
};

Matrix operator *(const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < SIZE; ++ i) {
        for (int j = 0; j < SIZE; ++ j) {
            long long tmp = 0;
            for (int k = 0; k < SIZE; ++ k) {
                tmp += (long long)a[i][k] * b[k][j];
            }
            c[i][j] = tmp % MOD;
        }
    }
    return c;
}

Matrix pow(Matrix a, int n) {
    Matrix result(true);
    while (n > 0) {
        if ((n & 1) == 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int n;
        scanf("%d", &n);
        Matrix seed;
        seed[0][0] = 1;
        seed[0][1] = 3;
        Matrix transfer;
        transfer[1][0] = 1;
        transfer[0][1] = 2;
        transfer[1][1] = 2;
        seed = seed * pow(transfer, n - 1);
        printf("%d\n", seed[0][0]);
    }
    return 0;
}
