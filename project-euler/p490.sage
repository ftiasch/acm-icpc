Z = Zmod(10 ** 9)

states = {}
transitions = []

def get_state(comp, deg):
    return tuple(comp), tuple(deg)

def dfs(ocomp, odeg):
    a = states[get_state(ocomp, odeg)] = len(states)
    for i in range(4):
        for j in range(i, 4):
            comp, deg = ocomp + [4], odeg + [2]

            def merge(x, y, comp=comp, deg=deg):
                if deg[x] > 0 and deg[y] > 0 and comp[x] != comp[y]:
                    deg[x] -= 1
                    deg[y] -= 1
                    ori = comp[x]
                    for k, _ in enumerate(comp):
                        if comp[k] == ori:
                            comp[k] = comp[y]
                    return True
                return False

            if i < 3 and not merge(i, 3):
                continue
            if j < 3 and not merge(j, 3):
                continue
            if comp[0] not in comp[1:]:
                continue

            comp, deg, m = comp[1:], deg[1:], {}
            for k, _ in enumerate(comp):
                if comp[k] not in m:
                    m[comp[k]] = len(m)
                comp[k] = m[comp[k]]

            state = get_state(comp, deg)
            if state not in states:
                dfs(comp, deg)
            transitions.append((a, states[state]))

dfs([0, 0, 0], [0, 0, 1])

m = len(states)
t = [[0] * m for _ in range(m)]
for a, b in transitions:
    t[a][b] += 1
t = matrix(t)
v = vector([1] + [0] * (m - 1))
ways = []
for _ in range(20):
    ways.append(v[0])
    v = v * t
poly = berlekamp_massey(ways)

deg = poly.degree()
how = [[(i + 1, 1)] for i in range(deg)]
how[-1] = []
for i in range(deg):
    how[-1].append((i, -poly[i]))

m = {}
for i in range(deg):
    for j in range(i, deg):
        for k in range(j, deg):
            m[(i, j, k)] = 1 + len(m)
ts = len(m) + 1

t = [[0] * ts for _ in range(ts)]
v = [0] * ts

t[0][0] = 1
t[m[(0, 0, 0)]][0] = 1
for i, j, k in m:
    v[m[(i, j, k)]] = Z(ways[i] * ways[j] * ways[k])
    for a, wa in how[i]:
        for b, wb in how[j]:
            for c, wc in how[k]:
                s = tuple(sorted([a, b, c]))
                t[m[s]][m[(i, j, k)]] += Z(wa * wb * wc)
t = matrix(t)
v = vector(v)

solve = lambda n: (v * pow(t, n))[0]

print solve(10)
print solve(20)
print solve(1000)
print solve(1000000)
print solve(10 ** 14)
