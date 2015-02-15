def accumulate(array):
    s, ss = 0, []
    for a in array:
        s += a
        ss.append(s)
    return ss

def count_inner(block, m):
    ret, s, count = 0, 0, [1] + [0] * (m - 1)
    for a in block:
        s += a
        if s >= m:
            s -= m
        ret += count[s]
        count[s] += 1
    return ret

def count_cross(block, m):
    count = [0] * m
    for s in accumulate(block):
        count[s % m] += 1
    ret = 0
    for s in accumulate(reversed(block)):
        ret += count[(m - s % m) % m]
    return ret

def solve(n, m):
    s, a = 1, [1]
    for i in xrange(1, 6 * m):
        t = s % (i + 1)
        a.append(t % m)
        s += (i + 1) * t
    b, r = n / (6 * m), n % (6 * m)
    ret = 0
    count = [0] * m
    for s in accumulate(reversed(a)):
        count[s % m] += b
    for s in accumulate(a[:r]):
        ret += count[(m - s % m) % m]
    ret += count_cross(a, m) * b * (b - 1) / 2
    ret += count_inner(a, m) * b
    ret += count_inner(a[:r], m)
    return ret

for n, m in ((10, 10), (10000, 1000), (10 ** 12, 1000000)):
    print(n, m, solve(n, m))
