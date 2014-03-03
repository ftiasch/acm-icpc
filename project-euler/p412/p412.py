def factorial(n, mod):
    result = 1
    for i in xrange(n):
        result = result * (i + 1) % mod
    return result

def powMod(a, n, mod):
    result = 1
    while n > 0:
        if n % 2 == 1:
            result = result * a % mod
        n >>= 1
        a = a * a % mod
    return result

def solve(n, m):
    mod = 76543217

    counts = [0] * (2 * n + 1)
    for i in xrange(m):
        counts[1 + i] += 2
        counts[1 + i + (n - m)] -= 2
    for i in xrange(m, n):
        counts[m + 1 + i] += 1
        counts[n + 1 + i] -= 1

    cells = n * n - m * m
    assert cells < mod
    result, current = factorial(cells, mod), 0
    for i in xrange(len(counts)):
        current += counts[i]
        result = result * powMod(i, current * (mod - 2), mod) % mod
    return result

print solve(3, 0)
print solve(5, 3)
print solve(6, 3)
print solve(10, 5)
print solve(10000, 5000)
