# f(4 * n + 1, 2 * k + 1) = binom(2 * n, k) mod 2
def recur(mem, a, i, less):
    key = (i, less)
    if key in mem:
        return mem[key]
    res = recur(mem, a, i + 1, less or a[i] == 1)
    if less or a[i] == 1:
        res += 2 * recur(mem, a, i + 1, less)
    mem[key] = res
    return res


def solve(n):
    a = list(map(int, bin((n - 1) // 4 + 1)[2:]))
    mem = {(len(a), False): 0, (len(a), True): 1}
    return recur(mem, a, 0, False)


print(solve(10))
print(solve(10 ** 12))
