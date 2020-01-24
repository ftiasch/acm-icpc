from fractions import Fraction
from math import floor, sqrt

def count(m):
    x = floor(sqrt(m))
    while (x + 1) * x <= m:
        x += 1
    return x - 1

def proportion(m):
    t = 0
    while 4 ** (t + 1) - 2 ** (t + 1) <= m:
        t += 1
    return Fraction(t, count(m))

for m in (5, 10, 15, 20, 25, 30, 180, 185):
    print(m, proportion(m))

t = 1
while True:
    next = 4 ** (t + 1) - 2 ** (t + 1)
    low = 4 ** t - 2 ** t
    high = next
    while low < high:
        middle = (low + high) // 2
        if t * 12345 < count(middle):
            high = middle
        else:
            low = middle + 1
    if high < next:
        break
    t += 1
print(high, proportion(high))
