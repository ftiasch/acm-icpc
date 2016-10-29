# at most 100 digits
result = 0
for d0 in range(1, 10):
    for d in range(1, 100):
        for k in range(1, 10): # 1?
            a = d0 * (10 ** d) - d0 * k
            b = 10 * k - 1
            if a % b == 0:
                D = a // b
                if len(str(D)) == d:
                    result += D * 10 + d0
                    result %= 100000
print(result)
