# Codeforces Round #106
# Problem A -- Business trip
k, result = input(), 0
a = sorted(map(int, raw_input().split()))
try:
    while k > 0:
        result += 1
        k -= a.pop()
    print result
except:
    print -1
