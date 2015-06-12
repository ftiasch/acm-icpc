import Data.Char (ord)
import Data.List

type Input  = [Int]
type Output = Maybe [Int]

readInt :: String -> Int
readInt = foldl' (\s d -> s * 10 + ord d - 48) 0

parse :: String -> Input
parse contents = a where [_, a] = map (map readInt . words) . lines $ contents

check :: [Int] -> Maybe [Int]
check a | sorted    = Just a
        | otherwise = Nothing
        where sorted = and $ zipWith (<=) (0 : a) a

solve :: Input -> Output
solve = check . add (map negate nats) . sort . add nats
        where nats = [0..]
              add  = zipWith (+)

format :: Output -> String
format Nothing  = ":("
format (Just a) = unwords . map show $ a

main :: IO ()
main = putStrLn . format . solve . parse =<< getContents
