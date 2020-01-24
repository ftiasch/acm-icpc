from collections import defaultdict

def solve(n):
    k = 0
    while 2 ** k <= 4 * n:
        k += 1
    ways = defaultdict(lambda: 0)
    ways[tuple([1] + [0] * 7)] = 1
    for i in reversed(range(k)):
        new_ways = defaultdict(lambda: 0)
        up = (n >> i & 1) * 4
        for dp, way in ways.items():
            for digit in range(2):
                new_dp = [0] * 8
                for left in range(8):
                    if dp[left] == 0:
                        continue
                    for use in range(up + 1):
                        new_left = (left + digit - use) * 2
                        if 0 <= new_left < 8:
                            new_dp[new_left] ^= 1
                new_ways[tuple(new_dp)] += way
        ways = new_ways

    result = 0
    for dp, way in ways.items():
        if dp[0] == 1:
            result += way
    print n, pow(5, bin(n).count('1')) == result
    return result

print(solve(3))
print(solve(10))
print(solve(100))

result = 0
for i in range(1, 19):
    result += solve(10 ** i)
print(result)
