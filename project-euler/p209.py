from itertools import product
from functools import reduce
from operator import mul

# print('graph {')
# for a, b, c, d, e, f in product((0, 1), repeat=6):
#     u = ''.join(map(str, (a, b, c, d, e, f)))
#     v = ''.join(map(str, (b, c, d, e, f, a ^ (b & c))))
#     print('{}--{}'.format(u, v))
# print('}')

def count(n):
    if n == 1:
        return 1
    if n == 2:
        return 3
    ways = [1, 2]
    for i in range(2, n):
        ways.append(ways[-1] + ways[-2])
    return ways[n - 1] + ways[n - 3]

cycles = [1, 2, 3, 6, 6, 46]
print(reduce(mul, map(count, cycles)))
