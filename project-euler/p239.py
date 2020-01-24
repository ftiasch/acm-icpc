def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

primes = [p for p in range(2, 100) if is_prime(p)]

n, m = len(primes), 100

binom = [[0] * (m + 1) for _ in range(m + 1)]
for i in range(m + 1):
    binom[i][0] = 1
    for j in range(1, i + 1):
        binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j]

factorial = [1] * (m + 1)
for i in range(1, m + 1):
    factorial[i] = factorial[i - 1] * i

correct = n - 22

result = 0
for i in range(n - correct + 1):
    result += (-1) ** i * binom[n][correct] * binom[n - correct][i] * factorial[m - correct - i]

print(result / factorial[m])
