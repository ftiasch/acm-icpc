# Codeforces Round #143
# Problem D -- Magic Box
read_ints = lambda: map(int, raw_input().split())
x, y, z = read_ints()
a, b, c = read_ints()
y_min, y_max, z_min, z_max, x_min, x_max = read_ints()
result = 0
if x < 0:
    result += x_min
if x > a:
    result += x_max
if y < 0:
    result += y_min
if y > b:
    result += y_max
if z < 0:
    result += z_min
if z > c:
    result += z_max
print result
