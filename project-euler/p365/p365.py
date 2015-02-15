def is_prime(n):
    i = 2
    while i * i <= n:
        if n % i == 0:
            return False
        i += 1
    return True

def inverse(a, p):
    if a >= p:
        return inverse(a % p, p)
    if a == 1:
        return 1
    return (p - p / a) * inverse(p % a, p) % p

def count_exp(n, p):
    if n == 0:
        return 0
    return (n / p) + count_exp(n / p, p)

def coprime_prod(n, p):
    if n < p:
        return fact[n]
    return pow(fact[p - 1], n / p, p) * fact[n % p] * coprime_prod(n / p, p) % p

def binom(n, m, p):
    if count_exp(n, p) > count_exp(m, p) + count_exp(n - m, p):
        return 0
    global fact
    fact = [1]
    for i in xrange(1, p):
        fact.append(fact[-1] * i % p)
    return coprime_prod(n, p) * inverse(coprime_prod(m, p), p) * inverse(coprime_prod(n - m, p), p) % p

primes = filter(is_prime, xrange(1000, 5000))
binom_tab = [binom(10 ** 18, 10 ** 9, p) for p in primes]
inv_tab = [[inverse(q, p) if p != q else 0 for q in primes] for p in primes]
res = 0
for pid, p in enumerate(primes):
    for qid, q in enumerate(primes):
        if pid < qid:
            for rid, r in enumerate(primes):
                if qid < rid:
                    m, tmp = p * q * r, 0
                    tmp += binom_tab[pid] * q * r * inv_tab[pid][qid] * inv_tab[pid][rid]
                    tmp += binom_tab[qid] * r * p * inv_tab[qid][rid] * inv_tab[qid][pid]
                    tmp += binom_tab[rid] * p * q * inv_tab[rid][pid] * inv_tab[rid][qid]
                    assert tmp % p == binom_tab[pid]
                    assert tmp % q == binom_tab[qid]
                    assert tmp % r == binom_tab[rid]
                    res += tmp % m
print res
