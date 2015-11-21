from fractions import Fraction

def solve(n):
    co = [2 ** i - 1 for i in xrange(n)]
    dp = [0] * (n + 1)
    for i in xrange(1, n):
        for j in xrange(i):
            dp[i + 1] += Fraction(dp[i] + 1 + co[j], i + 1)
        dp[i + 1] += Fraction(dp[i], i + 1)
    return dp[n]

if __name__ == '__main__':
    for n in (4, 10, 30):
        print 'E(%d) = %.2f' %(n, float(solve(n)))
