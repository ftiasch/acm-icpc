def get_mertens(n):
    global N
    if n < N:
        return mertens[n]
    if n in memory:
        return memory[n]
    result, i = 1, 2
    while i <= n:
        j = n / (n / i)
        result -= get_mertens(n / i) * (j - i + 1)
        i = j + 1
    memory[n] = result
    return result

def solve(n):
    result, i, last = 0, 1, 0
    while i <= n:
        j = n / (n / i)
        current = get_mertens(j)
        result += ((n / i + 1) ** 3 - 1) * (current - last)
        i, last = j + 1, current
    return result

def pretty_print(n):
    s = str(n)
    assert len(s) >= 9
    return s[:9] + s[-9:]

N = int(pow(10 ** 10, 2. / 3.))
mertens, primes = [-2] * N, []
for i in xrange(2, N):
    if mertens[i] == -2:
        mertens[i] = -1
        primes.append(i)
    for p in primes:
        if i * p >= N:
            break
        mertens[p * i] = -mertens[i]
        if i % p == 0:
            mertens[p * i] = 0
            break
mertens[:2] = [0, 1]
for i in xrange(2, N):
    mertens[i] += mertens[i - 1]

memory = {}
print solve(1000000)
print pretty_print(solve(10 ** 10))
