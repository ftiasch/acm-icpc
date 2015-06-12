import Data.Char (ord)
import Data.List
import Debug.Trace

type Input  = (Int, [Int])
type Output = Bool

readInt :: String -> Int
readInt = foldl' (\s d -> s * 10 + ord d - 48) 0

parse :: String -> Input
parse contents = (k, a)
                 where [[_, k], a] = map (map readInt . words) . lines $ contents

sum' :: [[Maybe Bool]] -> Bool
sum' = last . foldl' byRow (repeat False)
       where byRow p          = tail . scanl f True . zip p
             f _ (_, Just b)  = b
             f s (b, Nothing) = not s || not b

initVal :: Int -> Int -> Int -> Int -> Maybe Bool
initVal k p i j
    | i < 0 || j < 0 || i + j < k = Just True
    | i + j == k                  = Just (odd (j + p))
    | otherwise                   = Nothing

fastReduce :: Int -> Int -> Int -> Bool
fastReduce k n m
    | n >= 2 && m >= 2 && n + m >= k + 4 = fastReduce k (n - 1) (m - 1)
    | otherwise                          = sum' initVals
    where initVals = [[initVal k (n + m - k) i j | j <- [m0 .. m]] | i <- [n0 .. n]]
          n0       = max 0 (k - m) - 1
          m0       = max 0 (k - n) - 1

solve :: Input -> Output
solve (k, a) = fastReduce k (count even a) (count odd a)
               where count f = length . filter f

bool :: a -> a -> Bool -> a
bool f _ False = f
bool _ t True  = t

main :: IO ()
main = putStrLn . bool "Daenerys" "Stannis" . solve . parse =<< getContents
