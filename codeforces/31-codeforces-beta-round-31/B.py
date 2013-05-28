# Codeforces Beta Round #31
# Problem B -- Sysadmin Bob
tokens = raw_input().split('@')
if len(tokens) >= 2 and len(tokens[0]) >= 1 and len(tokens[-1]) >= 1 and (len(tokens) <= 2 or min(map(len, tokens[1:-1])) >= 2):
    address = [] 
    for i in reversed(xrange(len(tokens) - 1)):
        address.append((tokens[i][-1] if i > 0 else tokens[0]) + '@' + tokens[i + 1])
        tokens[i] = tokens[i][:-1]
    print ",".join(reversed(address))
else:
    print "No solution"
