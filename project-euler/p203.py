def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

def exponent(p, n):
    result = 0
    while n > 0:
        n //= p
        result += n
    return result

def solve(n):
    primes = [p for p in range(2, n) if is_prime(p)]
    frees = set()
    for i in range(n):
        for j in range(i + 1):
            exponents = [exponent(p, i) - exponent(p, j) - exponent(p, i - j) for p in primes]
            if max(exponents) < 2:
                frees.add(tuple(exponents))
    result = 0
    for free in frees:
        product = 1
        for p, e in zip(primes, free):
            if e == 1:
                product *= p
        result += product
    return result

print(solve(8))
print(solve(51))
