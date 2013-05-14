-- Codeforces Round #175 
-- Problem D - Permutation Sum
answer = [1, 3, 15, 133, 2025, 37851, 1030367, 36362925]

solve :: Int -> Integer
solve n = if n `mod` 2 == 0
          then 0
          else answer !! (n `div` 2) * product [1..fromIntegral n] `mod` (1000000000 + 7)

main = interact work where
    work = show . solve . (\x -> read x :: Int)
