#!/usr/bin/env python
a = raw_input()
b = raw_input()
mapping = [-1] * 26
for x, y in zip(a, b):
    if x.isalpha() and y.isalpha():
        mapping[ord(x) - ord('a')] = ord(y) - ord('a')
mapping[ord('q') - ord('a')] = ord('z') - ord('a')
print mapping
#for k, v in enumerate(mapping):
#    print chr(ord('a') + k), chr(ord('a') + v)
