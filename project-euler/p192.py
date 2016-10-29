from math import floor, sqrt

def cmp(p, q, n, n0):
    # p / q < sqrt{n} - n0
    # (p + n0 q)^2 < n * q^2
    return (p + n0 * q) ** 2 - n * q * q

def approx(n, bound):
    n0 = floor(sqrt(n))
    if n0 * n0 == n:
        return (0, 0)
    p1, q1, p2, q2 = 0, 1, 1, 1
    while True:
        p, q = p1 + p2, q1 + q2
        if q > bound:
            break
        if cmp(p, q, n, n0) < 0:
            max_k = (bound - q1) // q2
            low, high = 1, max_k
            while low < high:
                middle = (low + high + 1) // 2
                if cmp(p1 + middle * p2, q1 + middle * q2, n, n0) < 0:
                    low = middle
                else:
                    high = middle - 1
            p1 += low * p2
            q1 += low * q2
        else:
            max_k = (bound - q2) // q1
            low, high = 1, max_k
            while low < high:
                middle = (low + high + 1) // 2
                if cmp(p2 + middle * p1, q2 + middle * q1, n, n0) > 0:
                    low = middle
                else:
                    high = middle - 1
            p2 += low * p1
            q2 += low * q1
    p1 += n0 * q1
    p2 += n0 * q2
    if 4 * n * q1 * q1 * q2 * q2 < (p1 * q2 + p2 * q1) ** 2:
        return (p1, q1)
    return (p2, q2)

print(approx(13, 20))
print(approx(13, 30))
result = 0
for n in range(2, 100000 + 1):
    p, q = approx(n, 10 ** 12)
    result += q
print(result)
