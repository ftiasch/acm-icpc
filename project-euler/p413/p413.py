import sys

def count(n):
    power = [1]
    for i in xrange(n):
        power.append(power[-1] * (n + 1))
    ways = {(0, 0): 1}
    for i in xrange(n):
        #print >> sys.stderr, n, i
        new_ways = {}
        for k, v in ways.items():
            remainder = k[0]
            substring = k[1]
            for d in xrange(10):
                if i == 0 and d == 0:
                    continue
                new_remainder = 0
                for r in xrange(n):
                    new_remainder += power[(r * 10 + d) % n] * (remainder / power[r] % (n + 1))
                new_remainder += power[d % n]
                new_substring = substring + new_remainder % (n + 1)
                if new_substring <= 1:
                    key = (new_remainder, new_substring)
                    new_ways[key] = new_ways.get(key, 0) + v
        ways = new_ways
    ret = 0
    for k, v in ways.items():
        if k[1] == 1:
            ret += v
    return ret

def solve(n):
    return sum(map(count, xrange(1, n + 1)))

for n in (1, 3, 7, 19):
    print n, solve(n)
