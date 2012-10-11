# Codeforces Beta Round #86
# Problem A -- Cifera
k = input()
l = input()
result = -1
while l % k == 0:
    l /= k
    result += 1
if l == 1:
    print "YES"
    print result
else:
    print "NO"
