from sage.all import *

def p(d):
    return d == 0 and 2. / 3 or 1. / 6

def solve(n):
    n2 = n // 2
    a = [[0.] * (n2 + 1) for _ in range(n2 + 1)]
    b = [1.] * (n2 + 1)
    for d in range(n2 + 1):
        a[d][d] = 1.
        if d > 0:
            for dx in (-1, 0, 1):
                for dy in (-1, 0, 1):
                    dd = (d + n + dy + n - dx) % n
                    dd = min(dd, n - dd)
                    a[d][dd] -= p(dx) * p(dy)
        else:
            b[d] = 0.
    return Matrix(a).solve_right(vector(b))[n2]

print(solve(100))
