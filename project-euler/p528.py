MOD = 10 ** 9 + 7

def solve(n, k, b):
    mb = k * (b - 1)
    knapsack = [[0] * (mb  + 1) for _ in range(k + 1)]
    knapsack[0][0] = 1
    for i in range(k):
        for j, w in enumerate(knapsack[i]):
            if w > 0:
                for d in range(b):
                    knapsack[i + 1][j + d] += w
                    knapsack[i + 1][j + d] %= MOD
    mk = k * b
    up = min(mk, n // (b ** k))
    ways = [[0] * (up + 1)]
    ways[0][up] = 1
    for i in range(k, -1, -1):
        rem = 0
        if i > 0:
            rem = n // (b ** (i - 1)) % b
        new_ways = [[0] * (mk + 1) for _ in range(k - i + 2)]
        for free in range(k - i + 1):
            for left, w in enumerate(ways[free]):
                if w == 0:
                    continue
                for use in range(left + 1):
                    if i > 0 and use > 0 and use - 1 <= mb:
                        new_ways[free][min((left - use) * b + rem, mk)] += w * knapsack[free][use - 1]
                        new_ways[free][min((left - use) * b + rem, mk)] %= MOD
                    if use <= mb:
                        new_ways[free + 1][min((left - use) * b + rem, mk)] += w * knapsack[free][use]
                        new_ways[free + 1][min((left - use) * b + rem, mk)] %= MOD
        ways = new_ways
    return sum(map(sum, ways)) % MOD

print(solve(14, 3, 2))
print(solve(200, 5, 3))
print(solve(1000, 10, 5))
result = 0
for k in range(10, 16):
    result += solve(10 ** k, k, k)
print(result % MOD)
