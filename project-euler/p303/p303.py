import sys

def recur(pred, rr):
    (c, r) = pred[rr]
    if r == 0:
        return c
    return recur(pred, r) * 10 + c

def solve(m):
    pred, head, queue = [None] * m, 0, [0]
    while head < len(queue):
        r = queue[head]
        head += 1
        for c in (0, 1, 2):
            if r == 0 and c == 0:
                continue
            rr = (r * 10 + c) % m
            if not pred[rr]:
                pred[rr] = (c, r)
                if rr == 0:
                    return recur(pred, 0)
                queue.append(rr)

for n in (2, 3, 7, 42, 89):
    print n, solve(n)
result = 0
for n in xrange(1, 10000 + 1):
    #print >> sys.stderr, n, solve(n)
    result += solve(n) / n
print result
