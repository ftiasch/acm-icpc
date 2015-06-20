bool :: a -> a -> Bool -> a
bool f _ False = f
bool _ t True  = t

type Input = (Integer, Integer)

main :: IO ()
main = putStrLn . bool "NO" "YES" . solve . parse =<< getContents

parse :: String -> (Integer, Integer)
parse contents = (a, b) where [a, b] = map read . words $ contents

solve :: Input -> Bool
solve (w, m)
    | w == 2 = True
    | m == 0 = True
    | r == 0 = solve (w, m `div` w)
    | r == 1 = solve (w, (m - 1) `div` w)
    | r == w - 1 = solve (w, (m + 1) `div` w)
    | otherwise = False
    where r = m `mod` w
