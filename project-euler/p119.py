LENGTH = 50
results = set()
for dsum in range(2, 9 * LENGTH + 1):
    e = 1
    while dsum ** e < 10 ** LENGTH:
        n = dsum ** e
        if n >= 10 and sum(map(int, str(n))) == dsum:
            results.add(n)
        e += 1
print(sorted(list(results))[30 - 1])

