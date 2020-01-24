from numba import jit

@jit
def sf(m, k, s):
    return ((m - s + 1) + (m + k - s - s)) * (k - s) // 2

@jit
def solve(m):
    result = 0
    for d in range(1, m):
        for s in range(d, m - d + 1, d):
            result += sf(m, s + d, s)
    return result

print(solve(10))
print(solve(1000))
print(solve(10 ** 6))
