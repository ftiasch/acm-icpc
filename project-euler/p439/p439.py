mod = 10 ** 9

def divisors(n):
    global mod
    result, i = 0, 1
    while i <= n:
        j = n / (n / i) + 1
        result += (n / i) * (i + j - 1) * (j - i) / 2
        i = j
    return result % mod

def solve(n):
    if n in memory:
        return memory[n]
    result = divisors(n)
    result *= result
    result %= mod
    i = 2
    while i <= n:
        j = n / (n / i) + 1
        result += mod - solve(n / i) * (i + j - 1) * (j - i) / 2 % mod
        if result >= mod:
            result -= mod
        i = j
    memory[n] = result
    return result

memory = {}
print solve(3)
print solve(10 ** 3)
print solve(10 ** 5) % 1000000000
print solve(10 ** 11) % 1000000000
