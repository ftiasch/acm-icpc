from fractions import gcd

def solve(n):
    solutions = set()
    for two in range(2 * n + 1):
        for five in range(2 * n + 1):
            ap = (2 ** two) * (5 ** five) + 10 ** n
            bp = (2 ** (2 * n - two)) * (5 ** (2 * n - five)) + 10 ** n
            if ap <= bp:
                p = gcd(ap, bp)
                a = ap // p
                b = bp // p
                d = 1
                while d * d <= p:
                    if p % d == 0:
                        solutions.add((a * d, b * d))
                        if d * d != p:
                            solutions.add((a * (p // d), b * (p // d)))
                    d += 1
    return len(solutions)

print(solve(1))
print(sum(solve(n) for n in range(1, 10)))
