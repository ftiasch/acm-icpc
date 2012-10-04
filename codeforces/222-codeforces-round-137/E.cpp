// Codeforces Round #137
// Problem E -- Decoding Genome
#include <cstdio>
#include <cstring>
#include <cctype>
#include <iostream>
using namespace std;

const int MOD = 1000000000 + 7;

const int M = 52;

long long n;
int m, k;

struct Matrix {
    int matrix[M][M];

    Matrix(bool id = false) {
        memset(matrix, 0, sizeof(matrix));
        if (id) {
            for (int i = 0; i < m; ++ i) {
                matrix[i][i] = 1;
            }
        }
    }

    int* operator[](int i) {
        return matrix[i];
    }

    const int* operator[](int i) const {
        return matrix[i];
    }
};

Matrix operator *(const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < m; ++ i) {
        for (int j = 0; j < m; ++ j) {
            for (int k = 0; k < m; ++ k) {
                c[i][j] = (c[i][j] + (long long)a[i][k] * b[k][j]) % MOD;
            }
        }
    }
    return c;
}

Matrix pow(Matrix a, long long n) {
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

int getCode(char c) {
    if (islower(c)) {
        return c - 'a';
    }
    return 26 + c - 'A';
}

int main() {
    cin >> n >> m >> k;
    Matrix transfer;
    for (int i = 0; i < m; ++ i) {
        for (int j = 0; j < m; ++ j) {
            transfer[i][j] = 1;
        }
    }
    while (k --) {
        char buffer[3];
        scanf("%s", buffer);
        transfer[getCode(buffer[0])][getCode(buffer[1])] = 0;
    }
    Matrix init;
    for (int i = 0; i < m; ++ i) {
        init[0][i] = 1;
    }
    init = init * pow(transfer, n - 1);
    int result = 0;
    for (int i = 0; i < m; ++ i) {
        result = (result + init[0][i]) % MOD;
    }
    printf("%d\n", result);
    return 0;
}
