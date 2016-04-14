#include <cassert>
#include <cstdio>
#include <functional>
#include <vector>

typedef long long Long;

const int MOD = (int)1e9 + 7;

int update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
    return x;
}

const int N = 101;

struct Matrix
{
    Matrix() {
        memset(data, 0, sizeof(data));
    }

    int* operator[](int i) {
        return data[i];
    }

    const int* operator[](int i) const {
        return data[i];
    }

    int data[N][N];
};

Matrix operator * (const Matrix& a, const Matrix& b)
{
    Matrix c;
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j < N; ++ j) {
            int& ref = c[i][j];
            for (int k = 0; k < N; ++ k) {
                update(ref, static_cast<long long>(a[i][k]) * b[k][j] % MOD);
            }
        }
    }
    return c;
}

Matrix operator ^ (Matrix a, Long n)
{
    Matrix r;
    for (int i = 0; i < N; ++ i) {
        r[i][i] = 1;
    }
    while (n) {
        if (n & 1) {
            r = r * a;
        }
        a = a * a;
        n >>= 1;
    }
    return r;
}

int solve_wide(Long w, int h)
{
    Matrix a;
    a[0][0] = 1;
    for (int i = 2; i <= h; i += 2) {
        a[0][i] = 1;
    }
    Matrix t;
    t[0][0] = h;
    for (int i = 1; i <= h; ++ i) {
        for (int j = 1; j <= h; ++ j) {
            if (i < j && (j - i & 1)) {
                t[0][j] ++;
                t[i][j] = MOD - 1;
            } else {
                t[i][j] = 1;
            }
        }
    }
    Matrix b = a * (t ^ (w - 1));
    int result = 0;
    for (int i = 1; i <= h; ++ i) {
        update(result, b[0][i]);
    }
    return result;
}

int solve_normal(int w, int h)
{
    std::vector<int> ways(h + 1);
    for (int i = 2; i <= h; i += 2) {
        ways[i] = 1;
    }
    long long total = 1;
    for (int _ = 1; _ < w; ++ _) {
        std::vector<int> new_ways(ways);
        for (int i = h - 1; i >= 0; -- i) {
            update(new_ways[i], new_ways[i + 1]);
        }
        int sum[] = {0, 0};
        for (int i = 1; i <= h; ++ i) {
            update(new_ways[i], sum[i & 1]);
            update(new_ways[i], total * (i / 2) % MOD);
            update(new_ways[i], MOD - sum[(i & 1) ^ 1]);
            update(sum[i & 1], ways[i]);
        }
        ways.swap(new_ways);
        total = total * h % MOD;
    }
    int result = 0;
    for (int i = 1; i <= h; ++ i) {
        update(result, ways[i]);
    }
    return result;
}

int solve_tall(int w, Long h)
{
    int parity = h & 1;
    std::vector<int> known;
    for (int i = 0; i <= w; ++ i) {
        known.push_back(solve_normal(w, (i + 1 << 1) - parity));
    }
    std::vector<int> inv_fact(std::max(w + 1, 2));
    inv_fact.at(0) = inv_fact.at(1) = 1;
    for (int i = 2; i <= w; ++ i) {
        inv_fact.at(i) = static_cast<Long>(MOD - MOD / i) * inv_fact.at(MOD % i) % MOD;
    }
    for (int i = 1; i <= w; ++ i) {
        inv_fact.at(i) = static_cast<Long>(inv_fact.at(i)) * inv_fact.at(i - 1) % MOD;
    }
    auto binom = [&inv_fact](Long n, int k) -> Long {
        if (n < k) {
            return 0;
        }
        Long result = inv_fact.at(k);
        for (int i = 0; i < k; ++ i) {
            result *= (n - i) % MOD;
            result %= MOD;
        }
        return result;
    };
    Long n = (h + parity >> 1) - 1;
    if (n <= w) {
        return known.at(n);
    }
    int result = 0;
    for (int i = 0; i <= w; ++ i) {
        int tmp = static_cast<Long>(known[i]) * binom(n, i) % MOD * binom(n - i - 1, w - i) % MOD;
        if (w - i & 1) {
            update(result, MOD - tmp);
        } else {
            update(result, tmp);
        }
    }
    return result;
}

int solve(Long w, Long h, std::function<int(Long, Long)> solver)
{
    int result = solver(w, h);
    if (h > 1) {
        update(result, MOD - solver(w, h - 1));
    }
    return result;
}

int main()
{
    // auto&& solver = solve_tall;
    // printf("%d\n", solve(4, 2, solver));
    // printf("%d\n", solve(13, 100, solver));
    // printf("%d\n", solve(10, 13, solver));
    // printf("%d\n", solve(100, 100, solver));
    int result = 0;
    update(result, solve((Long)1e12, 100, solve_wide));
    update(result, solve(10000, 10000, solve_normal));
    update(result, solve(100, (Long)1e12, solve_tall));
    printf("%d\n", result);
    return 0;
}
