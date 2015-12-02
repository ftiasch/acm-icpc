import Data.Bits (xor)
import Data.Char (ord)
import Data.List (foldl1')

readInt :: String -> Int
readInt = foldl (\s c -> s * 10 + ord c - 48) 0

type Input = (Int, [Int])

parse :: String -> Input
parse contents = (k, readInts l1)
    where [l0, l1] = lines contents
          [_, k] = readInts l0
          readInts l = map readInt $ words l

sgEven :: Int -> Int
sgEven n
    | n < 3     = n
    | otherwise = (n + 1) `mod` 2

sgOdd :: Int -> Int
sgOdd n
    | n < 5 = [0, 1, 0, 1, 2] !! n
    | odd n = 0
    | otherwise = let sg' = sgOdd (n `div` 2) in
                  case sg' of
                      0 -> 1
                      1 -> 2
                      2 -> 1
                      _ -> undefined

sg :: Int -> Int -> Int
sg k
    | odd k = sgOdd
    | otherwise = sgEven

solve :: Input -> Bool
solve (k, a) = s == 0
    where s = foldl1' xor . map (sg k) $ a

format :: Bool -> String
format False = "Kevin"
format True = "Nicky"

main :: IO ()
main = putStrLn . format . solve . parse =<< getContents
