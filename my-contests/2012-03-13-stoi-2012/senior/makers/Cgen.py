#!/usr/bin/env python
from sys import argv
from random import randint, choice

rand = lambda n: randint(n - n / 3, n)

limit = int(argv[1])
result = int(argv[2])

n = rand(limit)
if result == 0:
    print "".join([choice(['a', 'b', 'c'])] * n)
else:
    print "".join([choice(['a', 'b', 'c']) for i in xrange(n)])
