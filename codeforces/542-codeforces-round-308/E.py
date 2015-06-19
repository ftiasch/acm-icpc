e = raw_input()

n, result = len(e), eval(e)
for i in xrange(-1, n):
    if i == -1 or e[i] == '*':
        for j in xrange(i + 1, n + 1):
            if j == n or e[j] == '*':
                result = max(result, eval(e[:i + 1] + '(' + e[i + 1:j] + ')' + e[j:]))
print(result)
