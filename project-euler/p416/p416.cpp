#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

const int MOD = (int)1e9;

typedef long long Long;

const int N = 20;
const int M = (N + 2) * (N + 1);

struct Matrix {
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
};

Matrix operator *(const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < M; ++ i) {
        for (int j = 0; j < M; ++ j) {
            Long tmp = 0;
            for (int k = 0; k < M; ++ k) {
                tmp += (Long)a[i][k] * b[k][j] % MOD;
            }
            c[i][j] = tmp % MOD;
        }
    }
    return c;
}

Matrix pow(Matrix a, Long n) {
    Matrix result;
    for (int i = 0; i < M; ++ i) {
        result[i][i] = 1;
    }
    while (n) {
        if (n & 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

int binom[N + 1][N + 1];

struct State {
    int id, c, x, y, z;

    State(int id, int c, int x, int y, int z) : id(id), c(c), x(x), y(y), z(z) {}
};

bool operator ==(const State &a, const State &b) {
    return a.c == b.c && a.x == b.x && a.y == b.y && a.z == b.z;
}

int solve(int m, Long n) {
    m *= 2;
    std::vector <State> states;
    for (int x = 0; x <= m; ++ x) {
        for (int y = 0; x + y <= m; ++ y) {
            for (int c = 0; c < 2; ++ c) {
                states.push_back(State(states.size(), c, x, y, m - x - y));
            }
        }
    }
    Matrix t;
    for (auto &a : states) {
        for (auto &b : states) {
            if (a.y >= b.x && a.z >= b.y && a.c + !b.z == b.c) {
                t[a.id][b.id] = (Long)binom[a.y][b.x] * binom[a.z][b.y] % MOD;
            }
        }
    }
    t = pow(t, n - 1);
#define ID(c, x, y, z) std::find(states.begin(), states.end(), State(-1, c, x, y, z)) - states.begin()
    return (t[ID(0, 0, 0, m)][ID(1, 0, 0, m)] + t[ID(1, 0, 0, m)][ID(1, 0, 0, m)]) % MOD;
}

int main() {
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= N; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    printf("%d\n", solve(2, 100));
    printf("%d\n", solve(10, 1000000000000LL));
    return 0;
}
