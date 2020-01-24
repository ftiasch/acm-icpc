from collections import defaultdict

def solve(n):
    assert n % 5 == 0
    ways = { (0, 0, 0, 0, 0) : 1 }
    for _ in range(n):
        # print(_)
        new_ways = defaultdict(lambda: 0)
        for s, way in ways.items():
            new_ways[(s[4] + 1, s[0], s[1], s[2], s[3])] += way
            new_ways[(s[1], s[2], s[3], s[4], s[0] + 1)] += way
        ways = new_ways
    result = 0
    for s, way in ways.items():
        if s[0] == s[1] == s[2] == s[3] == s[4]:
            result += way
    return result

print(solve(25))
print(solve(70))
