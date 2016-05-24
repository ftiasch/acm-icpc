# C(n) = 8 * binom(2 * n, n) - 3 * n * n - 2 * n - 7

N = 90
MOD = 10 ** 8 + 7

fib = [1, 1]
for i in xrange(2, N):
    fib.append(fib[-2] + fib[-1])

binoms, facts, inv_facts = [1] * N, [], []
for i in xrange(1, N):
    n, k = fib[i] + fib[i], fib[i]
    while n > 0 or k > 0:
        n0 = n % MOD
        k0 = k % MOD
        if n0 < k0:
            binoms[i] = 0
            break
        else:
            facts.append((n0, i))
            inv_facts.append((k0, i))
            inv_facts.append((n0 - k0, i))
        n /= MOD
        k /= MOD

facts.sort()
fact, j = 1, 0
for i in xrange(MOD):
    while j < len(facts) and facts[j][0] == i:
        binoms[facts[j][1]] *= fact
        j += 1
    fact = fact * (i + 1) % MOD

inv_facts.sort()
inv_fact, j = MOD - 1, len(inv_facts) - 1
for i in xrange(MOD - 1, -1, -1):
    while j >= 0 and inv_facts[j][0] == i:
        binoms[inv_facts[j][1]] *= inv_fact
        j -= 1
    inv_fact = inv_fact * i % MOD

result = 0
for i in xrange(1, N):
    n = fib[i]
    b = binoms[i]
    answer = ((8 * b - 3 * n * n - 2 * n - 7) % MOD + MOD) % MOD
    result += answer
    result %= MOD

print(result)
