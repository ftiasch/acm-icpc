subsets :: [a] -> [[a]]
subsets []       = [[]]
subsets (a : as) = ss ++ map (a :) ss
                   where ss = subsets as

valid l r x a = l <= s && s <= r && d >= x
                where s = sum a
                      d = (maximum a) - (minimum a)

solve :: [Int] -> Int
solve (_ : l : r : x : c) = length . filter (valid l r x) . subsets $ c

main :: IO ()
main = print . solve . (map read . words) =<< getContents
