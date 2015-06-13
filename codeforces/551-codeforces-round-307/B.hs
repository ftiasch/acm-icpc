import Debug.Trace (trace)

type Input  = (String, String, String)

parse :: String -> Input
parse contents = (a, b, c) where [a, b, c] = lines contents

count :: String -> [Int]
count s = map count' ['a'..'z']
          where count' c = length (filter (== c) s)

occur :: [Int] -> [Int] -> Int
occur p t = minimum . map (uncurry div) . filter ((> 0) . snd) $ zip t p

solve :: Input -> String
solve (a, b, c) = show (s, i)
                  where [countA, countB, countC] = map count [a, b, c]
                        combinations = do x <- [0 .. occur countA countC]
                                          let y = occur countB (zipWith (\w u -> w - u * i) countC countA)
                                          return (x + y, x)
                        (s, i) = trace (show combinations) $ maximum combinations

main :: IO ()
main = putStrLn . solve . parse =<< getContents
