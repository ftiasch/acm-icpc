from collections import defaultdict
from fractions import gcd
from itertools import product

def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

def get_size(ones, exps):
    result = ones + 1
    for e in exps:
        result *= e
    return result

def compute(a, x):
    p, q = 2 ** x[-1], 2 ** (a[-1] - x[-1])
    for i in xrange(len(x) - 1):
        p *= x[i] + 1
        q *= a[i] - x[i]
    g = gcd(p, q)
    return p / g, q / g

def solve(n):
    exps, ones = [], 0
    for p in range(2, n + 1):
        if not is_prime(p):
            continue
        e, x = 0, n
        while x >= p:
            x /= p
            e += x
        if e == 1:
            ones += 1
        else:
            exps.append(e)

    best = (10 ** 100, ([], []))
    for mask in range(2 ** len(exps)):
        a, b = [], []
        for i, e in enumerate(exps):
            if mask >> i & 1:
                a.append(e + 1)
            else:
                b.append(e + 1)
        for i in range(ones + 1):
            size = max(get_size(i, a), get_size(ones - i, b))
            best = min(best, (size, (a + [i + 1], b + [ones - i + 1])))

    binom = [[0] * (ones + 1) for _ in range(ones + 1)]
    for i in range(ones + 1):
        binom[i][0] = 1
        for j in range(1, i + 1):
            binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j]

    a, b = best[1]

    count = defaultdict(lambda: 0)
    for x in product(*map(range, a)):
        p, q = compute(a, x)
        count[(p, q)] += binom[a[-1] - 1][x[-1]]
    result = 0
    for x in product(*map(range, b)):
        p, q = compute(b, x)
        result += count.get((p, q), 0) * binom[b[-1] - 1][x[-1]]
    return result // 2

print solve(10)
print solve(100)
