def solve(n):
    binom = [[0] * 7 for i in range(n + 1)]
    for i in range(n + 1):
        binom[i][0] = 1
        for j in range(1, 7):
            binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j]
    result = 0
    # {1}
    for dt in range(n // 3 + 1):
        a = binom[(n - 3 * dt) // 2][3]
        result += a
        if dt != 0:
            result += a
    # r
    result += 2 * binom[n // 6][1]
    # r^2
    result += 2 * binom[n // 3][2]
    # r^3
    result += binom[n // 2][3]
    # f
    for x in range(1, n // 4 + 1):
        result += 3 * ((n - 4 * x) // 2)
    # fr
    for dt in range(n // 3 + 1):
        a = 0
        left = (n - 3 * dt) // 2
        for i in range(1, left // 2 + 1):
            a += left - 2 * i
        a *= 3
        result += a
        if dt != 0:
            result += a
    # assert result % 12 == 0
    return result // 12

print(solve(6))
print(solve(12))
print(solve(100))
print(solve(55106))
