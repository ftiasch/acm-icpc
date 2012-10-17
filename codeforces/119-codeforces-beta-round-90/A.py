# Codeforces Beta Round #90
# Problem A -- Epic Game
from fractions import gcd
a, b, n = map(int, raw_input().split())
t = 1
while n > 0:
    n -= gcd(a, n)
    t ^= 1
    a, b = b, a
print t
