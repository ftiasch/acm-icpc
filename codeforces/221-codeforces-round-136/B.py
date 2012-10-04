def get_divisors(n):
    divisors = []
    i = 1
    while i * i <= n:
        if n % i == 0:
            divisors.append(i)
            if i * i != n:
                divisors.append(n / i)
        i += 1
    return divisors

n = input()
digits = set(map(int, str(n)))
result = 0
for d in get_divisors(n):
    found = False
    for i in map(int, str(d)):
        found |= i in digits
    if found:
        result += 1
print result
