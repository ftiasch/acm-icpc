def solve(n):
    b, s = 0, (1, 0, 0, 0)
    while True:
        ns = (s[0] + s[1], s[2], s[3], s[0])
        if sum(ns) >= n:
            break
        b += 1
        s = ns
    d = n - sum(s)
    ns = [s[0] - d, s[1] + d, s[2], s[3], d]
    result = 0
    for i in range(5):
        result += ns[i] * (b + i)
    return result

print(solve(6))
print(solve(10 ** 9))
