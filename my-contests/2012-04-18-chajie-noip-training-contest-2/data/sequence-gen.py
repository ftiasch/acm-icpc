#!/usr/bin/env python
from sys import argv
from random import randint

N = int(argv[1])
print randint(0, 10002), randint(0, N)
