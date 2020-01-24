# mod 10
# mod 1000
# ...
# mod 10^{2k + 1}

# 19

candidates = []
for r in range(0, 10 ** 7, 10):
    r2 = r * r % (10 ** 7)
    if r2 // 100 % 10 == 9 and r2 // 10000 % 10 == 8 and r2 // 1000000 % 10 == 7:
        candidates.append(r)
for h in range(10 ** 3):
    for r in candidates:
        x = h * (10 ** 7) + r
        x2, valid = x * x, True
        x2 //= 10
        for i in range(9, 0, -1):
            if x2 // 10 % 10 != i:
                valid = False
                break
            x2 //= 100
        if valid and x2 == 0:
            print(x, x * x)
