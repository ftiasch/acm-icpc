-- Codeforces Beta Round #95
-- Problem C -- The World is a Theatre
main :: IO()
main = interact work

work :: String -> String
work input = show $ solve n m t
    where [n, m, t] = map read . words $ input

factorial :: Integer -> Integer
factorial n = product [1..n]

binom :: Integer -> Integer -> Integer
binom n k
    | n >= k    = factorial n `div` factorial k `div` factorial (n - k)
    | otherwise = 0

solve :: Integer -> Integer -> Integer -> Integer
solve n m t = sum  [binom n i * binom m (t - i) | i <- [4..t - 1]]
