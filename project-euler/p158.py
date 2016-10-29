def count(n, color):
    ways = [0] * (n + 1)
    ways[0] = 1
    for i in range(n):
        for j in range(n, 0, -1):
            ways[j] += color * ways[j - 1]
    return ways

def solve(n):
    ways_1 = count(n, 1)
    ways_2 = count(n, 2)
    ways = [0] * (n + 1)
    for i in range(n + 1):
        ways[i] = ways_2[i] - (i + 1) * ways_1[i]
    return ways

print(max(solve(26)))
