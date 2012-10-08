# Codeforces Round #109
# Problem A -- I_love_%username%
n = input()
a = map(int, raw_input().split())
result = 0
for i in xrange(1, n):
    if a[i] > max(a[:i]) or a[i] < min(a[:i]):
        result += 1
print result
