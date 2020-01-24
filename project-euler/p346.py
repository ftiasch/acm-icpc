def solve(n):
    b, s = 2, set()
    while b * b + b + 1 <= n:
        t = b * b + b + 1
        while t <= n:
            s.add(t)
            t = t * b + 1
        b += 1
    return 1 + sum(s)

print solve(1000)
print solve(10 ** 12)
