# Codeforces Beta Round #65
# Problem A -- Way Too Long Words
for _ in xrange(input()):
    word = raw_input()
    if len(word) > 10:
        print word[0] + str(len(word) - 2) + word[-1]
    else:
        print word
