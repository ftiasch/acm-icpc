cache = {}

def solve(height, digit_sum, count):
    state = (height, digit_sum, count)
    if state in cache:
        return cache[state]
    result = None
    if height == 1:
        if count > 0:
            result = (0, count - 1)
        else:
            result = (1, digit_sum - 1)
    else:
        steps = 0
        for d in xrange(10):
            step, count = solve(height - 1, digit_sum + d, count)
            steps += step
        result = (steps, count)
    cache[state] = result
    return result

def a(n):
    height = 2
    while solve(height, 0, 1)[0] < n:
        height += 1
    result, digit_sum, count = [], 0, 1
    while height > 1:
        for d in xrange(10):
            step, new_count = solve(height - 1, digit_sum + d, count)
            if step < n:
                n -= step
                count = new_count
            else:
                result.append(d)
                height -= 1
                digit_sum += d
                break
    #print(result)
    return int(''.join(map(str, result)))

print a(10 ** 15)
