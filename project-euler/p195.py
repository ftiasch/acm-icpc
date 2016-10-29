from math import floor, sqrt

import sys
sys.setrecursionlimit(1000000)

def count(r, a, b, c):
    if a <= 0 or a >= 4 * b:
        return 0
    while a % 4 != 0 or c % 4 != 0:
        a, b, c = a * 2, b * 2, c * 2
    a, c = a // 4, c // 4
    return floor(2 * r * (a + b + c) / (a * b * sqrt(3)))

def get_minimum(a, b, c, d):
    result = (a + b) * (c + d)
    if a < 0:
        x = b // (-a)
        result = min(result, (a * x + b) * (c * x + d))
    return result

def generate_1(r, p1, q1, p2, q2):
    if q1 <= p1 or 3 * p2 <= q2:
        return 0
    if get_minimum(3 * p1 - q1, 3 * p2 - q2, q1, q2) > 12 * r / sqrt(3):
        return 0
    p, q = p1 + p2, q1 + q2
    result = generate_1(r, p1, q1, p, q) + generate_1(r, p, q, p2, q2)
    if q % 3 != 0:
        result += count(r, (3 * p - q) * (p + q), p * q, 3 * p * p + q * q)
    return result

def generate_2(r, p1, q1, p2, q2):
    if 3 * q1 <= p1 or p2 <= q2:
        return 0
    if get_minimum(p1 - q1, p2 - q2, q1, q2) > 4 * r / sqrt(3):
        return 0
    p, q = p1 + p2, q1 + q2
    result = generate_2(r, p1, q1, p, q) + generate_2(r, p, q, p2, q2)
    if p % 3 != 0:
        result += count(r, (p + 3 * q) * (p - q), p * q, p * p + 3 * q * q)
    return result

def solve(r):
    return sum([
        generate_1(r, 0, 1, 1, 0),
        generate_2(r, 0, 1, 1, 0),
    ])

print(solve(100))
print(solve(1000))
print(solve(10000))
print(solve(1053779))
