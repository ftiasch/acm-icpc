# Qualification Round 2012
# Problem A -- Speaking in Tongues
mapping = [24, 7, 4, 18, 14, 2, 21, 23, 3, 20, 8, 6, 11, 1, 10, 17, 25, 19, 13, 22, 9, 15, 5, 12, 0, 16]

def translate(text):
    return ''.join(map(lambda c: chr(ord('a') + mapping[ord(c) - ord('a')]) if c != ' ' else ' ', text))

test_count = input()
for test in xrange(test_count):
    print "Case #%d: %s" %(test + 1, translate(raw_input()))
    
