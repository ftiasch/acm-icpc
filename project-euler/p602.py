from numba import jit

# e(n, p) = (1 - p)^{n + 1} * (sum_{r >= 0} p^r * r^n)
# c(n, k) = sum_{0 <= r <= k} (k - r)^n * (-1)^r * binom{n + 1}{r}

@jit
def solve(n, k):
    MOD = 10 ** 9 + 7

    fact_n, inv_fact = 1, [1, 1] + [0] * n
    for i in range(2, n + 2):
        fact_n = fact_n * i % MOD
        inv_fact[i] = (MOD - MOD // i) * inv_fact[MOD % i] % MOD
    for i in range(2, n + 2):
        inv_fact[i] = inv_fact[i - 1] * inv_fact[i] % MOD
    result = 0
    for r in range(k):
        add = pow(k - r, n, MOD) * fact_n * inv_fact[r] * inv_fact[n + 1 - r] % MOD
        if r % 2 == 0:
            result += add
        else:
            result -= add
    return result % MOD

print(solve(100, 40))
print(solve(10000000, 4000000))
