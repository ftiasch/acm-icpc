from fractions import Fraction

class Point(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __sub__(self, other):
        return Point(self.x - other.x, self.y - other.y)

    def __mul__(self, other):
        return Point(self.x * other, self.y * other)

    def __truediv__(self, other):
        return Point(self.x / other, self.y / other)

def det(a, b):
    return a.x * b.y - a.y * b.x

def intersect(a, b, c, d):
    s1 = det(b - a, c - a)
    s2 = det(b - a, d - a)
    if s1 * s2 >= 0:
        return None
    if det(d - c, a - c) * det(d - c, b - c) >= 0:
        return None
    p = (c * s2 - d * s1) / Fraction(s2 - s1)
    return (p.x, p.y)

n = 5000
s = 290797
t = []
for i in range(n << 2):
    s *= s
    s %= 50515093
    t.append(s % 500)
p = [Point(t[i << 1], t[i << 1 | 1]) for i in range(n << 1)]
points = set()
for i in range(n):
    for j in range(i):
        q = intersect(p[i << 1], p[i << 1 | 1], p[j << 1], p[j << 1 | 1])
        if q is not None:
            points.add(q)
print(len(points))
