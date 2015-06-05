import Data.Char (ord, chr)
import Data.List (find)

parse :: String -> [Int]
parse = map ((Prelude.- 48) . ord)

isSubSeqOf :: (Eq a) => [a] -> [a] -> Bool
isSubSeqOf [] _ = True
isSubSeqOf _ [] = False
isSubSeqOf (p : ps) (s : ss)
    | p == s    = ps       `isSubSeqOf` ss
    | otherwise = (p : ps) `isSubSeqOf` ss

solve :: [Int] -> Maybe Int
solve n = find ((`isSubSeqOf` n) . parse . show) [0,8..999]

format Nothing  = "NO"
format (Just a) = "YES\n" ++ (show a)

main :: IO ()
main = putStrLn . format . solve . parse =<< getLine
