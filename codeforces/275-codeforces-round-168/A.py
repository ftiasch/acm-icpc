# Codeforces Round #168
# Problem A -- Lights Out
def count(a, i, j):
    total = 1
    for x, y in ((-1, 0), (0, -1), (0, 0), (0, 1), (1, 0)):
        if 0 <= i + x < 3 and 0 <= j + y < 3:
            total += a[i + x][j + y]
    return str(total % 2)
a = [map(int, raw_input().split()) for _ in xrange(3)]
for i in xrange(3):
    print ''.join([count(a, i, j) for j in xrange(3)])
