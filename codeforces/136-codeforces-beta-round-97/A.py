# Codeforces Beta Round #97
# Problem A -- Presents
n = input()
answer = [0] * n
for k, v in enumerate(map(int, raw_input().split())):
    answer[v - 1] = k + 1
print " ".join(map(str, answer))
