# Descartes's theorem
from math import sqrt
from collections import defaultdict

def normalize(k1, k2, k3):
    return tuple(sorted([k1, k2, k3]))

K = 3 - 2 * sqrt(3)
R = 1 / (3 - 2 * sqrt(3))
covered = 3
ways = { (1, 1, 1) : 1, (K, 1, 1) : 3 }
for _ in range(10):
    new_ways = defaultdict(lambda: 0)
    for ks, way in ways.items():
        k1, k2, k3 = ks
        k4 = k1 + k2 + k3 + 2 * sqrt(k1 * k2 + k2 * k3 + k3 * k1)
        covered += 1 / (k4 * k4) * way
        new_ways[normalize(k1, k2, k4)] += way
        new_ways[normalize(k2, k3, k4)] += way
        new_ways[normalize(k3, k1, k4)] += way
    ways = new_ways
print(1. - covered / R / R)
