from fractions import gcd
from math import log

def d(n):
    result= (0., 1)
    for k in range(1, n + 1):
        result = max(result, (k * log(n / k), k))
    k = result[1]
    k //= gcd(n, k)
    for p in (2, 5):
        while k % p == 0:
            k //= p
    return k == 1 and -n or n

def solve(n):
    result = 0
    for n in range(5, n + 1):
        result += d(n)
    return result

print(solve(100))
print(solve(10000))
