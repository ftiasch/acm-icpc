-- Codeforces Round #151
-- Problem A -- Buggy Sorting
main :: IO()
main = prettify . solve . (\x -> read x :: Int) =<< getLine

solve :: Int -> Maybe [Int]
solve n 
    | n <= 2    = Nothing
    | otherwise = Just (reverse [1..n])

prettify :: Maybe [Int] -> IO()
prettify Nothing     = putStrLn "-1"
prettify (Just list) = putStrLn $ unwords . map show $ list
