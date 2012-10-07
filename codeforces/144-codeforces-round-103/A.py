# Codeforces Round #103
# Problem A -- Arrival of the General
n = input()
a = map(int, raw_input().split())
result = 0
i = 0
while a[i] != max(a):
    i += 1
while i > 0:
    result += 1
    a[i], a[i - 1] = a[i - 1], a[i]
    i -= 1
i = -1
while a[i] != min(a):
    i -= 1
while i < -1:
    result += 1
    a[i], a[i + 1] = a[i + 1], a[i]
    i += 1
print result
