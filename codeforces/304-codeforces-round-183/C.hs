-- Codeforces Round #183
-- Problem C -- Lucky Permutation Triple
main :: IO()
main = interact work
 
work :: String -> String
work input = unlines . map unwords . map (map show) . solve $ n
    where n = read input :: Int

solve :: Int -> [[Int]]
solve n = if n `mod` 2 == 0 
          then [[-1]]
          else [a, a, [i * 2 `mod` n | i <- a]]
    where a = [0..n - 1]
