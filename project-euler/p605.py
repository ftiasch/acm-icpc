from fractions import Fraction

MOD = 10 ** 8

def solve(n, k):
    r = n if k == 1 else k - 1
    p = pow(2, n - r - 1, MOD) * (r * (pow(2, n, MOD) - 1) + n)
    q = (pow(2, n, MOD) - 1) ** 2
    return p * q % MOD

print(solve(10 ** 8 + 7, 10 ** 4 + 7))
