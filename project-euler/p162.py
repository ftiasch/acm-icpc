def count(max_length, digits):
    result = 0
    for length in range(max_length):
        for first in digits:
            if first != 0:
                result += pow(len(digits), length)
    return result

from itertools import combinations

result = 0
for size in range(4):
    for combination in combinations((0, 1, 15), size):
        ways = count(16, [d for d in range(16) if d not in combination])
        if size % 2 == 0:
            result += ways
        else:
            result -= ways
print(hex(result).upper()[2:])
