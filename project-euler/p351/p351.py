memory = {}

def count_coprime(n):
    if n in memory:
        return memory[n]
    if n == 1:
        return 1
    ret, i = n * n, 2
    while i <= n:
        i_prime = n / (n / i) + 1
        ret -= (i_prime - i) * count_coprime(n / i)
        i = i_prime
    memory[n] = ret
    return ret

def h(n):
    return (n * n - count_coprime(n) + n - 1) * 3

for n in [5, 10, 1000, 100000000]:
    print n, h(n)
