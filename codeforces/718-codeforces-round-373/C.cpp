#include <algorithm>
#include <cstdio>
#include <cstring>

const int MOD = (int)1e9 + 7;

const int N = 100000;

struct Matrix
{
    explicit Matrix(int identity = false)
    {
        memset(m, 0, sizeof(m));
        if (identity) {
            m[0][0] = m[1][1] = 1;
        }
    }

    const int* operator [] (int i) const
    {
        return m[i];
    }

    int* operator [] (int i)
    {
        return m[i];
    }

    int m[2][2];
};

Matrix operator + (const Matrix& a, const Matrix& b)
{
    Matrix c;
    for (int i = 0; i < 2; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            int& ref = c[i][j];
            ref = a[i][j] + b[i][j];
            if (ref >= MOD) {
                ref -= MOD;
            }
        }
    }
    return c;
}

Matrix operator * (const Matrix& a, const Matrix& b)
{
    Matrix c;
    for (int i = 0; i < 2; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            c[i][j] = (1LL * a[i][0] * b[0][j] + 1LL * a[i][1] * b[1][j]) % MOD;
        }
    }
    return c;
}

Matrix power(Matrix a, int n)
{
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

int a[N];
Matrix U, T, delta[N << 1], sum[N << 1];

int get_id(int l, int r)
{
    return l + r | l != r;
}

void build(int l, int r)
{
    delta[get_id(l, r)] = Matrix(true);
    if (l < r) {
        int m = l + r >> 1;
        build(l, m);
        build(m + 1, r);
        sum[get_id(l, r)] = sum[get_id(l, m)] + sum[get_id(m + 1, r)];
    } else {
        sum[get_id(l, r)] = U * power(T, a[l]);
    }
}

void cover(int l, int r, int a, int b, const Matrix& M)
{
    if (b < l || r < a) {
        return;
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        delta[id] = delta[id] * M;
        sum[id] = sum[id] * M;
    } else {
        int m = l + r >> 1;
        cover(l, m, a, b, M);
        cover(m + 1, r, a, b, M);
        sum[id] = (sum[get_id(l, m)] + sum[get_id(m + 1, r)]) * delta[id];
    }
}

Matrix query(int l, int r, int a, int b)
{
    if (b < l || r < a) {
        return Matrix();
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        return sum[id];
    }
    int m = l + r >> 1;
    return (query(l, m, a, b) + query(m + 1, r, a, b)) * delta[id];
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("C.in", "r", stdin);
#endif
    U[0][1] = T[0][1] = T[1][0] = T[1][1] = 1;
    int n, m;
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        build(0, n - 1);
        while (m --) {
            int t, l, r, x;
            scanf("%d%d%d", &t, &l, &r);
            l --;
            r --;
            if (t == 1) {
                scanf("%d", &x);
                Matrix M = power(T, x);
                cover(0, n - 1, l, r, M);
            } else {
                printf("%d\n", query(0, n - 1, l, r)[0][0]);
            }
        }
    }
}
