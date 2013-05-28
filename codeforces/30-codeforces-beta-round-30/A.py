# Codeforces Beta Round #30 
# Problem A -- Accounting
no = "No solution"

a, b, n = map(int, raw_input().split())
if a == 0:
    if b == 0:
        print 0
    else:
        print no
else:
    if b % a == 0:
        c = b / a
        if c < 0 and n % 2 == 0:
            print no
        else:
            x = (int)(round(pow(abs(c), 1.0 / n) * (1, -1)[c < 0]))
            if a * pow(x, n) == b:
                print x
            else:
                print no
    else:
        print no
