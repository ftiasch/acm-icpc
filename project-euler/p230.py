A = '1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679'
B = '8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196'

def solve(a, b, n):
    lengths = [len(a), len(b)]
    while lengths[-1] < n:
        lengths.append(lengths[-2] + lengths[-1])
    index, position = 0, n - 1
    while lengths[index] < n:
        index += 1
    while index >= 2:
        if position < lengths[index - 2]:
            index -= 2
        else:
            position -= lengths[index - 2]
            index -= 1
    return [a, b][index][position]

print(solve('1415926535', '8979323846', 35))
result = 0
for n in range(18):
    result += 10 ** n * int(solve(A, B, (127 + 19 * n) * 7 ** n))
print(result)
