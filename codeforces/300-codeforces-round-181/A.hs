-- Codeforces Round #181 
-- Problem A -- Array
main :: IO()
main = interact work

work :: String -> String
work input = unlines . map showList . solve $ numbers
    where numbers = map parseInt . words . last . lines $ input
          parseInt str = read str :: Int
          showList lst = (show . length $ lst) ++ " " ++ (unwords . map show $ lst)

solve :: [Int] -> [[Int]]
solve numbers = if null ps
                then [[head ns], tail . take 3 $ ns, drop 3 ns ++ zs]
                else [[head ns], [head ps], tail ns ++ tail ps ++ zs]
    where ps = filter (\x -> x > 0) numbers
          ns = filter (\x -> x < 0) numbers
          zs = filter (\x -> x == 0) numbers
