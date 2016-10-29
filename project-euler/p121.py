from fractions import Fraction

def solve(n):
    prob = [Fraction(1)] + [Fraction(0)] * n
    for i in range(n):
        blue_prob = Fraction(1, 2 + i)
        for j in range(i + 1, 0, -1):
            prob[j] = prob[j] * (1 - blue_prob) +  prob[j - 1] * blue_prob
        prob[0] *= 1 - blue_prob
    result = 0
    for i in range(n // 2 + 1, n + 1):
        result += prob[i]
    return int(float(1 / result))

for n in (4, 15):
    print(solve(n))
