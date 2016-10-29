from fractions import gcd

def exponent(p, n):
    result = 0
    while n > 0:
        n //= p
        result += n
    return result

def _gcd(a, b):
    if b == 0:
        assert a == 1
        return (1, 0)
    y, x = _gcd(b, a % b)
    return (x, y - a // b * x)

def inverse(a, mod):
    assert gcd(a, mod) == 1
    x, y = _gcd(a, mod)
    inv = (x % mod + mod) % mod
    assert a * inv % mod == 1
    return inv

def prepare(p, mod):
    table = [0] * mod
    table[0] = 1
    for i in range(1, mod):
        table[i] = table[i - 1]
        if i % p != 0:
            table[i] = table[i] * i % mod
    return table

def factorial(table, p, mod, n):
    if n < p:
        return table[n]
    return factorial(table, p, mod, n // p) * pow(table[mod - 1], n // mod, mod) * table[n % mod] % mod

def solve(n):
    cnt_2, cnt_5 = exponent(2, n), exponent(5, n)
    rem_2 = factorial(prepare(2, 2 ** 5), 2, 2 ** 5, n) * pow(inverse(5, 2 ** 5), cnt_5, 2 ** 5) % (2 ** 5)
    rem_5 = factorial(prepare(5, 5 ** 5), 5, 5 ** 5, n) * pow(inverse(2, 5 ** 5), cnt_2, 5 ** 5) % (5 ** 5)
    return (rem_2 * (5 ** 5) * inverse(5 ** 5, 2 ** 5) + rem_5 * (2 ** 5) * inverse(2 ** 5, 5 ** 5)) * pow(2, cnt_2 - cnt_5, 10 ** 5) % (10 ** 5)

for n in (9, 10, 20, 10 ** 12):
    print(n, solve(n))
