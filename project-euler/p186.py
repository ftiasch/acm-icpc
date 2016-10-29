N = 1000000

buffer = [ (100003 - 200003 * k + 300007 * k * k * k) % N for k in range(1, 56) ]

def generate():
    buffer.append((buffer[0] + buffer[-24]) % N)
    elem = buffer[0]
    del buffer[0]
    return elem

parent = list(range(N))
size = [1] * N

def find(u):
    if parent[u] != u:
        parent[u] = find(parent[u])
    return parent[u]

minister, count = 524287, 0
while size[find(minister)] * 100 < N * 99:
    a = generate()
    b = generate()
    if a == b:
        continue
    count += 1
    a = find(a)
    b = find(b)
    if a != b:
        parent[a] = b
        size[b] += size[a]
print(count)
