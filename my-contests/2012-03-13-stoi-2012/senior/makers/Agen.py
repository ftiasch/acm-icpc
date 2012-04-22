#!/usr/bin/env python
from sys import argv
from random import randint

rand = lambda n: randint(n - n / 3, n)

n = rand(int(argv[1]))
print n
print " ".join(map(str, (rand(10 ** 9) for i in xrange(n))))
