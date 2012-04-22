#!/usr/bin/env python
from sys import argv
from random import randint

rand = lambda n: randint(n - n / 3, n)

limit = int(argv[1])

n = rand(limit)
m = rand(limit)
print n, m, rand(n), randint(1, n), randint(1, n)
