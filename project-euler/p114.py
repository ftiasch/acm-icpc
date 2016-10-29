ways = [1]
n = 0
while ways[-1] <= 1000000:
    n += 1
    new_ways = ways[n - 1]
    for i in range(50, n + 1):
        new_ways += ways[max(n - i - 1, 0)]
    ways.append(new_ways)
print(n)
