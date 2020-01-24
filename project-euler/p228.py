from fractions import Fraction

alphas = set()
for n in range(1864, 1909 + 1):
    alpha = 0
    delta = Fraction(1, n)
    for i in range(n):
        alphas.add(alpha)
        alpha += delta
print(len(alphas))
