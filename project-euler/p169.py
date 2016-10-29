def solve(n):
    k = 0
    while 2 ** (k + 1) <= n:
        k += 1
    ways = [1, 0, 0, 0]
    for i in range(k, -1, -1):
        new_ways = [0] * 4
        add = n // (2 ** i) % 2
        for r in range(4):
            for u in range(3):
                rr = r * 2 + add - u
                if rr < 4:
                    new_ways[rr] += ways[r]
        ways = new_ways
    return ways[0]

print(solve(10))
print(solve(10 ** 25))
