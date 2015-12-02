import Data.Char (ord)

parse :: String -> String
parse = (!! 1) . lines

solve :: String -> Int
solve s = 1 + ones + min zeros 2
    where a = map (\c -> ord c - 48) s
          zeros = length . filter id $ zipWith (==) a (tail a)
          ones = length a - 1 - zeros

main :: IO ()
main = print . solve . parse =<< getContents
