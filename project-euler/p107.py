from urllib.request import urlopen

def find(u):
    if parent[u] != u:
        parent[u] = find(parent[u])
    return parent[u]

lines = urlopen('https://projecteuler.net/project/resources/p107_network.txt').readlines()
n = len(lines)
edges = []
for i, line in enumerate(lines):
    tokens = line.decode().rstrip().split(',')
    for j in range(i):
        if tokens[j] != '-':
            edges.append((int(tokens[j]), i, j))
result, parent = 0, [i for i in range(n)]
for c, a, b in sorted(edges):
    if find(a) == find(b):
        result += c
    else:
        parent[find(a)] = find(b)
print(result)
