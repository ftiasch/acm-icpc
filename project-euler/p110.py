#   nx + ny = xy
# (x - n) (y - n) = n^2

def is_prime(n):
    p = 2
    while p * p <= n:
        if n % p == 0:
            return False
        p += 1
    return True

next_prime = 2
primes = []

def get_prime(index):
    global next_prime
    while index >= len(primes):
        if is_prime(next_prime):
            primes.append(next_prime)
        next_prime += 1
    return primes[index]

result = -1

def search(exponents, ways, number):
    global result
    if (ways + 1) / 2 > 4000000:
        result = number
        return
    index = len(exponents)
    p = get_prime(index)
    upper_bound = index > 0 and exponents[index - 1] or -1
    e = 0
    while True:
        e += 1
        number *= p
        if result != -1 and number >= result:
            return
        if upper_bound != -1 and e > upper_bound:
            return
        search(exponents + [e], ways * (e + e + 1), number)

search([], 1, 1)
print(result)
