-- School Team Contest #1
-- Problem H -- Multiplication Table
main :: IO()
main = interact work

work :: String -> String
work input = unlines . map (unwords . map (showBase n)) . solve $ n
    where n = read input :: Int

showBase :: Int -> Int -> String
showBase b n 
    | n == 0    = ""
    | otherwise = showBase b (n `div` b) ++ show (n `mod` b)

solve :: Int -> [[Int]]
solve n = [[i * j | j <- numbers] | i <- numbers]
    where numbers = [1..n - 1]
