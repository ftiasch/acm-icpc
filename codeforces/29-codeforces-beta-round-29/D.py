# Codeforces Beta Round #29
# Problem D -- Ant on the Tree
def dfs(p, u):
    parent[u] = p
    depth[u] = depth[p] + 1
    for v in edges[u]:
        if v != p:
            dfs(u, v)

def go(a, b):
    head = []
    tail = []
    while a != b:
        if depth[a] > depth[b]:
            head.append(a)
            a = parent[a]
        else:
            tail.append(b)
            b = parent[b]
    tail.reverse()
    return head + [a] + tail

n = input()
edges = [[] for _ in xrange(n + 1)]
for _ in xrange(n - 1):
    a, b = map(int, raw_input().split())
    edges[a].append(b)
    edges[b].append(a)
leaves = map(int, raw_input().split())
leaves.append(1)
depth = [-1] + [0] * n
parent = [-1] * (n + 1)
dfs(0, 1)

now = 1
solution = []
for leaf in leaves:
    solution += go(now, leaf)
    solution.pop()
    now = leaf
solution.append(1)
if len(solution) == 2 * n - 1:
    print " ".join(map(str, solution))
else:
    print -1
