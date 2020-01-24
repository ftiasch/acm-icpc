def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

def dfs(primes, memo, index, bound):
    if index < 0:
        return 1
    key = (index, bound)
    if key in memo:
        return memo[key]
    p, result = primes[index], 0
    while bound > 0:
        result += dfs(primes, memo, index - 1, bound)
        bound //= p
    memo[key] = result
    return result

def solve(n, m):
    primes = [p for p in range(2, n + 1) if is_prime(p)]
    return dfs(primes, {}, len(primes) - 1, m)

print(solve(5, 10 ** 8))
print(solve(100, 10 ** 9))
