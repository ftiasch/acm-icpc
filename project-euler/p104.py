def pandigital(s):
    return set(map(int, s)) == {i for i in xrange(1, 10)}

i = 2
pa = pb = sa = sb = 1
while True:
    i += 1
    pa, pb = pa + pb, pa
    while pa > 10 ** 50:
        pa /= 10
        pb /= 10
    sa, sb = (sa + sb) % 1000000000, sa
    if pandigital(str(pa)[:9]) and pandigital(str(sa)):
        print i
        break
