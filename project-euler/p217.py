from collections import defaultdict

MOD = 3 ** 15

def update(ways, k, v):
    ways[k] = (ways[k] + v) % MOD

def count(n):
    ways = {(0, False) : 1}
    for i in range(n):
        delta = 0
        if i < n // 2:
            delta = 1
        if i >= (n + 1) // 2:
            delta = -1
        weight = pow(10, n - 1 - i, MOD)
        new_ways = defaultdict(lambda: 0)
        for state, way in ways.items():
            s, f = state
            for d in range(int(i == 0), 10):
                ns = s + d * delta
                update(new_ways, (ns, f), way)
                if not f:
                    update(new_ways, (ns, True), way * d * weight)
        ways = new_ways
    return ways[(0, True)]

def solve(n):
    return sum((count(length) for length in range(1, n + 1))) % MOD

for n in (1, 2, 5, 47):
    print(n, solve(n))
