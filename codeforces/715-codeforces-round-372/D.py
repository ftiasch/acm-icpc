def down(x, y):
    return (x, y, x + 1, y)

def right(x, y):
    return (x, y, x, y + 1)

t = input()
n = 0
while 3 ** n <= t:
    n += 1
walls = []
for i in xrange(n):
    if t % 3 < 1:
        walls.append(right(1 + i, 3 + i))
    if i + 1 < n:
        walls.append(down(1 + i, 4 + i))
    if t % 3 < 2:
        walls.append(down(3 + i, 1 + i))
    if i + 1 < n:
        walls.append(right(4 + i, 1 + i))
    t /= 3
walls.append(right(1 + n, 2 + n))
walls.append(right(2 + n, 2 + n))
walls.append(down(2 + n, 1 + n))
walls.append(down(2 + n, 2 + n))
for i in xrange(n - 2):
    walls.append(right(1 + i, 5 + i))
    walls.append(down(5 + i, 1 + i))
assert t == 0
print n + 3, n + 3
print len(walls)
for wall in walls:
    print ' '.join(map(str, wall))
# m = n + 3
# grid = [[' '] * (2 * m + 1) for _ in xrange(2 * m + 1)]
# for i in xrange(m + 1):
#     for j in xrange(m + 1):
#         grid[2 * i][2 * j] = '.'
# for wall in walls:
#     if wall[0] == wall[2]:
#         grid[2 * wall[0] - 1][2 * wall[1]] = '|'
#     else:
#         grid[2 * wall[0]][2 * wall[1] - 1] = '-'
# print "\n".join(map(lambda r: ''.join(r), grid))
