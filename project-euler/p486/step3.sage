from itertools import product
from fractions import gcd

output = open('step2.out').readlines()

zeros, period = [None] * 3, [None] * 3
for i in range(3):
    period[i] = int(output[2 * i + 1])
    zeros[i] = list(map(int, output[2 * i].split()))
zeros[0] = zeros[0] + [r + period[0] for r in zeros[0]]
period[0] *= 2
G = 12
period = [p // G for p in period]
for i in range(3):
    groups = [[] for _ in range(G)]
    for z in zeros[i]:
        groups[z % G].append(z // G)
    zeros[i] = groups
L = lcm(period[0], lcm(period[1], period[2]))
coefficient = [(L // period[i]) * inverse_mod(L // period[i], period[i]) for i in range(3)]

def solve(n):
    n -= 5
    # [0, n)
    count = 0
    for r in range(G):
        for a, b, c in product(zeros[0][r], zeros[1][r], zeros[2][r]):
            rem = 12 * ((a * coefficient[0] + b * coefficient[1] + c * coefficient[2]) % L) + r
            if rem < n:
                count += (n - rem + 12 * L - 1) // (12 * L)
    return count

# print solve(5 * (10 ** 9))
print solve(10 ** 18)
