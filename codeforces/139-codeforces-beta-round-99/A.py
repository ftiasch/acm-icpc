# Codeforces Beta Round #99
# Problem A -- Petr and Book
from sys import exit
n = input()
a = list(enumerate(map(int, raw_input().split())))
while n > 0:
    if a[0][1] >= n:
        print a[0][0] + 1
        exit(0)
    n -= a[0][1]
    a = a[1:] + [a[0]]
