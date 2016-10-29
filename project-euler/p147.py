def count(n, m):
    result = 0
    for i in range(n):
        for j in range(m):
            result += (n - i) * (m - j)
    check = lambda x, y: 0 <= x <= 2 * n and 0 <= y <= 2 * m
    height = [0] * (n + m)
    for i in range(m, - n - 1, -1):
        stack, total = [], 0
        for j in range(n + m):
            y, x = j + i, j - i
            if check(x, y) and check(x - 1, y + 1) and check(x, y + 2) and check(x + 1, y + 1):
                height[j] += 1
            else:
                height[j] = 0
            first = j
            while len(stack) > 0 and stack[-1][0] >= height[j]:
                total -= stack[-1][0] * (first - stack[-1][1])
                first = stack[-1][1]
                stack.pop()
            total += height[j] * (j - first + 1)
            stack.append((height[j], first))
            result += total
    return result

def solve(n, m):
    result = 0
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            result += count(i, j)
    return result

print(solve(3, 2))
print(solve(47, 43))
