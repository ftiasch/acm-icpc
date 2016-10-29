def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

n = 10 ** 9
p = 2
count = sum = 0
while count < 40:
    if p not in (2, 5) and is_prime(p) and (pow(10, n, p * 9) - 1) / 9 % p == 0:
        count += 1
        sum += p
    p += 1
print(sum)
