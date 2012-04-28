# Qualification Round 2012
# Problem C -- Recycled Numbers
test_count = input()
for test in xrange(test_count):
    a, b = map(int, raw_input().split())
    times = 1
    while times * 10 <= b:
        times *= 10
    result = 0
    for x in xrange(a, b + 1):
        numbers = []
        i = x
        while True:
            r = i % 10
            i = (i - r) / 10 + r * times
            if a <= i <= b:
                numbers.append(i)
            if i == x:
                break
        if min(numbers) == x:
            n = len(set(numbers))
            result += n * (n - 1) / 2
    print "Case #%d: %d" %(test + 1, result)
