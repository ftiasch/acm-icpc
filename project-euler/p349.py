STEP = 104
# DIRS = [(1, 0), (0, 1), (-1, 0), (0, -1)]
# x, y, d, blacks = 0, 0, 0, set()
# for i in range(100000):
#     p = (x, y)
#     if p in blacks:
#         blacks.remove(p)
#         d += 1
#     else:
#         blacks.add(p)
#         d += 3
#     d %= 4
#     dx, dy = DIRS[d]
#     x, y = x + dx, y + dy
#     if (i + 1) % STEP == (10 ** 18) % STEP:
#         print len(blacks) % 12 # = 4
#         print i / STEP - len(blacks) / 12 # = 36
print ((10 ** 18) / STEP - 36) * 12 + 4
