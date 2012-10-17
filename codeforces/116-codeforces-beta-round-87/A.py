# Codeforces Beta Round #87
# Problem A -- Tram
capacity, current = 0, 0
for i in xrange(input()):
    a, b = map(int, raw_input().split())
    current += b - a
    capacity = max(capacity, current)
print capacity
