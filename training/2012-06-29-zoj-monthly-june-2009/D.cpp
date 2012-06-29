// Problem D -- Compositions
#include <cstdio>
#include <cstring>
using namespace std;

const int M = 31;
const int MOD = 1000000007;

struct Matrix {
    int matrix[M][M];

    Matrix(bool identity = false) {
        memset(matrix, 0, sizeof(matrix));
        if (identity) {
            for (int i = 0; i < M; ++ i) {
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
    for (int i = 0; i < M; ++ i) {
        for (int j = 0; j < M; ++ j) {
            for (int k = 0; k < M; ++ k) {
                c[i][j] = (c[i][j] + (long long)a[i][k] * b[k][j]) % MOD;
            }
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

int solve(int n, int k) {
    Matrix init;
    init[0][k - 1] += 1;
    Matrix transform;
    transform[0][k - 1] += 1;
    transform[k - 1][k - 1] += 1;
    for (int i = 1; i < k; ++ i) {
        transform[i][i - 1] += 1;
    }
    init = init * pow(transform, n);
    return init[0][k - 1];
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int n, k;
        scanf("%d%d", &n, &k);
        printf("%d\n", (solve(n, k) - solve(n - 1, k) + MOD) % MOD);
    }
    return 0;
}
