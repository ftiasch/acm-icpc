# Round 1A 2012
# Problem A -- Password Problem
def solve():
    m, n = map(int, raw_input().split())
    p = map(float, raw_input().split())
    ps = [1.0]
    for i in xrange(m):
        ps.append(ps[-1] * p[i])
    result = n + 2 # give up
    for d in xrange(m + 1):
        expected = (d + n - (m - d) + 1) \
                + (1 - ps[m - d]) * (n + 1)
        result = min(result, expected)
    return result

test_count = input()
for test in xrange(test_count):
    print "Case #%d: %.6f" %(test + 1, solve())
