-- Codeforces Round #131
-- Problem A -- System of Equations
main = interact work

work :: String -> String
work input = show $ solve n m 
    where numbers = map (\i -> read i :: Int) . words $ input
          n = numbers !! 0
          m = numbers !! 1

square :: Int -> Int
square x = x * x

solve :: Int -> Int -> Int
solve n m = length . filter (check n m) $ [0..n]

check :: Int -> Int -> Int -> Bool
check n m x = y >= 0 && x + square y == m
    where y = n - square x
