def transform(state, x):
    new_state = list(state)
    new_state.remove(x)
    y = x
    while y > 1:
        y //= 2
        new_state.append(y)
    new_state.sort()
    return tuple(new_state)

def solve(memo, state):
    if state in memo:
        return memo[state]
    result = len(state) == 1 and 1. or 0.
    prob = 1. / len(state)
    for x in state:
        result += prob * solve(memo, transform(state, x))
    memo[state] = result
    return result

memo = { (1,) : 0. }
print(solve(memo, (16,)) - 1.)
