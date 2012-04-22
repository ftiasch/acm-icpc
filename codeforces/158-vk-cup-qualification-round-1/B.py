# VK Cup 2012 Qualification Round 1
# Problem B -- Taxi
n = input()
sizes = [0] * 5
for size in map(int, raw_input().split()):
    sizes[size] += 1
taxis, spaces = 0, 0
taxis += sizes[4]
taxis += sizes[3]
spaces += sizes[3]
taxis += (sizes[2] + 1) / 2
spaces += (sizes[2] % 2) * 2
taxis += (max(sizes[1] - spaces, 0) + 3) / 4
print taxis
