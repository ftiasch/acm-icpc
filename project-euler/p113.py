def count(n):
    result = 1
    for i in xrange(n):
        result *= 10 + i
        result /= i + 1
    return result

n = 100
asc = count(n)
dec = 0
for length in xrange(n):
    dec += count(length + 1)
print(asc + dec - 10 * n - 1)
