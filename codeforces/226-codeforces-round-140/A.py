# Codeforces Round #140
# Problem A -- Flying Saucer Segments
n, m = map(int, raw_input().split())
print (pow(3, n, m) + m - 1) % m
