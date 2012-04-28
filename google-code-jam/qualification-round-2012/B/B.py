# Qualification Round 2012
# Problem B -- Dancing With the Googlers
INF = 1000000000

test_count = input()
for test in xrange(test_count):
    data = map(int, raw_input().split())
    n, m, p, t = data[0], data[1], data[2], data[3:]
    valid = [[[False, False], [False, False]] for i in xrange(31)]
    for a in xrange(11):
        for b in xrange(11):
            for c in xrange(11):
                valid[a + b + c][max(a, b, c) - min(a, b, c) >= 2][max(a, b, c) >= p] = True
    dp = [-INF for i in xrange(m + 1)]
    dp[0] = 0
    for ti in t:
        new_dp = [-INF for i in xrange(m + 1)]
        for i in reversed(xrange(m + 1)):
            for x in xrange(2):
                for y in xrange(2):
                    if i >= x and valid[ti][x][y]:
                        new_dp[i] = max(new_dp[i], dp[i - x] + y)
        dp = new_dp
    print "Case #%d: %d" %(test + 1, dp[m])
