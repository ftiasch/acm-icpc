from fractions import Fraction

def solve(numbers, sides):
    max_sum = numbers * sides
    result = [0] * (max_sum + 1)
    result[0] = 1
    for _ in range(numbers):
        for j in range(max_sum, -1, -1):
            if result[j] > 0:
                for outcome in range(1, sides + 1):
                    result[j + outcome] += result[j] / Fraction(sides)
                result[j] = 0
    return result

peter = solve(9, 4)
culin = solve(6, 6)
result = 0
cumsum = 0
for x in range(37):
    result += peter[x] * cumsum
    cumsum += culin[x]
print(float(result))
