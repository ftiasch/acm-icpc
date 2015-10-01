import sys

def solve(n, m, k):
    result = 0
    for i in xrange(1, n + 1):
        for j in xrange(1, m + 1):
            if 2 * (i + j) <= k:
                result += (n - i + 1) * (m - j + 1)
    return result

for line in sys.stdin.readlines():
    print(solve(*map(int, line.rstrip().split())))
