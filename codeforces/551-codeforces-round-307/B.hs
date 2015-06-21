alphabet :: String
alphabet = ['a'..'z']

singleton :: a -> [a]
singleton x = [x]

type Count  = [Int]
type Result = (Int, Int, Count)

count :: String -> Count
count s = map count' alphabet
          where count' c = length . filter (== c) $ s

occur :: Count -> Count -> Int
occur p = minimum . map (uncurry $ flip div) . filter ((> 0) . fst) . zip p

build :: String -> String -> Result -> String
build a b (xy, x, c) = concat $ concatMap (uncurry replicate) components
                       where y          = xy - x
                             components = [(x, a), (y, b)] ++ zip c (map singleton alphabet)

compute :: Count -> Count -> Count -> Int -> Result
compute a b c i = (i + j, i, c'')
                  where c'        = exclude i a c
                        j         = occur b c'
                        c''       = exclude j b c'
                        exclude n = zipWith (\a c -> c - n * a)

solve :: String -> String -> String -> String
solve a' b' c' = build a' b' best
                 where best      = maximum [compute a b c i | i <- [0..n]]
                       n         = occur a c
                       [a, b, c] = map count [a', b', c']

main :: IO ()
main = do
    a <- getLine
    b <- getLine
    c <- getLine
    putStrLn $ solve b c a
