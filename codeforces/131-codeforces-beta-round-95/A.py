# Codeforces Beta Round #95
# Problem A -- cAPS lOCK
all_upper = lambda word: all(map(lambda c: c.isupper(), word))
word = raw_input()
if all_upper(word) or word[0].islower() and all_upper(word[1:]):
    print "".join(map(lambda c: c.upper() if c.islower() else c.lower(), word))
else:
    print word
