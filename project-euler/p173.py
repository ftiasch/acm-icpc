def solve(n):
    result = 0
    for i in range(2, n + 1, 2):
        j = i + 2
        while i * j <= n:
            result += 1
            j += 2
    return result

print(solve(100))
print(solve(10 ** 6))
