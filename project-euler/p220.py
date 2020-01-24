RULES = {
    'a' : 'aRbFR',
    'b' : 'LFaLb'
}

def add(p, q):
    return (p[0] + q[0], p[1] + q[1])

def multiply(p, q):
    return (p[0] * q[0] - p[1] * q[1], p[0] * q[1] + p[1] * q[0])

def rec(memo, sequence, times, steps):
    key = (sequence, times, steps)
    if key in memo:
        return memo[key]
    d, p = (1, 0), (0, 0)
    for c in sequence:
        if steps == 0:
            break
        if c == 'F':
            steps -= 1
            p = add(p, d)
        elif c == 'L':
            d = multiply(d, (0, 1))
        elif c == 'R':
            d = multiply(d, (0, -1))
        else:
            sub_steps = min(2 ** times, steps)
            steps -= min(2 ** times - 1, steps)
            if times > 0:
                dd, pp = rec(memo, RULES[c], times - 1, sub_steps)
                p = add(p, multiply(d, pp))
                d = multiply(d, dd)
    memo[key] = (d, p)
    return memo[key]

def solve(times, steps):
    memo = {}
    d, p = rec(memo, 'Fa', times, steps)
    return multiply(p, (0, 1))

print(solve(10, 500))
print(solve(50, 10 ** 12))
