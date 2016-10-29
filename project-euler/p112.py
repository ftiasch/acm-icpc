def check(n):
    digits = list(str(n))
    return sorted(digits) == digits or sorted(digits) == list(reversed(digits))

n, m = 1, 0
while m * 100 != n * 99:
    n += 1
    if not check(n):
        m += 1
print(n)
