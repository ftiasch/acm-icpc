# Codeforces Round #132
# Problem A -- Bicycle Chain
n = input()
a = map(int, raw_input().split())
m = input()
b = map(int, raw_input().split())
maximum = 0
for i in a:
    for j in b:
        if j % i == 0:
            maximum = max(maximum, j / i)
result = 0
for i in a:
    for j in b:
        if j % i == 0 and j / i == maximum:
            result += 1
print result
