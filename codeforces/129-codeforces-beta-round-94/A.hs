-- Codeforces Beta Round #94
-- Problem A -- Cookies
main = interact work

work :: String -> String
work input = show $ solve . parse $ input

parse :: String -> [Int]
parse = map (\i -> read i :: Int) . words . last . lines

solve :: [Int] -> Int
solve a = length . filter (\x -> x `mod` 2 == (sum a) `mod` 2) $ a
