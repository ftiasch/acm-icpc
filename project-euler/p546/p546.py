MOD = 10 ** 9 + 7

def mat_mult(a, b):
    n = len(a)
    c = [[0] * n for _ in xrange(n)]
    for i in xrange(n):
        for j in xrange(n):
            s = 0
            for k in xrange(n):
                s += a[i][k] * b[k][j]
            c[i][j] = s % MOD
    return c

def mat_pow(a, n):
    m = len(a)
    r = [[i == j and 1 or 0 for j in xrange(m)] for i in xrange(m)]
    while n > 0:
        if n % 2 == 1:
            r = mat_mult(r, a)
        a = mat_mult(a, a)
        n >>= 1
    return r

def solve(n, k):
    m = 1
    while k ** m <= n:
        m += 1
    s = m * (k - 1) + 1
    mats = []
    for i in xrange(m):
        mat = [[0] * s for _ in xrange(s)]
        if i > 0:
            mat = mat_pow(mats[-1], k)
        for x in xrange(s):
            lv = x / (k - 1)
            cnt = x % (k - 1)
            if lv > i:
                y = i * (k - 1)
                if lv >= m:
                    mat[x][y] += 1
                else:
                    mat[x][y] += cnt + 2
            elif lv == i:
                mat[x][i * (k - 1) + min(cnt + 1, k - 2)] += 1
        mats.append(mat)
    val = [[0] * s for _ in xrange(s)]
    val[0][m * (k - 1)] = 1
    for i in reversed(xrange(m)):
        p = k ** i
        val = mat_mult(val, mat_pow(mats[i], n / p))
        n %= p
    res = 0
    for lv in xrange(m):
        for cnt in xrange(0, k - 1):
            res += val[0][lv * (k - 1) + cnt] * (cnt + 2)
    return res % MOD

res = 0
for k in xrange(2, 11):
    res += solve(10 ** 14, k)
print res % MOD
