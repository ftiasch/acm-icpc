from numba import jit
from fractions import gcd

@jit
def solve(n):
    N = 5 * (10 ** 6)
    mu = [0] * N
    mu[1] = 1
    for d in range(1, N):
        for i in range(d + d, N, d):
            mu[i] -= mu[d]
    coprimes = [0] * N
    for d in range(1, N):
        for i in range(d, N, d):
            coprimes[i] += mu[d] * (i // d)

    result = 2
    d = 3
    while coprimes[d] * d // 2 <= n:
        result += coprimes[d]
        n -= d * coprimes[d] // 2
        d += 1
    result1 = result + (n // d) * 2
    x = d // 2
    while gcd(x, d) != 1:
        x -= 1
    result2 = result + 1 + (n - d + x) // d * 2
    return max(result1, result2)

print(solve(1))
print(solve(3))
print(solve(9))
print(solve(11))
print(solve(100))
print(solve(50000))
print(solve(10 ** 18))
