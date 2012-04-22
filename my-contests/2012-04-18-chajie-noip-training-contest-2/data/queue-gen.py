#!/usr/bin/env python
from sys import argv
from math import log
from random import randint

M = int(argv[1])
m = M - randint(0, M / 5)
print m
q = []
for _ in xrange(m):
    while True:
        t = (1, 1, 2, 2, 3, 4, 4, 4)[randint(0, 7)]
        if t <= 2 or len(q) > 0:
            break
    while True:
        k = randint(0, 10 ** 9)
        if k not in q:
            break
    if t == 1:
        print 1, k
        q.append(k)
    elif t == 2:
        print 2, k
        q.insert(0, k)
    elif t == 3:
        print 3
        q.pop()
    else:
        print 4, randint(1, max(int(log(len(q))), 1)) 
