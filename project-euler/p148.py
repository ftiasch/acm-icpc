def dfs(digits, memo, less, pos):
    if pos == -1:
        return int(less)
    key = (less, pos)
    if key in memo:
        return memo[key]
    result = 0
    for d in range(less and 6 or digits[pos], -1, -1):
        result += dfs(digits, memo, less, pos - 1) * (d + 1)
        less = True
    memo[key] = result
    return result

def solve(n):
    digits = []
    while n > 0:
        digits.append(n % 7)
        n //= 7
    memo = {}
    return dfs(digits, memo, False, len(digits) - 1)

for n in (100, 10 ** 9):
    print(n, solve(n))
