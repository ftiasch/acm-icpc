from fractions import Fraction

def p(n, k, r):
    return float((n + 1) * r) / (k + 1)

def solve(n):
    dp = float(n + 1) / 2
    for i in xrange(1, n):
        low, high = 0, n - i
        while low < high:
            middle = low + high + 1 >> 1
            if p(n, n - i, middle) <= dp:
                low = middle
            else:
                high = middle - 1
        dp = (float((n + 1) * (low + 1) * low) / (n - i + 1) / 2 + (n - i - low) * dp) / (n - i)
    return dp

for n in (3, 4, 10, 10 ** 6):
    print n, '%.10f' %(float(solve(n)))
