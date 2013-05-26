-- Codeforces Beta Round #26 
-- Problem D -- Tickets
main :: IO()
main = interact work

work :: String -> String
work input = show $ solve n m k
    where [n, m, k] = map (\i -> read i :: Int) . words $ input

solve :: Int -> Int -> Int -> Double
solve n m k = max 0 (1 - product [fromIntegral (m - i) / fromIntegral (n + i + 1) | i <- [0..k]])
