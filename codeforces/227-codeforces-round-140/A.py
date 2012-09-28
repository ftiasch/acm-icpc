# Codeforces Round #140
# Problem A -- Where do I Turn?
def det(a, b):
    return a[0] * b[1] - a[1] * b[0]

def subtract(a, b):
    return (a[0] - b[0], a[1] - b[1])

read = lambda: tuple(map(int, raw_input().split()))
a = read()
b = read()
c = read()
d = det(subtract(b, a), subtract(c, b))
if d < 0:
    print "RIGHT"
elif d == 0:
    print "TOWARDS"
else:
    print "LEFT"
