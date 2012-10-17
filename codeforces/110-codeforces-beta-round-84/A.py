# Codeforces Beta Round #84
# Problem A -- Nearly Lucky Number
def is_lucky(n):
    if n == 0:
        return False
    while n > 0:
        if (n % 10) not in [4, 7]:
            return False
        n /= 10
    return True

print ("NO", "YES")[is_lucky(len(filter(is_lucky, map(int, raw_input()))))]
