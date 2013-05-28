# Codeforces Beta Round #30
# Problem B -- Codeforces World Finals
from sys import exit
from itertools import permutations

def read_date():
   return map(int, raw_input().split('.'))

def is_leap(year):
    return year % 4 == 0

def day_of(year, month):
    if month == 2:
        return [28, 29][is_leap(year)]
    return (31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)[month - 1]

def is_valid(date):
    day, month, year = date
    year += 2000
    return 1 <= month <= 12 and 1 <= day <= day_of(year, month)

def adult(birth, final):
    if final[2] - birth[2] != 18:
        return final[2] - birth[2] > 18
    if final[1] != birth[1]:
        return final[1] > birth[1]
    return final[0] >= birth[0]

final = read_date()
birth = read_date()
for p in permutations(birth):
    if is_valid(p) and adult(p, final):
        print "YES"
        exit(0)
print "NO"
