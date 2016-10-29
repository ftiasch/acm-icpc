solutions = []
for x0, y0 in (
        (7, 1),
        (8, 2),
        (13, 5),
        (17, 7),
        (32, 14),
        (43, 19),
        (83, 37),
        (112, 50),
        ):
    x, y = x0, y0
    for _ in range(20):
        assert x * x - 5 * y * y == 44
        if x > 7 and x % 5 == 2:
            solutions.append((x - 7) // 5)
        # solutions.append((x, y))
        x, y = 9 * x + 20 * y, 4 * x + 9 * y
solutions = list(set(solutions))
solutions.sort()
print(sum(solutions[:30])
