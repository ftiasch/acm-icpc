from math import sqrt, log, ceil

n, result = 2011, 0
for p in xrange(1, n + 1):
    for q in xrange(p + 1, n + 1):
        if p + q <= n:
            r = sqrt(q) - sqrt(p)
            if r < 1:
                min_n = int(ceil(-2011 * log(10) / log(r) / 2))
                result += min_n
print result
