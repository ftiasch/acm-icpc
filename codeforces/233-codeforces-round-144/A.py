# Codeforces Round #144
# Problem A -- Perfect Permutation
n = input()
if n % 2 == 0:
    print " ".join(map(str, [(i ^ 1) + 1 for i in xrange(n)]))
else:
    print -1
