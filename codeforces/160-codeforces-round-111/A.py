# Codeforces Round #111
# Problem A -- Twins 
n = input()
a = sorted(map(int, raw_input().split()), reverse = True)
s = sum(a)
i, c = 0, 0
while c <= s - c:
    c += a[i]
    i += 1
print i
