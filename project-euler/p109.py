from operator import itemgetter
from itertools import product

scores = []
for i in range(3):
    for j in range(20):
        scores.append((i + 1, (i + 1) * (j + 1)))
scores.append((3, 25))
scores.append((2, 50))

results = set()
for length in range(3):
    for permutation in product(scores, repeat=length + 1):
        if permutation[-1][0] != 2:
            continue
        if sum(map(itemgetter(1), permutation)) >= 100:
            continue
        if length + 1 == 3 and permutation[0] > permutation[1]:
            continue
        results.add(permutation)
print(len(results))
