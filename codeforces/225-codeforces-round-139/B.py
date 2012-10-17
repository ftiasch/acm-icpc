# Codeforces Round #139
# Problem B -- Well-known Numbers
s, k = map(int, raw_input().split())
sums, sequence = 1, [1]
while sums <= s:
    sequence.append(sums)
    sums += sums
    if len(sequence) > k:
        sums -= sequence[-(k + 1)]
result = []
for i in reversed(sequence):
    if i <= s:
        s -= i
        result.append(i)
if len(result) == 1:
    result.append(0)
print len(result)
print " ".join(map(str, result))
