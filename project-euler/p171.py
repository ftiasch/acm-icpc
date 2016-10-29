from collections import defaultdict

# (cnt_0, # cnt = 0, # cnt = 1, # cnt = 2)

def brute(n):
    result = 0
    for t in range(10 ** (n - 1), 10 ** n):
        cnt = [0] * 10
        for d in str(t):
            cnt[int(d)] += 1
        if max(cnt) <= 3:
            result += 1
    return result

def solve(n):
    ways = { (9, 1, 0) : 9 }
    for i in range(1, n):
        new_ways = defaultdict(lambda: 0)
        for state, way in ways.items():
            cc0, cc1, cc2 = state
            if cc0 > 0:
                new_ways[(cc0 - 1, cc1 + 1, cc2)] += way * cc0
            if cc1 > 0:
                new_ways[(cc0, cc1 - 1, cc2 + 1)] += way * cc1
            if cc2 > 0:
                new_ways[(cc0, cc1, cc2 - 1)] += way * cc2
        ways = new_ways
    return sum(ways.values())

print(solve(18))
