def count(n, length, mask):
    if length == 0:
        return (0, 1)[mask == 31]
    # 1 < a < b < c < n | counted
    if (length, mask) in memory:
        return memory[(length, mask)]
    ret = 0
    old_order = map(lambda i : mask >> i & 1, xrange(5))
    for a in (0, 1):
        for b in (0, 1):
            c = a ^ b
            new_order = zip(old_order, (a - (length == 1 and 1 or 0), b - a, c - b, n[length - 1] - c, 0))
            if (0, -1) in new_order:
                continue
            new_mask = int(''.join(map(lambda p: '01'[p[0] != 0 or p[1] != 0], reversed(new_order))), 2)
            ret += count(n, length - 1, new_mask)
            if not (new_mask >> 4 & 1):
                ret += count(n, length - 1, new_mask | 16) * (a + b + c << length - 1)
    memory[(length, mask)] = ret
    return ret

def solve(n):
    n += 1
    b = []
    while n > 0:
        b.append(n % 2)
        n /= 2
    l = len(b)
    return count(b, l, 0) - 3 * count(b, l, 16)

for n in (8, 128, 10 ** 18):
    memory = {}
    print n, solve(n) % (10 ** 9)
