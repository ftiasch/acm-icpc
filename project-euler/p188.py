def get_phi(mod):
    result, d = mod, 2
    while d * d <= mod:
        if mod % d == 0:
            result //= d
            result *= d - 1
            while mod % d == 0:
                mod //= d
        d += 1
    if mod > 1:
        result //= mod
        result *= mod - 1
    return result

def solve(a, k, mod):
    if k == 1:
        return a % mod
    if mod == 1:
        return 0
    phi = get_phi(mod)
    return pow(a, phi + solve(a, k - 1, phi), mod)

print(solve(1777, 1855, 10 ** 8))
