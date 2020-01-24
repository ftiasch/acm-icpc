from collections import defaultdict

def solve(dices, sides, top, target):
    binom = [[0] * (dices + 1) for _ in range(dices + 1)]
    for i in range(dices + 1):
        binom[i][0] = 1
        for j in range(1, i + 1):
            binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j]
    ways = { (dices, top, target) : 1 }
    for side in range(sides, 0, -1):
        new_ways = defaultdict(lambda: 0)
        for state, way in ways.items():
            d, t, s = state
            for count in range(d + 1):
                ns = s - side * min(count, t)
                if ns >= 0:
                    new_ways[(d - count, max(t - count, 0), ns)] += way * binom[d][count]
        ways = new_ways
    return ways[(0, 0, 0)]

print(solve(5, 6, 3, 15))
print(solve(20, 12, 10, 70))
