n, result = 50, -3
for length in (2, 3, 4):
    ways = [0] * (n + 1)
    ways[0] = 1
    for i in range(1, n + 1):
        ways[i] = ways[i - 1]
        if i >= length:
            ways[i] += ways[i - length]
    result += ways[n]
print(result)
