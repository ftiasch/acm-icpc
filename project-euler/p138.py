# (5b \pm 4)^2 + 4 = 20L^2
# N(4, 1) = -4
# N(9, 2) = 1

x, y, count, result = 4, 1, 0, 0
while count < 12:
    x, y = 9 * x + 40 * y, 2 * x + 9 * y
    if x % 5 == 4 or x % 5 == 1:
        # b = (x % 5 == 4 and x - 4 or x + 4) // 5
        # L = y
        count += 1
        result += y
print(result)
