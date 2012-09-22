# Codeforces Beta Round #37
# Problem C -- Old Berland Language
n = input()
lengths = map(int, raw_input().split())

length_count = [0] * (max(lengths) + 1)
for length in lengths:
    length_count[length] += 1

try:
    result = [[] for _ in xrange(max(lengths) + 1)]
    choices = [""]
    for i in xrange(1, max(lengths) + 1):
        next_choices = []
        for choice in choices:
            if len(next_choices) >= n:
                break
            next_choices.append(choice + '0')
            next_choices.append(choice + '1')
        choices = next_choices
        while length_count[i] > 0:
            length_count[i] -= 1
            result[i].append(choices.pop())
    print "YES"
    for l in lengths:
        print result[l].pop()
except IndexError:
    print "NO"
