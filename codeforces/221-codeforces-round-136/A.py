# Codeforces Round #136
# Problem A -- Little Elephant and Function

n = input()
position = [i for i in xrange(n)]
for i in xrange(1, n):
    position[i], position[i - 1] = position[i - 1], position[i]
result = [0] * n
for i in xrange(n):
    result[position[i]] = i + 1
print " ".join(map(str, result))
