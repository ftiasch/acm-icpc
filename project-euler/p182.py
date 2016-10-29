from fractions import gcd

def count(p, e):
    result = 1 # m = 0
    q = (p - 1) // gcd(p - 1, e - 1)
    return (p - 1 + q - 1) // q

def solve(p, q):
    phi = (p - 1) * (q - 1)
    minimum, minimum_count = p * q, 0
    for e in range(2, phi):
        if gcd(e, phi) != 1:
            continue
        tmp = count(p, e) * count(q, e)
        if tmp < minimum:
            minimum, minimum_count = tmp, 0
        if tmp == minimum:
            minimum_count += e
    return minimum_count

print(solve(1009, 3643))
