-- Codeforces Beta Round #32
-- Problem C -- Flea
main :: IO()
main = interact work

work :: String -> String
work input = let [n, m, s] = map (\i -> read i :: Integer) . words $ input
             in show $ solve n m s

solve :: Integer -> Integer -> Integer -> Integer
solve n m s = solve1D n s * solve1D m s

solve1D :: Integer -> Integer -> Integer
solve1D n s = let r = n `mod` s
              in if r == 0
                 then n
                 else (n `div` s + 1) * r
