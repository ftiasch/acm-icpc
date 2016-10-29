import sys

result = 0
for line in open('p105_sets.txt').readlines():
    a = sorted(map(int, line.split(',')))
    n = len(a)
    valid = True
    for i in xrange(2, n + 1):
        if sum(a[:i]) < sum(a[-(i - 1):]):
            valid = False
    sums = [0]
    for x in a:
        sums += map(lambda y: x + y, sums)
    if len(set(sums)) != 2 ** n:
        valid = False
    if valid:
        result += sum(a)
print result
