parse :: String -> [[Int]]
parse = map (map weight) . tail . lines
        where weight 'B' =  1
              weight 'W' = -1

diff :: [[Int]] -> [[Int]]
diff = d (zipWith (-)) . map (d (-))
       where d f l = (zipWith f l (tail l)) ++ [last l]

solve :: [[Int]] -> Int
solve = length . filter (/= 0) . concat . diff

main :: IO ()
main = print . solve . parse =<< getContents
