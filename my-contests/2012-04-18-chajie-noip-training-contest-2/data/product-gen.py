#!/usr/bin/env python
from sys import argv
from random import randint, shuffle

N = int(argv[1])
n = randint(3, N)
a = [2 * randint(0, 10000) + 1 for _ in xrange(n)]
a[0] += 1
b = [a[i] * a[j] for i in xrange(0, n) for j in xrange(i + 1, n)]
shuffle(b)
print n
print " ".join(map(str, b))
