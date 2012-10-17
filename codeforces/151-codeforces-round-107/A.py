# Codeforces Round #107
# Problem A -- Soft Drinking
n, k, l, c, d, p, nl, np = map(int, raw_input().split())
print min((k * l / nl, c * d, p / np)) / n
