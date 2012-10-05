# Codeforces Round #135
# Problem B -- Special Offer! Super Price 999 Bourles!
def find(p, l):
    if l > 0 and int('9' * l) > p:
        return 0
    low = 0
    high = 10 ** 18
    while low < high:
        middle = (low + high + 1) >> 1
        if int(str(middle) + '9' * l) <= p:
            low = middle
        else:
            high = middle - 1
    return int(str(low) + '9' * l)

p, d = map(int, raw_input().split())
i = 18
while p - find(p, i) > d:
    i -= 1
print find(p, i)
