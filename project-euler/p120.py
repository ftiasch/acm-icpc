# a + 1
# a^2 + 2a + 1 = 2a + 1
# 2a^2 + 2a = 3a + 1
# ...

# a - 1
# a^2 - 2a + 1 = -2a + 1
# (-2a + 1) (a - 1) = 3a - 1
# (3a - 1)(a - 1) = -4a + 1

def solve(a):
    result = 0
    for n in range(1, 2 * a):
        r = n * a + 1
        if n % 2 == 0:
            r -= n * a - 1
        else:
            r += n * a - 1
        r %= a * a
        if r < 0:
            r += a * a
        result = max(result, r)
    return result

result = 0
for a in range(3, 1001):
    result += solve(a)
print(result)
