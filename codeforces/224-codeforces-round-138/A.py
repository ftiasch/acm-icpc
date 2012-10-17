# Codeforces Round #138
# Problem A -- Parallelepiped
a, b, c = map(int, raw_input().split())
for i in xrange(1, a + 1):
    if a % i == 0 and b % i == 0 and a / i * b / i == c:
        print 4 * (i + a / i + b / i)
