import random
from collections import defaultdict
from fractions import gcd
from functools import reduce

def is_prime(n):
    d = 2
    while d * d <= n:
        if n % d == 0:
            return False
        d += 1
    return True

def lcm(a, b):
    return a // gcd(a, b) * b

def check(LCM, mod, candidates, non_empty, index, sum):
    if non_empty and sum % mod == 0:
        return True
    if index >= len(candidates) or LCM < sum:
        return False
    return check(LCM, mod, candidates, True, index + 1, sum + candidates[index]) \
            or check(LCM, mod, candidates, non_empty, index + 1, sum)

def search(LCM, candidates, index, sum):
    if LCM < sum:
        return 0
    if index == len(candidates):
        return int(LCM == sum)
    return search(LCM, candidates, index + 1, sum) + search(LCM, candidates, index + 1, sum + candidates[index])

def generate(LCM, hash, candidates, sum, index):
    if LCM < sum:
        return hash
    if index < len(candidates):
        generate(LCM, hash, candidates, sum, index + 1)
        generate(LCM, hash, candidates, sum + candidates[index], index + 1)
    else:
        hash[sum] += 1
    return hash

def solve(n):
    primes = [p for p in range(2, n + 1) if is_prime(p)]
    LCM = reduce(lcm, [i * i for i in range(2, n + 1)])
    final_candidates = [i for i in range(2, n + 1)]
    for p in primes:
        candidates = [LCM // (i * i) for i in range(p, n + 1, p)]
        if not check(LCM, p * p, candidates, False, 0, LCM // 2):
            for i in range(p, n + 1, p):
                final_candidates.remove(i)
    random.shuffle(final_candidates)
    # final_candidates.sort()
    LCM = reduce(lcm, [i * i for i in final_candidates])
    candidates = [LCM // (i * i) for i in final_candidates]
    candidates_0 = candidates[:len(candidates) // 2]
    candidates_1 = candidates[len(candidates) // 2:]
    set_0 = generate(LCM, defaultdict(lambda: 0), candidates_0, LCM // 2, 0)
    set_1 = generate(LCM, defaultdict(lambda: 0), candidates_1, 0, 0)
    result = 0
    for k, v in set_0.items():
        result += set_1[LCM - k] * v
    return result

print(solve(80))
