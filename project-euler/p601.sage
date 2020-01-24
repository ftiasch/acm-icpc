def solve(s, n):
    l = 1
    for i in range(1, s + 1):
        l = lcm(l, i)
    n = (n - 2) // l
    g = gcd(l, s + 1)
    d = (s + 1) // g
    return n - n // d

print solve(3, 14)
print solve(6, 10 ** 6)
result = 0
for i in range(1, 32):
    result += solve(i, 4 ** i)
print result
