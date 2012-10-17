# Codeforces Beta Round #95
# Problem B -- Opposites Attract
n = input()
a = map(int, raw_input().split())
result = 0 
for i in xrange(1, 11):
    result += a.count(i) * a.count(-i)
result += a.count(0) * (a.count(0) - 1) / 2
print result
