def add(d, k, v):
    d[k] = d.get(k, 0) + v

def solve(n):
    mod = 10 ** 18
    result, queues = 0, [{}, {}, {}]
    queues[n % 3][0] = 1
    for i in xrange(n, 0, -1):
        queues[(i + 1) % 3].clear()
        for k, v in queues[i % 3].items():
            if k == -1:
                result += v
                result %= mod
            else:
                add(queues[(i + 2) % 3], (k ^ sg[i - 2] + 1) - 1, v)
                add(queues[(i + 1) % 3], (k ^ sg[i - 1] + 1) - 1, v)
    return result

N = 10000
sg = [-1, 0, 1]
for i in xrange(3, N + 1):
    sg.append(sg[-1] + 1 ^ sg[-2] + 1)
print solve(5)
print solve(10)
print solve(10000)
