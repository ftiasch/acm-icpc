N = 2000

def check(N, x, y):
    return 0 <= x < N and 0 <= y < N

s = [0] * (N * N + 1)
for k in range(1, 56):
    s[k] = (100003 - 200003 * k + 300007 * k * k * k) % 1000000 - 500000
for k in range(56, 4000001):
    s[k] = (s[k - 24] + s[k - 55] + 1000000) % 1000000 - 500000
for n in (10, 100):
    print(n, s[n])
result = 0
for dx, dy in ((0, 1), (1, 0), (1, -1), (1, 1)):
    for i in range(N):
        for j in range(N):
            if check(N, i - dx, j - dy):
                continue
            x, y, best = i, j, 0
            while check(N, x, y):
                best += s[1 + 2000 * x + y]
                result = max(result, best)
                if best < 0:
                    best = 0
                x += dx
                y += dy
print(result)
