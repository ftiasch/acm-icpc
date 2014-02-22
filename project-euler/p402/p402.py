# Project Euler 402 Integer-valued polynomials
from fractions import gcd

def polynomial_sum(a, b):
    result = [0] * max(len(a), len(b))
    for i in xrange(len(a)):
        result[i] += a[i]
    for i in xrange(len(b)):
        result[i] += b[i]
    return result

def polynomial_product(a, b):
    result = [0] * (len(a) + len(b) - 1)
    for i in xrange(len(a)):
        for j in xrange(len(b)):
            result[i + j] += a[i] * b[j]
    return result

def make_matrix(n):
    return [[0] * n for _ in xrange(n)]

def matrix_product(a, b):
    mod, m, result = 1000000000 * 24 * 24 * 24, len(a), []
    for i in xrange(m):
        row = []
        for j in xrange(m):
            s = 0
            for k in xrange(m):
                s += a[i][k] * b[k][j]
            row.append(s % mod)
        result.append(row)
    return result

def matrix_power(a, n):
    m = len(a)
    result = [[i == j and 1 or 0 for j in xrange(m)] for i in xrange(m)]
    while n > 0:
        if n % 2 == 1:
            result = matrix_product(result, a)
        a = matrix_product(a, a)
        n /= 2
    return result

polynomials = []
for remainder in xrange(24):
    r = remainder
    frequency = [[-r, 1] for _ in xrange(24)]
    for i in xrange(r):
        frequency[1 + i][0] += 24
    result = []
    for a in xrange(24):
        for b in xrange(24):
            for c in xrange(24):
                m, pd = gcd(24, gcd(36 + 6 * a, gcd(14 + 6 * a + 2 * b, 1 + a + b + c))), polynomial_product
                product = pd([m], pd(frequency[a], pd(frequency[b], frequency[c])))
                result = polynomial_sum(result, product)
    polynomials.append(result)

def s(n):
    polynomial, result = polynomials[n % 24], 0
    for i in reversed(xrange(len(polynomial))):
        result = result * n + polynomial[i]
    assert result % (24 * 24 * 24) == 0
    return result / (24 * 24 * 24)

print s(10)
print s(10000)

fibonacci = [0, 1]
for i in xrange(25):
    fibonacci.append(fibonacci[-1] + fibonacci[-2])

binom = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1]]

def get_power_sum(s, k, n):
    init, transfer = make_matrix(k + 2), make_matrix(k + 2)
    transfer[k][k + 1] = transfer[k + 1][k + 1] = 1
    for i in xrange(k + 1):
        init[0][i] = pow(fibonacci[s], i) * pow(fibonacci[s + 1], k - i)
        # (f[23] x + f[24] y)^i (f[24] x + f[25]y)^{k - i}
        for x in xrange(i + 1):
            for y in xrange(k - i + 1):
                transfer[x + y][i] += binom[i][x] * binom[k - i][y] \
                        * pow(fibonacci[23], x) * pow(fibonacci[24], i - x) \
                        * pow(fibonacci[24], y) * pow(fibonacci[25], k - i - y)
    return (matrix_product(init, matrix_power(transfer, n)))[0][k + 1]

def solve(n):
    result = 0
    for i in xrange(2, 2 + 24):
        polynomial = polynomials[fibonacci[i] % 24]
        for j in xrange(4):
            s = get_power_sum(i, j, (n - i) / 24 + 1)
            result += s * polynomial[j]
    assert result % (24 * 24 * 24) == 0
    return (result / (24 * 24 * 24)) % (1000000000)

print solve(1234567890123)
