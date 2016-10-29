from math import sqrt

# def check(n):
#     result = 0
#     for i in range(1, n + 1, 2):
#         if n % i == 0:
#             if i % 4 == 1:
#                 result += 1
#             if i % 4 == 3:
#                 result -= 1
#     s = int(sqrt(n))
#     if s * s == n:
#         result -= 1
#     # result *= 4
#     for i in range(1, n + 1):
#         j = int(sqrt(max(n - i * i, 0)))
#         if j > 0 and i * i + j * j == n:
#             result -= 1
#     assert(result == 0)

# n^2 = \prod{(4 k + 1)^{2e}}
# r_2(n^2) = (\prod{2e + 1} - 1) / 2 = 47547
# factor = 5 7 11 13 19

primes = [2]

def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

def get_prime(index):
    while index >= len(primes):
        p = primes[-1] + 1
        while not is_prime(p):
            p += 1
        primes.append(p)
    return primes[index]

def search(best, n, index, upper_bound, product):
    if n == 1:
        if best is None or best > product:
            best = product
        return best
    else:
        p = get_prime(index)
        e = 1
        while (2 * e + 1 <= n) and (upper_bound is None or e <= upper_bound):
            product *= p
            if best is not None and product >= best:
                break
            if n % (2 * e + 1) == 0:
                best = search(best, n // (2 * e + 1), index + 1, e, product)
            e += 1
        return best

def solve(n):
    n = n * 2 + 1
    best = search(None, n, 1, None, 1)
    for two in range(1, n + 1):
        if n % (2 * two - 1) == 0:
            best = search(best, n // (2 * two - 1), 1, None, 2 ** two)
    return best

print(solve(4))
print(solve(47547))
