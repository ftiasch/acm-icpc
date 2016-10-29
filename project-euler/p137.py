from math import sqrt

# https://www.alpertron.com.ar/QUAD.HTM

count, x, y = 0, 13, 29
ns = []
for x0, y0 in ((1, 1), (13, 29), (1, -1), (5, 11), (2, 4)):
    count, x, y = 0, x0, y0
    while count < 15:
        x, y = 9 * x + 4 * y, 20 * x + 9 * y
        if (y - 1) % 5 == 0:
            count += 1
            ns.append((y - 1) // 5)
ns = list(set(ns))
ns.sort()
print(ns[15 - 1])
