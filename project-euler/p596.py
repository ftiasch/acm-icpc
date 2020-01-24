def count(a, b):
    return (a + b) * (b - a + 1) / 2

def solve(n):
    result = 0
    n *= n
    result, i = 0, 1
    while i <= n:
        j = n / (n / i)
        cnt = count(i, j)
        cnt -= 4 * count((i + 3) / 4, j / 4)
        result += cnt * (n / i)
        i = j + 1
    return result * 8 + 1

print solve(2)
print solve(5)
print solve(100)
print solve(10 ** 4)
print solve(10 ** 8) % (10 ** 9 + 7)
