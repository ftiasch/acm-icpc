from collections import defaultdict

def solve(n):
    ways = {(0, 0) : 1}
    for _ in range(n):
        new_ways = defaultdict(lambda: 0)
        for state, way in ways.items():
            absent, late = state
            if late == 0:
                new_ways[(0, 1)] += way
            new_ways[(0, late)] += way  # on time
            if absent < 2:
                new_ways[(absent + 1, late)] += way
        ways = new_ways
    return sum(ways.values())

print(solve(4))
print(solve(30))
