mod      = 1234567890
patterns = map(int, '00102010301020104010201030102010')

def convolution(a, b):
    c = {}
    for i, ai in a.items():
        for j, bj in b.items():
            c[i ^ j] = (c.get(i ^ j, 0) + ai * bj) % mod
    return c

def solve(n):
    counts, m = {}, len(patterns)
    for i, pi in enumerate(patterns):
        if i > 0:
            counts[pi] = counts.get(pi, 0) + ((n - i) + m) / m
    n /= 32
    sg = 5
    while n > 0:
        counts[sg] = counts.get(sg, 0) + (n + 1) / 2
        n /= 2
        sg += 1
    return convolution(counts, convolution(counts, counts))[0]

for n in [10, 100, 123456787654321]:
    print "S(%d)=%d" %(n, (n * n * n - solve(n)) % mod)
