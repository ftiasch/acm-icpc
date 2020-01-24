from fractions import gcd

def factor(n):
    primes, p = [], 2
    while p * p <= n:
        if n % p == 0:
            primes.append(p)
        while n % p == 0:
            n //= p
        p += 1
    if n > 1:
        primes.append(n)
    return primes

def dfs(m, primes, index, mu, d):
    if index < len(primes):
        return dfs(m, primes, index + 1, mu, d) + dfs(m, primes, index + 1, -mu, d * primes[index])
    else:
        result, md = 0, m // d
        for r in range(3):
            if r <= md and (r * d - 2 * m) % 3 == 0:
                result += (md - r) // 3 + 1
        return mu * result

def solve(m):
    assert m % 2 == 1
    m = (m + 3) // 2
    return dfs(m, factor(m), 0, 1, 1)
    # result = 0
    # for i in range(m + 1):
    #     if gcd(i, m) == 1 and (i - 2 * m) % 3 == 0:
    #         result += 1
    # return result

print(solve(11))
print(solve(1000001))
print(solve(12017639147))
