#!/usr/bin/env python
from sys import argv
from random import randint

rand = lambda n: randint(n - n / 3, n)

limit = int(argv[1])
value = 10 ** 4 

n = m = limit
print n, m
print " ".join(map(str, (randint(0, value) for i in xrange(n))))
for i in xrange(m):
    a = randint(1, n - n / 4)
    b = randint(a + n / 4, n)
    print a, b, randint(0, value)
