# Codeforces Round #140
# Problem B -- Effective Approach
n = input()
positions = [0] * n
for i, p in enumerate(map(int, raw_input().split())):
    positions[p - 1] = i
m = input()
a, b = 0, 0
for k in map(int, raw_input().split()):
    a += 1 + positions[k - 1]
    b += n - positions[k - 1]
print a, b
