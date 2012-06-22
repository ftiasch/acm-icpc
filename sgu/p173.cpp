// SGU 173 -- Coins
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 222;
const int M = 55;

struct Matrix {
    bool matrix[M][M];

    Matrix(bool type) {
        memset(matrix, 0, sizeof(matrix));
        if (type) {
            for (int i = 0; i < M; ++ i) {
                matrix[i][i] = true;
            }
        }
    }

    bool get(int i, int j) const {
        return matrix[i][j];
    }

    void set(int i, int j, bool v) {
        matrix[i][j] = v;
    }

    Matrix multiply(const Matrix &other) {
        Matrix result(false);
        for (int i = 0; i < M; ++ i) {
            for (int j = 0; j < M; ++ j) {
                for (int k = 0; k < M; ++ k) {
                    result.set(i, j, result.get(i, j) ^ (get(i, k) & other.get(k, j)));
                }
            }
        }
        return result;
    }

    Matrix pow(int n) {
        Matrix result(true);
        Matrix base = *this;
        while (n > 0) {
            if ((n & 1) == 1) {
                result = result.multiply(base);
            }
            n >>= 1;
            base = base.multiply(base);
        }
        return result;
    }
};

int nextInt() {
    int buffer;
    scanf("%d", &buffer);
    return buffer;
}

int startPosition[N], repeatTimes[N];
bool coefficient[N][N], a[N], solution[N];

void eliminate(bool coefficient[N][N], int n, int m, bool solution[N]) {
    for (int k = 0; k < m - 1; ++ k) {
        int pivot = k;
        while (!coefficient[pivot][k]) {
            pivot ++;
        }
        for (int j = 0; j < m; ++ j) {
            swap(coefficient[pivot][j], coefficient[k][j]);
        }
        for (int i = 0; i < n; ++ i) {
            if (i != k && coefficient[i][k]) {
                for (int j = 0; j < m; ++ j) {
                    coefficient[i][j] ^= coefficient[k][j];
                }
            }
        }
    }
    for (int i = 0; i < m - 1; ++ i) {
        solution[i] = coefficient[i][m - 1];
    }
}

int main() {
    int sequenceLength = nextInt();
    int operationCount = nextInt();
    int length = nextInt();
    int sampleCount = nextInt();
    for (int i = 0; i < operationCount; ++ i) {
        startPosition[i] = nextInt() - 1;
        repeatTimes[i] = nextInt();
    }
    for (int k = 0; k < sampleCount; ++ k) {
        char before[N], after[N];
        scanf("%s%s", before, after);
        for (int i = 0; i < length - 1; ++ i) {
            coefficient[k][i] = after[i] == '1';
        }
        coefficient[k][length - 1] = before[0] != after[length - 1];
    }
    char finalState[N];
    scanf("%s", finalState);
    eliminate(coefficient, sampleCount, length, a);
    Matrix transform(true);
    for (int k = 0; k < operationCount; ++ k) {
        Matrix singleTransform(false);
        for (int i = 0; i < sequenceLength; ++ i) {
            if (startPosition[k] <= i && i < startPosition[k] + length) {
                singleTransform.set(startPosition[k] + (i - startPosition[k] + length - 1) % length, i, true);
                if (i > startPosition[k] && a[i - startPosition[k] - 1]) {
                    singleTransform.set(startPosition[k] + length - 1, i, true);
                }
            } else {
                singleTransform.set(i, i, true);
            }
        }
        transform = singleTransform.pow(repeatTimes[k]).multiply(transform);
    }
    for (int i = 0; i < sequenceLength; ++ i) {
        for (int j = 0; j < sequenceLength; ++ j) {
            coefficient[i][j] = transform.get(i, j);
        }
        coefficient[i][sequenceLength] = finalState[i] == '1';
    }
    eliminate(coefficient, sequenceLength, sequenceLength + 1, solution);
    for (int i = 0; i < sequenceLength; ++ i) {
        printf("%c", solution[i]? '1': '0');
    }
    puts("");
    return 0;
}
