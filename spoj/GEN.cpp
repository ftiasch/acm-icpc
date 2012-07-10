// 1676. Text Generator
// Problem code: GEN
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 10;
const int L = 6;
const int M = N * L + 1;
const int A = 26;
const int MOD = 10007;

struct Matrix {
    static int size;

    int matrix[M][M];

    Matrix() {
        memset(matrix, 0, sizeof(matrix));
    }

    int* operator[](int i) {
        return matrix[i];
    }

    const int* operator[](int i) const {
        return matrix[i];
    }

    Matrix operator *=(const Matrix &other) {
        Matrix result;
        for (int i = 0; i < size; ++ i) {
            for (int j = 0; j < size; ++ j) {
                long long tmp = 0;
                for (int k = 0; k < size; ++ k) {
                    tmp += (*this)[i][k] * other[k][j];
                }
                result[i][j] = tmp % MOD;
            }
        }
        return *this = result;
    }
};

int powMod(int a, int n) {
    int result = 1;
    while (n > 0) {
        if ((n & 1) == 1) {
            result = (long long)result * a % MOD;
        }
        a = (long long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

int wordCount, length, trieCount, children[M][A], fail[M], queue[M], next[M][A];
bool end[M];
char words[N][L + 1];

int Matrix::size;

int main() {
    while (scanf("%d%d", &wordCount, &length) == 2) {
        for (int i = 0; i < wordCount; ++ i) {
            scanf("%s", words[i]);
        }
        trieCount = 1;
        memset(children, -1, sizeof(children));
        memset(end, 0, sizeof(end));
        for (int i = 0; i < wordCount; ++ i) {
            int p = 0;
            int length = strlen(words[i]);
            for (int j = 0; j < length; ++ j) {
                int token = words[i][j] - 'A';
                if (children[p][token] == -1) {
                    children[p][token] = trieCount ++;
                }
                p = children[p][token];
            }
            end[p] = true;
        }
        Matrix::size = trieCount;
        memset(fail, 0, sizeof(fail));
        memset(next, 0, sizeof(next));
        int tail = 0;
        for (int token = 0; token < 26; ++ token) {
            if (children[0][token] != -1) {
                queue[tail ++] = next[0][token] = children[0][token];
            }
        }
        int head = 0;
        while (head != tail) {
            int u = queue[head ++];
            end[u] |= end[fail[u]];
            for (int token = 0; token < 26; ++ token) {
                if (children[u][token] != -1) {
                    next[u][token] = children[u][token];
                    int v = children[u][token];
                    fail[v] = next[fail[u]][token];
                    queue[tail ++] = v;
                } else {
                    next[u][token] = next[fail[u]][token];
                }
            }
        }
        Matrix transfer;
        for (int i = 0; i < trieCount; ++ i) {
            for (int token = 0; token < 26; ++ token) {
                if (!end[next[i][token]]) {
                    transfer[i][next[i][token]] ++;
                }
            }
        }
        long long result = powMod(26, length);
        Matrix seed;
        seed[0][0] = 1;
        while (length > 0) {
            if ((length & 1) == 1) {
                seed *= transfer;
            }
            transfer *= transfer;
            length >>= 1;
        }
        for (int i = 0; i < trieCount; ++ i) {
            result -= seed[0][i];
        }
        result = ((result % MOD) + MOD) % MOD;
        printf("%d\n", static_cast<int>(result));
    }
    return 0;
}
