from math      import sqrt
from fractions import Fraction

def generate(k):
    return { Fraction(a, b) for b in range(1, k + 1) for a in range(1, b) }

def solve(k):
    candidates = generate(k)
    precomputed = {
        n : { z ** n : z for z in candidates } for n in range(-2, 3)
    }
    result = set()
    for x in candidates:
        for y in candidates:
            for n in range(-2, 3):
                zn = x ** n + y ** n
                if zn in precomputed[n]:
                    z = precomputed[n][zn]
                    result.add(x + y + z)
            if x == y:
                break
    result = sum(result)
    return result.numerator + result.denominator

print(solve(35))
