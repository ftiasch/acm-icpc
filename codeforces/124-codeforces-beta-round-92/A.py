# Codeforces Beta Round #92 
# Problem A -- The number of positions
n, a, b = map(int, raw_input().split())
print min(n - a, b + 1)
