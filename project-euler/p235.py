def check(n, r):
    s = 0
    for k in range(n, 0, -1):
        s *= r
        s += (900 - 3 * k)
    return s

def solve(n, a):
    def f(r):
        return check(n, r) - a

    low = 1.
    high = 1.01
    for _ in range(5000):
        x = .5 * (low + high)
        if f(low) * f(x) < 0:
            high = x
        else:
            low = x
    print(check(n, x))
    return x

print(solve(5000, -6 * 10 ** 11))
