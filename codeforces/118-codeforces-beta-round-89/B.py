# Codefores Beta Round #89
# Problem B -- Present from Lena
gen = lambda n: range(0, n) + range(n, -1, -1)
n = input()
for i in gen(n):
    print " " * ((n - i) << 1) + " ".join(map(str, gen(i)))
