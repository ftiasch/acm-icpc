-- Project Euler 340 Crazy Function
main = print $ (solve (21 ^ 7) (7 ^ 21) (12 ^ 7)) `mod` (10 ^ 9)

solve a b c = 4 * (a - c) * (b + 1) + arithmeticSum b + (4 * a - 3 * c) * sum a b
              where sum a b = let last = (b + 1 - a) `div` a
                              in a * arithmeticSum last + (last + 1) * (b + 1 - (last + 1) * a)
                    arithmeticSum n = (1 + n) * n `div` 2

