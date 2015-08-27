#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>
#include <string>

using namespace std;

static const int N   = 50;
static const int M   = 12;
static const int MOD = (int)1e9 + 7;

void update(int &x, int a) {
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

struct Matrix {
    Matrix() {
        memset(mat, 0, sizeof(mat));
    }

    int* operator[](int i) {
        return mat[i];
    }

    const int* operator[](int i) const {
        return mat[i];
    }

    int mat[M][M];
};

Matrix operator * (const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < M; ++ i) {
        for (int j = 0; j < M; ++ j) {
            for (int k = 0; k < M; ++ k) {
                update(c[i][j], (long long)a[i][k] * b[k][j] % MOD);
            }
        }
    }
    return c;
}

Matrix identity() {
    Matrix id;
    for (int i = 0; i < M; ++ i) {
        id[i][i] = 1;
    }
    return id;
}

Matrix pow(Matrix a, int n) {
    Matrix result = identity();
    while (n > 0) {
        if (n & 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

std::vector <int> l, r, children[N + 1];
Matrix a;

bool by_l(int i, int j) {
    return l[i] < l[j];
}

Matrix solve(int u) {
    Matrix result = identity();
    int p = l[u];
    for (int v : children[u]) {
        result = result * pow(a, l[v] - p);
        Matrix t = solve(v);
        for (int i = 0; i < M; ++ i) {
            for (int j = 0; j < M; ++ j) {
                if ((i & 1) != (j & 1)) {
                    t[i][j] = 0;
                }
            }
        }
        result = result * t;
        p = r[v] + 1;
    }
    result = result * pow(a, r[u] + 1 - p);
    return result;
}

struct CountBinarySequences {
    int countSequences(int n, int k, std::vector<int> l_, std::vector<int> r_) {
        ::l = l_;
        ::r = r_;
        l.push_back(1);
        r.push_back(n);
        int m = l.size();
        for (int i = 0; i < m; ++ i) {
            children[i].clear();
        }
        for (int i = 0; i < m - 1; ++ i) {
            int p = i;
            for (int j = 0; j < m; ++ j) {
                if (i != j && l[j] <= l[i] && r[i] <= r[j] && (p == i || l[p] < l[j] || r[j] < r[p])) {
                    p = j;
                }
            }
            if (i != p) {
                children[p].push_back(i);
            }
        }
        for (int i = 0; i < m; ++ i) {
            std::sort(children[i].begin(), children[i].end(), by_l);
        }
        a = Matrix();
        for (int i = 0; i <= k; ++ i) {
            for (int j = 0; j < 2; ++ j) {
                a[i << 1 | j][j] = 1;
                if (i + 1 <= k) {
                    a[i << 1 | j][i + 1 << 1 | j ^ 1] = 1;
                }
            }
        }
        Matrix t = solve(m - 1);
        int result = 0;
        for (int i = 0; i < M; ++ i) {
            update(result, t[0][i]);
        }
        return result;
    }
};
