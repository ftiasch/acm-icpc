# Codeforces Beta Round #91
# Problem A -- Lucky Division
from sys import exit
def lucky(n):
    while n > 0:
        if (n % 10) not in [4, 7]:
            return False
        n /= 10
    return True

n = input()
for i in xrange(1, n + 1):
    if n % i == 0 and lucky(i):
        print "YES"
        exit(0)
print "NO"
