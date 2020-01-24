from itertools import product

palins = []
for l in [5, 6]:
    for p in product(*([(0, 1)] * l)):
        if p == p[::-1]:
            palins.append(''.join(map(str, p)))
sufs = ['']
for p in palins:
    for i in range(1, len(p)):
        sufs.append(p[:i])
sufs = sorted(list(set(sufs)))
m = len(sufs) + 1
go = [[-1, -1] for _ in range(m)]
go[0][0] = go[0][1] = 0
for i, suf in enumerate(sufs):
    for c in range(2):
        s, check = suf + str(c), False
        for k in range(len(s)):
            if s[k:] in palins:
                check = True
        if check:
            go[i + 1][c] = 0
        else:
            while s not in sufs:
                s = s[1:]
            go[i + 1][c] = sufs.index(s) + 1
ways = [0] * m
ways[1] = 1
guess = [0]
for _ in range(99):
    new_ways = [0] * m
    for i in range(m):
        for j in range(2):
            new_ways[go[i][j]] += ways[i]
    ways = new_ways
    guess.append(guess[-1] + ways[0])
guess = guess

poly = berlekamp_massey(guess)
deg = poly.degree()

print poly
print guess[6:6+deg]
