import Data.List (sort)

type Face = ((Char, Char), (Char, Char))

parse :: String -> [String]
parse = tail . lines

isFace :: Face -> Bool
isFace ((a, b), (c, d)) = (sort [a, b, c, d]) == "acef"

mkFaces :: [String] -> [Face]
mkFaces = concat . pairWith zip . map (pairWith (,))
          where pairWith f a = zipWith f a (tail a)

solve :: [String] -> Int
solve = length . filter isFace . mkFaces

main :: IO ()
main = print . solve . parse =<< getContents
