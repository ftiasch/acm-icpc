from math import floor
from operator import mul
from functools import reduce

def solve(m):
    s = m * (m + 1) / 2
    return floor(reduce(mul, [(i / s * m) ** i for i in range(1, m + 1)]))

print(solve(10))
print(sum(solve(m) for m in range(2, 16))
