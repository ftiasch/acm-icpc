# Codeforces Round #139
# Problem A -- Dice Tower
from sys import exit
n = input()
top = input()
for i in xrange(n):
    top = 7 - top
    a, b = map(int, raw_input().split())
    sides = [a, b, 7 - a, 7 - b]
    choices = [i for i in xrange(1, 7) if i not in sides and i != top]
    if len(choices) > 1:
        print "NO"
        exit(0)
    top = choices[0]
print "YES"
