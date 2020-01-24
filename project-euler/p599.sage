G = SymmetricGroup(24)

H = G.subgroup([
    G('(7, 8, 14, 13)(3, 9, 18, 12)(4, 15, 17, 6)'),
    G('(1, 7, 17, 21)(2, 8, 18, 22)(3, 13, 19, 23)(4, 14, 20, 24)(5, 6, 12, 11)(10, 9, 15, 16)'),
    G('(5, 7, 9, 24)(6, 8, 10, 23)(11, 13, 15, 22)(12, 14, 16, 21)(3, 4, 2, 1)(17, 18, 20, 19)'),
    G('(3, 9, 18, 12)(1, 10, 20, 11)(4, 15, 17, 6)(2, 16, 19, 5)(7, 8, 14, 13)(22, 21, 23, 24)')
])

def solve(c):
    result = 0
    for g in H.conjugacy_classes_representatives():
        result += c ^ len(g.cycle_type()) / H.centralizer(g).order()
    return result

print solve(2)
print solve(10)
