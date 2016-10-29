from math import floor

def f(x):
    return floor(2 ** (30.403243784 - x * x)) * (1e-9)

u, v = None, -1
for i in range(100000):
    u, v = v, f(v)
print(u + v)
