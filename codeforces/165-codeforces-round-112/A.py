# Codeforces Round #112
# Problem A -- Supercentral Point
n = input()
points = [map(int, raw_input().split()) for i in xrange(n)]
result = 0
for p in points:
    comprators = [lambda q: p[0] == q[0] and p[1] < q[1],
                  lambda q: p[0] == q[0] and p[1] > q[1],
                  lambda q: p[1] == q[1] and p[0] < q[0],
                  lambda q: p[1] == q[1] and p[0] > q[0]]
    if all([filter(c, points) for c in comprators]):
        result += 1
print result
