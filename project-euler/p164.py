def count(memo, n, d1, d0):
    if n == 0:
        return 1
    key = (n, d1, d0)
    if key in memo:
        return memo[key]
    result = 0
    for d in range(0, 10 - d1 - d0):
        result += count(memo, n - 1, d0, d)
    memo[key] = result
    return result

memo, result = {}, 0
for d in range(1, 10):
    result += count(memo, 19, 0, d)
print(result)
