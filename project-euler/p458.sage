from collections import defaultdict

def solve(n):
    Z = Zmod(10 ** 9)
    ways = {tuple([7] * 7) : 1}
    result = []
    for i in range(50):
        new_ways = defaultdict(lambda: 0)
        for k, v in ways.items():
            for j in range(7):
                nk = [0]
                for p in range(7):
                    if j != p:
                        nk.append(min(k[p] + 1, 7))
                nk = tuple(nk)
                if nk != (0, 1, 2, 3, 4, 5, 6):
                    new_ways[nk] += v
        result.append(sum(new_ways.values()))
        ways = new_ways
    poly = berlekamp_massey(result)
    deg = poly.degree()
    t = [[Z(0)] * deg for _ in range(deg)]
    for i in range(1, deg):
        t[i][i - 1] = Z(1)
    for i in range(0, deg):
        t[i][deg - 1] = Z(-poly[i])
    return (vector(Z, result[:deg]) * (matrix(t) ** (n - 1)))[0]

print solve(7)
print solve(10 ** 12)
