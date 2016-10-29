from fractions import gcd

def update(x, a):
    if x is None:
        return a
    sx, sa = sum(x), sum(a)
    if sx != sa:
        return x if sx < sa else a
    i, j = 0
    while i < len(x) and j < len(a):
        if x[i] != a[j]:
            return x if x[i] > a[j] else a
        i += 1
        j += 1
    return x

def solve(p, q):
    g = gcd(p, q)
    p //= g
    q //= g
    if q == 0:
        return None
    if p == 0 and q == 1:
        return []
    assert(p != 0)
    result, k = None, 0
    while k * p <= q:
        if p - q + k * p >= 0:
            n = solve(p - q + k * p, q - k * p)
            if n is not None:
                n = n + [k]
                result = update(result, n)
        k += 1
    return result

print(solve(17, 13))
print(solve(987654321, 123456789))
