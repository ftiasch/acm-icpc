#   nx + ny = xy
# (x - n) (y - n) = n^2
def count(n):
    result, p = 1, 2
    while p * p <= n:
        if n % p == 0:
            e = 0
            while n % p == 0:
                e += 1
                n /= p
            result *= e + e + 1
        p += 1
    if n > 1:
        result *= 3
    return (result + 1) / 2

n = 1
while count(n) <= 1000:
    n += 1
print(n)
