# Codeforces Round #104
# Problem A -- Lucky Ticket
n = input()
s = map(int, list(raw_input()))
lucky = lambda x : x in [4, 7]
print "YES" if all(map(lucky, s)) and sum(s[:n >> 1]) == sum(s[n >> 1:]) else "NO"
