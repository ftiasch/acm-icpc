F = GF(10 ** 9 + 7)

def solve(n, m):
    a = matrix.identity(F, 4, 4)
    bs = []
    for d in range(10):
        b = [[0] * 4 for _ in range(4)]
        b[0][0] = b[0][1] = b[1][1] = b[2][3] = b[3][3] = F(1)
        b[1][2] = F(d)
        b[2][2] = F(10)
        bs.append(matrix(b))
    primes = prime_range(n * 20)[:n]
    sys.stderr.write("Generated prime\n")
    for p in primes:
        for d in map(int, str(p)):
            a *= bs[d]
    sys.stderr.write("Constructed matrix\n")
    w = vector([1, 1, 0, 0]) * (a ** m)
    return w[2] + w[3]

print solve(10 ** 6, 10 ** 12)
